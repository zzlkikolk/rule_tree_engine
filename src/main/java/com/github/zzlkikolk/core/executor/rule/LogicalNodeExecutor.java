package com.github.zzlkikolk.core.executor.rule;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.executor.actions.ActionsExecutorFactory;
import com.github.zzlkikolk.core.model.rule.LogicalNode;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.Setter;

import java.util.List;

/**
 * 逻辑节点执行器
 * @author zhangzhilin
 */
public class LogicalNodeExecutor implements RuleNodeExecutor{

    @Setter
    private  RuleNodeExecutorFactory ruleNodeExecutorFactory;



    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        if(ruleNodeExecutorFactory==null){
            throw new IllegalArgumentException("ruleNodeExecutorFactory is not null");
        }

        LogicalNode logicalNode = (LogicalNode) rootNode;
        List<RootNode> children = logicalNode.getChildren();
        if(children==null||children.isEmpty()){
            throw new  IllegalArgumentException("children is empty");
        }

        String operator = logicalNode.getOperator();
    // 根据不同的逻辑运算符执行不同的逻辑
        return switch (operator.toUpperCase()) {
            case "OR" -> executeOr(context, children);
            case "AND" -> executeAnd(context, children);
            case "NOT" -> executeNot(context, children);
            default -> throw new IllegalArgumentException("Unsupported logical operator: " + operator);
        };
    }
    /**
     * 执行 OR 逻辑：任一子节点为true则返回true，并中断执行；全为false则返回false。
     */
    private boolean executeOr(RuleExecutionContext context, List<RootNode> children) {
        for (RootNode child : children) {
            RuleNodeExecutor executor = ruleNodeExecutorFactory.getRuleNodeExecutor(child.getType());
            if (executor == null) {
                throw new IllegalArgumentException("No executor found for node type: " + child.getType());
            }
            boolean childResult = executor.execute(context, child);
            if (childResult) { // 如果任一子节点结果为true
                return true; // 立即返回true，中断执行
            }
        }
        return false; // 所有子节点结果都为false
    }

    /**
     * 执行 AND 逻辑：所有子节点都为true则返回true；遇到第一个false则返回false并中断执行。
     */
    private boolean executeAnd(RuleExecutionContext context, List<RootNode> children) {
        for (RootNode child : children) {
            RuleNodeExecutor executor = ruleNodeExecutorFactory.getRuleNodeExecutor(child.getType());
            if (executor == null) {
                throw new IllegalArgumentException("No executor found for node type: " + child.getType());
            }
            boolean childResult = executor.execute(context, child);
            if (!childResult) { // 如果任一子节点结果为false
                return false; // 立即返回false，中断执行
            }
        }
        return true; // 所有子节点结果都为true
    }

    /**
     * 执行 NOT 逻辑：
     * 1. NOT 只能有一个子节点（一元运算符），否则抛出异常。
     * 2. 对子节点的结果取反后返回。
     */
    private boolean executeNot(RuleExecutionContext context, List<RootNode> children) {
        // 1. 校验子节点数量（NOT 必须是单操作数）
        if (children.size() != 1) {
            throw new IllegalArgumentException("NOT operator must have exactly one child node.");
        }

        // 2. 获取唯一的子节点并执行
        RootNode child = children.get(0);
        RuleNodeExecutor executor = ruleNodeExecutorFactory.getRuleNodeExecutor(child.getType());
        if (executor == null) {
            throw new IllegalArgumentException("No executor found for node type: " + child.getType());
        }

        // 3. 对子节点结果取反
        boolean childResult = executor.execute(context, child);
        return !childResult; // 核心逻辑：取反
    }
    @Override
    public String getNodeType() {
        return "logical";
    }
}
