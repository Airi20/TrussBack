package com.airi.trussforce.demo.converter;

import com.airi.trussforce.demo.service.TrussForceSolver;
import com.airi.trussforce.demo.dto.TrussResult;

public class ResultConverter {
    public static TrussResult convert(TrussForceSolver.Result result) {
        TrussResult dto = new TrussResult();
        dto.setReactionsX(result.reactionsX);
        dto.setReactionsY(result.reactionsY);
        dto.setMemberForces(result.memberForces);
        return dto;
    }
}
