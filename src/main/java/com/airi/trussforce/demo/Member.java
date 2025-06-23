package com.airi.trussforce.demo;

public class Member {
    public int startNodeId;
    public int endNodeId;

    public Member() {} // JSONデシリアライズ用に空コンストラクタ必須

    public Member(int startNodeId, int endNodeId) {
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
    }
}
