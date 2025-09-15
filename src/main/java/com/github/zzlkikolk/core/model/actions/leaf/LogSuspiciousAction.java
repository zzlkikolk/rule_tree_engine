package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 记录风控行为日志
 */
@Setter
@Getter
@JsonTypeName("logSuspicious")
public class LogSuspiciousAction extends Actions {

    public String getType() {
        return "logSuspicious";
    }
}
