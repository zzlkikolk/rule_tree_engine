package com.github.zzlkikolk.core.executor.rule;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.executor.actions.ActionsExecutorFactory;
import com.github.zzlkikolk.core.model.rule.BranchNode;
import com.github.zzlkikolk.core.model.rule.RootNode;

import java.util.List;

/**
 * 分支节点执行器
 * @author zhangzhilin
 */
public class BranchNodeExecutor implements RuleNodeExecutor{

    private final RuleNodeExecutorFactory ruleNodeExecutorFactory;

    public BranchNodeExecutor(RuleNodeExecutorFactory ruleNodeExecutorFactory){
        this.ruleNodeExecutorFactory=ruleNodeExecutorFactory;
    }

    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        BranchNode branchNode = (BranchNode) rootNode;
        // 遍历每个 Case 分支
        for (BranchNode.CaseNode caseNode : branchNode.getCaseNodes()) {
            // 条件检查
            boolean condOk = true;
            for (RootNode condition : caseNode.getConditions()) {
                RuleNodeExecutor executor = ruleNodeExecutorFactory.getRuleNodeExecutor(condition.getType());
                if (!executor.execute(context, condition)) {
                    condOk = false;
                    break;
                }
            }
            if (!condOk) {
                continue;  // 去试下一个 case
            }
            // then 动作
            boolean actionOk = true;
            for (RootNode actionNode : caseNode.getThen()) {
                RuleNodeExecutor executor = ruleNodeExecutorFactory.getRuleNodeExecutor(actionNode.getType());
                if (!executor.execute(context, actionNode)) {
                    actionOk = false;
                    break;
                }
            }
            if (actionOk) {
                return true;  // 一旦 then 成功，整个 branch node 可以返回 true
            }
        }
        return false;  // 所有 case 都没成功 then，就返回 false
    }

    @Override
    public String getNodeType() {
        return "branch";
    }
}
