package com.airi.trussforce.demo.controller;

import com.airi.trussforce.demo.dto.MemberDto;
import com.airi.trussforce.demo.dto.NodeDto;
import com.airi.trussforce.demo.dto.SolveRequestDto;
import com.airi.trussforce.demo.dto.SolveResultDto;
import com.airi.trussforce.demo.service.TrussForceSolver;
import com.airi.trussforce.demo.converter.NodeConverter;
import com.airi.trussforce.demo.converter.MemberConverter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SolveController {

    @PostMapping("/api/solve")
    public ResponseEntity<SolveResultDto> solve(@RequestBody SolveRequestDto request) {
        System.out.println("受信ノードDTO: " + request.getNodes());
        System.out.println("受信部材DTO: " + request.getMembers());

        List<TrussForceSolver.Node> nodes = NodeConverter.toSolverNodeList(request.getNodes());
        List<TrussForceSolver.Member> members = MemberConverter.toSolverMemberList(request.getMembers());

        System.out.println("変換後ノード: " + nodes);
        System.out.println("変換後部材: " + members);

        TrussForceSolver.Result result = TrussForceSolver.solve(nodes, members);

        if (result == null) {
            System.out.println("計算失敗：連立方程式の解なし");
            return ResponseEntity.badRequest().build();
        }

        System.out.println("計算結果 - 反力X: " + result.reactionsX);
        System.out.println("計算結果 - 反力Y: " + result.reactionsY);
        System.out.println("計算結果 - 部材力: " + result.memberForces);

        return ResponseEntity.ok(new SolveResultDto(result));
    }
}
