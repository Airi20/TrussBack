package com.airi.trussforce.demo.dto;

import com.airi.trussforce.demo.service.TrussForceSolver;

import java.util.Map;

public class SolveResultDto {
    private Map<Integer, Double> reactionsX;
    private Map<Integer, Double> reactionsY;
    private Map<String, Double> memberForces;

    public SolveResultDto(TrussForceSolver.Result result) {
        this.reactionsX = result.reactionsX;
        this.reactionsY = result.reactionsY;
        this.memberForces = result.memberForces;
    }

    public Map<Integer, Double> getReactionsX() {
        return reactionsX;
    }

    public void setReactionsX(Map<Integer, Double> reactionsX) {
        this.reactionsX = reactionsX;
    }

    public Map<Integer, Double> getReactionsY() {
        return reactionsY;
    }

    public void setReactionsY(Map<Integer, Double> reactionsY) {
        this.reactionsY = reactionsY;
    }

    public Map<String, Double> getMemberForces() {
        return memberForces;
    }

    public void setMemberForces(Map<String, Double> memberForces) {
        this.memberForces = memberForces;
    }
}
