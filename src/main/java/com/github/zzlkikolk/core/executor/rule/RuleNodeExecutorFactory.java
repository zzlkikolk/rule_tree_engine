package com.github.zzlkikolk.core.executor.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhilin
 */
public class RuleNodeExecutorFactory {

    private final Map<String,RuleNodeExecutor> executorMap;

    public RuleNodeExecutorFactory(
            List<RuleNodeExecutor> ruleNodeExecutors
    ){
        executorMap=new HashMap<>();
        ruleNodeExecutors.forEach(r->executorMap.put(r.getNodeType(),r));
    }

    public RuleNodeExecutorFactory(
            Map<String,RuleNodeExecutor> executorMap
    ){
        this.executorMap=executorMap;
    }


    public RuleNodeExecutor getRuleNodeExecutor(String type){
        return executorMap.get(type);
    }
}
