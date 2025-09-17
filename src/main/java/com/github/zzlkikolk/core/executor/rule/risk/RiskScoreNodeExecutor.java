package com.github.zzlkikolk.core.executor.rule.risk;

import com.github.zzlkikolk.context.RiskControlContext;
import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.executor.actions.ActionsExecutor;
import com.github.zzlkikolk.core.executor.actions.ActionsExecutorFactory;
import com.github.zzlkikolk.core.executor.rule.AbstractActionsRuleNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutor;
import com.github.zzlkikolk.core.model.actions.Actions;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.model.rule.leaf.RiskScoreNode;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:39
 */
public class RiskScoreNodeExecutor extends AbstractActionsRuleNodeExecutor {
    public RiskScoreNodeExecutor(ActionsExecutorFactory actionsExecutorFactory) {
        super(actionsExecutorFactory);
    }

    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        if(!(context instanceof RiskControlContext)){
            return true;
        }

        RiskControlContext riskControlContext = (RiskControlContext) context;
        RiskScoreNode riskScoreNode = (RiskScoreNode) rootNode;

        Long score = riskControlContext.getData(riskScoreNode.getType(),Long.class);

        boolean result= switch (riskScoreNode.getValue().getOperator()) {
            case ">=" -> score >= riskScoreNode.getValue().getScore();
            case ">" -> score > riskScoreNode.getValue().getScore();
            case "=", "==" -> score == riskScoreNode.getValue().getScore();
            case "<=" -> score <= riskScoreNode.getValue().getScore();
            case "<" -> score < riskScoreNode.getValue().getScore();
            default -> false;
        };

        //执行动作
        if(riskScoreNode.getActions()!=null){
            for (Actions action : riskScoreNode.getActions()) {
               ActionsExecutor actionsExecutor = actionsExecutorFactory.getActionsExecutor(action.getType());
               actionsExecutor.execute(context,action,result);
            }
        }

        return result;
    }

    @Override
    public String getNodeType() {
        return "riskScore";
    }
}
