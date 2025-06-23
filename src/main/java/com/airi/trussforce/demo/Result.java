package com.airi.trussforce.demo;

import java.util.Map;

public class Result {
    private Map<Integer, Double> reactionForces;
    private Map<Integer, Double> memberForces;

    public Result(Map<Integer, Double> reactionForces, Map<Integer, Double> memberForces) {
        this.reactionForces = reactionForces;
        this.memberForces = memberForces;
    }

    public Map<Integer, Double> getReactionForces() {
        return reactionForces;
    }

    public Map<Integer, Double> getMemberForces() {
        return memberForces;
    }
}
