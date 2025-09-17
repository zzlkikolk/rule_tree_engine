package com.github.zzlkikolk.core.engine;

import com.github.zzlkikolk.context.RiskControlContext;
import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.context.provider.ContextDataProvider;
import com.github.zzlkikolk.context.variables.ContextParam;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutorFactory;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 14:30
 */
@RequiredArgsConstructor
public class RiskRuleEngine implements RuleEngine{

    private final RuleNodeExecutorFactory ruleNodeExecutorFactory;

    private final List<ContextDataProvider<?>> riskContextDataProviders;

    public boolean execute(List<RootNode> list, ContextParam contextParam){
        boolean check = false;
        for (RootNode rootNode : list) {
            RuleNodeExecutor ruleNodeExecutor = ruleNodeExecutorFactory.getRuleNodeExecutor(rootNode.getType());
            RuleExecutionContext riskControlContext = new RiskControlContext(contextParam,riskContextDataProviders);
            check=ruleNodeExecutor.execute(riskControlContext,rootNode);
            if(!check){
                return check;
            }
        }
        return check;
    }
}
