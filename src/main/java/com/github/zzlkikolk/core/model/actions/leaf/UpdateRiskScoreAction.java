package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 更新信用分
 */
@Getter
@Setter
@JsonTypeName("updateRiskScore")
public class UpdateRiskScoreAction extends Actions {

    public String getType() {
        return "updateRiskScore";
    }
}
