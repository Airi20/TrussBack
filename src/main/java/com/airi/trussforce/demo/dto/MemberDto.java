package com.airi.trussforce.demo.dto;

import java.util.ArrayList;

public class MemberDto {
    private int id;
    private int startNode;
    private int endNode;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStartNode() { return startNode; }
    public void setStartNode(int startNode) { this.startNode = startNode; }

    public int getEndNode() { return endNode; }
    public void setEndNode(int endNode) { this.endNode = endNode; }

    @Override
    public String toString() {
        return "MemberDto{id=" + id + ", startNode=" + startNode + ", endNode=" + endNode + "}";
    }
}
