package core.model.rule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 逻辑节点
 */
@Setter
@Getter
public class LogicalNode extends RootNode{

    private List<RootNode> nodes; // 逻辑节点


    public String getType() {
        return "logical";
    }
}
