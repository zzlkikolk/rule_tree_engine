package com.github.zzlkikolk.core.executor;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.model.rule.BranchNode;
import com.github.zzlkikolk.core.model.rule.RootNode;

import java.util.List;

/**
 * 分支节点执行器
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 17:39
 */
public class BranchNodeExecutor implements RuleNodeExecutor{

    private final RuleNodeExecutorFactory ruleNodeExecutorFactory;

    public BranchNodeExecutor(RuleNodeExecutorFactory ruleNodeExecutorFactory){
        this.ruleNodeExecutorFactory=ruleNodeExecutorFactory;
    }

    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        BranchNode branchNode = (BranchNode) rootNode;

        List<BranchNode.CaseNode> caseNodes = branchNode.getCaseNodes();
        boolean check = false;

        // 遍历每个 Case 分支
        for (BranchNode.CaseNode caseNode : caseNodes) {
            boolean flag = false;
            List<RootNode> conditions = caseNode.getConditions();
            // 1. 执行分支的所有条件
            for (RootNode condition : conditions) {
                RuleNodeExecutor ruleNodeExecutor = ruleNodeExecutorFactory.getRuleNodeExecutor(condition.getType());
                flag = ruleNodeExecutor.execute(context,condition);
                if(!flag) break; //未通过直接跳出
            }
            if(!flag) break;//未通过直接跳出当前case

            List<RootNode> then = caseNode.getThen();
            for (RootNode node : then) {
                RuleNodeExecutor ruleNodeExecutor = ruleNodeExecutorFactory.getRuleNodeExecutor(node.getType());
                check=ruleNodeExecutor.execute(context,node);
                if(!check) break;//有一个条件为通过则直接退出
            }
        }

        return check;
    }

    @Override
    public String getNodeType() {
        return "branch";
    }
}
