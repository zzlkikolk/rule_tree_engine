package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 短信通知用户
 */
@Setter
@Getter
@JsonTypeName("smsNotification")
public class SmsNotificationAction extends Actions {

    public String getType() {
        return "smsNotification";
    }
}
