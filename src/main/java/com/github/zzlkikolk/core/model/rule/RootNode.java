package com.github.zzlkikolk.core.model.rule;

import com.github.zzlkikolk.core.model.actions.Actions;
import lombok.Getter;

import java.util.List;

/**
 * root node
 */
@Getter
public abstract class RootNode {

    private String type;

    private List<Actions> actions;
}
