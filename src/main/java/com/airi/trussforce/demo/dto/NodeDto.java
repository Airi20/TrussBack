package com.airi.trussforce.demo.dto;

public class NodeDto {
    private int id;
    private double x;
    private double y;
    private boolean supportX;
    private boolean supportY;
    private double loadX;
    private double loadY;

    // ✅ 空コンストラクタ（Jacksonなどで必要になることが多い）
    public NodeDto() {}

    // ✅ フルコンストラクタ（手動生成に便利）
    public NodeDto(int id, double x, double y, boolean supportX, boolean supportY, double loadX, double loadY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.supportX = supportX;
        this.supportY = supportY;
        this.loadX = loadX;
        this.loadY = loadY;
    }

    // ✅ Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public boolean isSupportX() { return supportX; }
    public void setSupportX(boolean supportX) { this.supportX = supportX; }

    public boolean isSupportY() { return supportY; }
    public void setSupportY(boolean supportY) { this.supportY = supportY; }

    public double getLoadX() { return loadX; }
    public void setLoadX(double loadX) { this.loadX = loadX; }

    public double getLoadY() { return loadY; }
    public void setLoadY(double loadY) { this.loadY = loadY; }

    // ✅ toString（ログに出すとき便利！）
    @Override
    public String toString() {
        return "NodeDto{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", supportX=" + supportX +
                ", supportY=" + supportY +
                ", loadX=" + loadX +
                ", loadY=" + loadY +
                '}';
    }
}
