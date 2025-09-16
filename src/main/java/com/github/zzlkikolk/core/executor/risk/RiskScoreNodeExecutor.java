package com.github.zzlkikolk.core.executor.risk;

import com.github.zzlkikolk.context.RiskControlContext;
import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.executor.RuleNodeExecutor;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.model.rule.leaf.RiskScoreNode;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:39
 */
public class RiskScoreNodeExecutor implements RuleNodeExecutor {
    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        RiskControlContext riskControlContext = (RiskControlContext) context;
        RiskScoreNode riskScoreNode = (RiskScoreNode) rootNode;

        Long score = riskControlContext.getData(riskScoreNode.getType(),Long.class);

        return switch (riskScoreNode.getValue().getOperator()) {
            case ">=" -> score >= riskScoreNode.getValue().getScore();
            case ">" -> score > riskScoreNode.getValue().getScore();
            case "=", "==" -> score == riskScoreNode.getValue().getScore();
            case "<=" -> score <= riskScoreNode.getValue().getScore();
            case "<" -> score < riskScoreNode.getValue().getScore();
            default -> false;
        };
    }

    @Override
    public String getNodeType() {
        return "riskScore";
    }
}
