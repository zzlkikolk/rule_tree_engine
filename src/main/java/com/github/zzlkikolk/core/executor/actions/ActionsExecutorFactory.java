package com.github.zzlkikolk.core.executor.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 13:49
 */
public class ActionsExecutorFactory {

    private final Map<String,ActionsExecutor> actionsExecutorMap;

    public ActionsExecutorFactory(List<ActionsExecutor> actionsExecutors){
        actionsExecutorMap = new HashMap<>();
        actionsExecutors.forEach(a->actionsExecutorMap.put(a.getType(),a));
    }

    public ActionsExecutorFactory(Map<String,ActionsExecutor> actionsExecutorMap){
        this.actionsExecutorMap=actionsExecutorMap;
    }


    public ActionsExecutor getActionsExecutor(String type){
        return actionsExecutorMap.get(type);
    }
}
