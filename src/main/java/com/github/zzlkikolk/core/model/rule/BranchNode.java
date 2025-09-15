package com.github.zzlkikolk.core.model.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonTypeName("branch")
public class BranchNode extends RootNode{

    private List<CaseNode> caseNodes;//branch node

    public String getType() {
        return "branch";
    }

    @Getter
    @Setter
    public static class CaseNode extends RootNode{

        private List<RootNode> conditions; // if

        private List<RootNode> then; // then

        public String getType() {
            return "case";
        }
    }
}
