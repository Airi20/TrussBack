package com.airi.trussforce.demo.dto;

import java.util.List;

public class SolveRequestDto {
    private List<NodeDto> nodes;
    private List<MemberDto> members;

    public List<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDto> nodes) {
        this.nodes = nodes;
    }

    public List<MemberDto> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDto> members) {
        this.members = members;
    }
}
