package com.airi.trussforce.demo.converter;

import com.airi.trussforce.demo.dto.MemberDto;
import com.airi.trussforce.demo.service.TrussForceSolver.Member;

import java.util.List;
import java.util.ArrayList;

public class MemberConverter {
    public static Member toSolverMember(MemberDto dto) {
        Member m = new Member();
        m.startNode = dto.getStartNode();  // ← getterを普通に呼ぶだけでOK
        m.endNode = dto.getEndNode();      // ← 同じく
        return m;
    }

    public static List<Member> toSolverMemberList(List<MemberDto> dtos) {
        List<Member> list = new ArrayList<>();
        for (MemberDto dto : dtos) {
            list.add(toSolverMember(dto));
        }
        return list;
    }
}
