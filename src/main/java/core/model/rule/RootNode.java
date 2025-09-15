package core.model.rule;

import core.model.actions.ActionsNode;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * root node
 */
@Getter
public abstract class RootNode {
    private String type;

    private List<ActionsNode> actionsNodes;
}
