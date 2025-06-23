package com.airi.trussforce.demo.converter;

import com.airi.trussforce.demo.dto.NodeDto;
import com.airi.trussforce.demo.service.TrussForceSolver;

import java.util.ArrayList;
import java.util.List;

public class NodeConverter {

    // NodeDto → Solver用 Node に変換
    public static TrussForceSolver.Node toSolverNode(NodeDto dto) {
        return new TrussForceSolver.Node(
            dto.getId(),
            dto.getX(),
            dto.getY(),
            dto.isSupportX(),
            dto.isSupportY(),
            dto.getLoadX(),
            dto.getLoadY()
        );
    }

    // List<NodeDto> → List<TrussForceSolver.Node> に変換
    public static List<TrussForceSolver.Node> toSolverNodeList(List<NodeDto> dtos) {
        List<TrussForceSolver.Node> nodes = new ArrayList<>();
        for (NodeDto dto : dtos) {
            nodes.add(toSolverNode(dto));
        }
        return nodes;
    }
}
