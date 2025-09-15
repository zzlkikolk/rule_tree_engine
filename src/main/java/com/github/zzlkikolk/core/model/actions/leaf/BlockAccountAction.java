package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 冻结账户
 */
@Getter
@Setter
@JsonTypeName("blockAccount")
public class BlockAccountAction extends Actions {

    public String getType() {
        return "blockAccount";
    }
}
