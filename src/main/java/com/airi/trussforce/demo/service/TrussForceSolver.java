package com.airi.trussforce.demo.service;

import java.util.*;

public class TrussForceSolver {

    public static class Node {
        public int id;
        public double x, y;
        public String support; // "pin", "rollerx", "rollery", null
        public double fx, fy;  // 荷重（外力）
    
    // ✅ コンストラクタを定義
    public Node(int id, double x, double y, boolean supportX, boolean supportY, double fx, double fy) {
        this.id = id;
        this.x = x;
        this.y = y;
    
        if (supportX && supportY) {
            this.support = "pin";
        } else if (supportX) {
            this.support = "rollerx";
        } else if (supportY) {
            this.support = "rollery";
        } else {
            this.support = null;
        }

        this.fx = fx;
        this.fy = fy;
    }
}
    public static class Member {
        public int startNode;
        public int endNode;
    }

    public static class Result {
        public Map<Integer, Double> reactionsX = new HashMap<>();
        public Map<Integer, Double> reactionsY = new HashMap<>();
        public Map<String, Double> memberForces = new HashMap<>();
    }

    public static Result solve(List<Node> nodes, List<Member> members) {
        Map<Integer, Integer> reactionXIndex = new HashMap<>();
        Map<Integer, Integer> reactionYIndex = new HashMap<>();
        int reactionCount = 0;

        for (Node n : nodes) {
            if (n.support == null) continue;
            String s = n.support.toLowerCase();
            if (s.contains("pin")) {
                reactionXIndex.put(n.id, reactionCount++);
                reactionYIndex.put(n.id, reactionCount++);
            } else if (s.contains("rollerx")) {
                reactionXIndex.put(n.id, reactionCount++);
            } else if (s.contains("rollery")) {
                reactionYIndex.put(n.id, reactionCount++);
            }
        }

        int varCount = reactionCount + members.size();
        int eqCount = nodes.size() * 2;
        double[][] A = new double[eqCount][varCount];
        double[] b = new double[eqCount];

        Map<Integer, Integer> rowX = new HashMap<>();
        Map<Integer, Integer> rowY = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            rowX.put(nodes.get(i).id, i * 2);
            rowY.put(nodes.get(i).id, i * 2 + 1);
        }

        // 反力の係数をセット
        for (Node n : nodes) {
            int rx = reactionXIndex.getOrDefault(n.id, -1);
            int ry = reactionYIndex.getOrDefault(n.id, -1);
            int rX = rowX.get(n.id);
            int rY = rowY.get(n.id);
            if (rx >= 0) A[rX][rx] = 1.0;
            if (ry >= 0) A[rY][ry] = 1.0;
        }

        // 部材の係数をセット
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            Node n1 = getById(nodes, m.startNode);
            Node n2 = getById(nodes, m.endNode);
            if (n1 == null || n2 == null) continue;
            double dx = n2.x - n1.x;
            double dy = n2.y - n1.y;
            double len = Math.hypot(dx, dy);
            if (len < 1e-10) continue;
            double cos = dx / len, sin = dy / len;
            A[rowX.get(n1.id)][reactionCount + i] = cos;
            A[rowY.get(n1.id)][reactionCount + i] = sin;
            A[rowX.get(n2.id)][reactionCount + i] = -cos;
            A[rowY.get(n2.id)][reactionCount + i] = -sin;
        }

        // 外力をセット（符号に注意）
        for (Node n : nodes) {
            b[rowX.get(n.id)] = -n.fx;
            b[rowY.get(n.id)] = -n.fy;
        }

        double[] x = gaussJordan(A, b);
        if (x == null) return null;

        Result result = new Result();
        for (Map.Entry<Integer, Integer> e : reactionXIndex.entrySet()) {
            result.reactionsX.put(e.getKey(), x[e.getValue()]);
        }
        for (Map.Entry<Integer, Integer> e : reactionYIndex.entrySet()) {
            result.reactionsY.put(e.getKey(), x[e.getValue()]);
        }
        for (int i = 0; i < members.size(); i++) {
            String key = members.get(i).startNode + "-" + members.get(i).endNode;
            result.memberForces.put(key, x[reactionCount + i]);
        }
        return result;
    }

    private static Node getById(List<Node> list, int id) {
        for (Node n : list) if (n.id == id) return n;
        return null;
    }

    private static double[] gaussJordan(double[][] A, double[] b) {
        int n = A.length, m = A[0].length;
        double[][] mat = new double[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, mat[i], 0, m);
            mat[i][m] = b[i];
        }

        int row = 0;
        for (int col = 0; col < m && row < n; col++) {
            int sel = row;
            for (int i = row + 1; i < n; i++) {
                if (Math.abs(mat[i][col]) > Math.abs(mat[sel][col])) sel = i;
            }
            if (Math.abs(mat[sel][col]) < 1e-12) continue;
            double[] tmp = mat[sel]; mat[sel] = mat[row]; mat[row] = tmp;

            double div = mat[row][col];
            for (int j = col; j <= m; j++) mat[row][j] /= div;
            for (int i = 0; i < n; i++) {
                if (i != row) {
                    double mul = mat[i][col];
                    for (int j = col; j <= m; j++) {
                        mat[i][j] -= mat[row][j] * mul;
                    }
                }
            }
            row++;
        }

        double[] x = new double[m];
        for (int i = 0; i < n; i++) {
            int pivot = -1;
            for (int j = 0; j < m; j++) {
                if (Math.abs(mat[i][j] - 1.0) < 1e-10) {
                    pivot = j;
                    break;
                }
            }
            if (pivot != -1) x[pivot] = mat[i][m];
        }
        return x;
    }
}
