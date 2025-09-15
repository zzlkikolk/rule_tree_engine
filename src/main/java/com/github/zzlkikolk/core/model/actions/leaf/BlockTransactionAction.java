package com.github.zzlkikolk.core.model.actions.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;
import lombok.Setter;

/**
 * 阻断交易
 */
@Getter
@Setter
@JsonTypeName("blockTransaction")
public class BlockTransactionAction extends Actions {

    public String getType() {
        return "blockTransaction";
    }
}
