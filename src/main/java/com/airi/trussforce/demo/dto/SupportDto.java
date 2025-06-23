package com.airi.trussforce.demo.dto;

public class SupportDto {
    public int nodeId;
    public boolean fixedX;
    public boolean fixedY;

    // デフォルトコンストラクタ（Jackson用）
    public SupportDto() {
    }

    // 任意で使えるコンストラクタ
    public SupportDto(int nodeId, boolean fixedX, boolean fixedY) {
        this.nodeId = nodeId;
        this.fixedX = fixedX;
        this.fixedY = fixedY;
    }

    @Override
    public String toString() {
        return "SupportDto[nodeId=" + nodeId + ", fixedX=" + fixedX + ", fixedY=" + fixedY + "]";
    }
}
