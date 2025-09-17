package com.github.zzlkikolk.core.engine;

import com.github.zzlkikolk.context.variables.ContextParam;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutorFactory;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:49
 */
@RequiredArgsConstructor
public class RuleEngine {

    private final RuleNodeExecutorFactory ruleNodeExecutorFactory;


    public boolean execute(List<RootNode> list, ContextParam contextParam){
        boolean check = false;
        for (RootNode rootNode : list) {
            RuleNodeExecutor ruleNodeExecutor = ruleNodeExecutorFactory.getRuleNodeExecutor(rootNode.getType());
        }
        //TODO 执行规则
        return check;
    }
}
