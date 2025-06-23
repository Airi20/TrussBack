package com.airi.trussforce.demo;

public class Node {
    private int id;
    private double x;
    private double y;
    private boolean supportX;
    private boolean supportY;
    private double loadX;
    private double loadY;
    private String support; // 変換後の「pin」などの支点情報もここに持たせる

    // 空コンストラクタ（Jacksonとかで必須なら）
    public Node() {}

    // フルコンストラクタ
    public Node(int id, double x, double y, boolean supportX, boolean supportY, double loadX, double loadY) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.supportX = supportX;
        this.supportY = supportY;
        this.loadX = loadX;
        this.loadY = loadY;
        setSupportFromBooleans();
    }

    // supportX, supportYのbool値からsupport文字列をセット
    private void setSupportFromBooleans() {
        if (supportX && supportY) {
            support = "pin";
        } else if (supportX) {
            support = "rollerx";
        } else if (supportY) {
            support = "rollery";
        } else {
            support = null; // 自由節点
        }
    }

    // ゲッター・セッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public boolean isSupportX() { return supportX; }
    public void setSupportX(boolean supportX) { 
        this.supportX = supportX; 
        setSupportFromBooleans();
    }

    public boolean isSupportY() { return supportY; }
    public void setSupportY(boolean supportY) { 
        this.supportY = supportY; 
        setSupportFromBooleans();
    }

    public double getLoadX() { return loadX; }
    public void setLoadX(double loadX) { this.loadX = loadX; }

    public double getLoadY() { return loadY; }
    public void setLoadY(double loadY) { this.loadY = loadY; }

    public String getSupport() { return support; }
    public void setSupport(String support) { this.support = support; }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", supportX=" + supportX +
                ", supportY=" + supportY +
                ", loadX=" + loadX +
                ", loadY=" + loadY +
                ", support='" + support + '\'' +
                '}';
    }
}
