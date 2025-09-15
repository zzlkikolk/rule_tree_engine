package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 推送内部告警消息
 */
@Getter
@Setter
@JsonTypeName("pushNotification")
public class PushInnerNotificationAction extends Actions {

    public String getType() {
        return "pushNotification";
    }

}
