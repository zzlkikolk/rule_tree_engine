package com.github.zzlkikolk.core.executor.actions;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.model.actions.Actions;
import com.github.zzlkikolk.core.model.rule.RootNode;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 13:42
 */
public interface ActionsExecutor {

    void execute(RuleExecutionContext context, Actions actions,boolean result);

    String getType();
}
