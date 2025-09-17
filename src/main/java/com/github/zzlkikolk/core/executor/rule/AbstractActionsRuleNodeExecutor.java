package com.github.zzlkikolk.core.executor.rule;

import com.github.zzlkikolk.core.executor.actions.ActionsExecutorFactory;
import lombok.RequiredArgsConstructor;

/**
 * 带有动作执行的规则抽象类
 * @author zhangzhilin
 */
@RequiredArgsConstructor
public abstract class AbstractActionsRuleNodeExecutor implements RuleNodeExecutor{

    protected final ActionsExecutorFactory actionsExecutorFactory;
}
