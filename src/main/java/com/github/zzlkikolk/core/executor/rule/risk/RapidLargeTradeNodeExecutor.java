package com.github.zzlkikolk.core.executor.rule.risk;

import com.github.zzlkikolk.context.RiskControlContext;
import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.context.provider.risk.RapidLargeTradeDataProvider;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutor;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.model.rule.leaf.RapidLargeTradeNode;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 14:37
 */
public class RapidLargeTradeNodeExecutor implements RuleNodeExecutor {
    @Override
    public boolean execute(RuleExecutionContext context, RootNode rootNode) {

        if(!(context instanceof RiskControlContext riskControlContext)){
            return true;
        }

        RapidLargeTradeNode rapidLargeTradeNode = (RapidLargeTradeNode) rootNode;

        RapidLargeTradeDataProvider.RapidLargeTradeData rapidLargeTradeData = riskControlContext.getData(rapidLargeTradeNode.getType(), RapidLargeTradeDataProvider.RapidLargeTradeData.class);

        return rapidLargeTradeData.getAmountThreshold() <= rapidLargeTradeNode.getValue().getAmountThreshold();
    }

    @Override
    public String getNodeType() {
        return "rapidLargeTrade";
    }
}
