package com.github.zzlkikolk.core.executor.actions;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.model.actions.Actions;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 14:01
 */
public class LogSuspiciousActionExecutor implements ActionsExecutor{
    @Override
    public void execute(RuleExecutionContext context, Actions actions,boolean result) {
        System.out.println("打印动作日志"+result);
    }

    @Override
    public String getType() {
        return "logSuspicious";
    }
}
