package com.github.zzlkikolk.core.engine;

import com.github.zzlkikolk.context.provider.ContextDataProvider;
import com.github.zzlkikolk.context.provider.risk.RapidLargeTradeDataProvider;
import com.github.zzlkikolk.context.provider.risk.UserRiskScoreDataProvider;
import com.github.zzlkikolk.context.variables.ContextParam;
import com.github.zzlkikolk.context.variables.RiskControlContextParam;
import com.github.zzlkikolk.core.executor.actions.ActionsExecutorFactory;
import com.github.zzlkikolk.core.executor.actions.LogSuspiciousActionExecutor;
import com.github.zzlkikolk.core.executor.rule.LogicalNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutorFactory;
import com.github.zzlkikolk.core.executor.rule.risk.RapidLargeTradeNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.risk.RiskScoreNodeExecutor;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.parser.JsonRuleParse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiskRuleEngineTest {

    @Test
    void execute() {
        String json ="[{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}},{\"type\":\"logical\",\"operator\":\"AND\",\"children\":[{\"type\":\"riskScore\",\"value\":{\"score\":750,\"operator\":\">=\"},\"actions\":[{\"type\":\"logSuspicious\"}]},{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}}]}]";

        LogSuspiciousActionExecutor logSuspiciousActionExecutor = new LogSuspiciousActionExecutor();

        ActionsExecutorFactory actionsExecutorFactory = new ActionsExecutorFactory(List.of(logSuspiciousActionExecutor));

        RiskScoreNodeExecutor riskScoreNodeExecutor = new RiskScoreNodeExecutor(actionsExecutorFactory);

        LogicalNodeExecutor logicalNodeExecutor = new LogicalNodeExecutor();

        RapidLargeTradeNodeExecutor rapidLargeTradeNodeExecutor = new RapidLargeTradeNodeExecutor();

        RuleNodeExecutorFactory ruleNodeExecutorFactory = new RuleNodeExecutorFactory(List.of(rapidLargeTradeNodeExecutor,riskScoreNodeExecutor,logicalNodeExecutor));
        logicalNodeExecutor.setRuleNodeExecutorFactory(ruleNodeExecutorFactory);

        ContextDataProvider<?> userRiskScoreDataProvider = new UserRiskScoreDataProvider();

        ContextDataProvider<?> rapidLargeTradeDataProvider = new RapidLargeTradeDataProvider();


        List<RootNode> list = JsonRuleParse.parse(json);

        RuleEngine ruleEngine = new RiskRuleEngine(ruleNodeExecutorFactory,List.of(userRiskScoreDataProvider,rapidLargeTradeDataProvider));

        RiskControlContextParam contextParam = new RiskControlContextParam();
        contextParam.setUserId(101088L);
        boolean check= ruleEngine.execute(list,contextParam);
        System.out.println(check);
    }
}