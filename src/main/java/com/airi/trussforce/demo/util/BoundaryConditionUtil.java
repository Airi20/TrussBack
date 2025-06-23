package com.airi.trussforce.demo.util;

import java.util.*;
import com.airi.trussforce.demo.dto.SupportDto;

public class BoundaryConditionUtil {

    /**
     * 固定されている自由度（DOF）のインデックスを返す
     * @param supports 支点情報
     * @return 固定されている自由度のリスト
     */
    public static List<Integer> getFixedDOFs(List<SupportDto> supports) {
        List<Integer> fixedDOFs = new ArrayList<>();
        for (SupportDto support : supports) {
            int nodeId = support.nodeId;
            if (support.fixedX) {
                fixedDOFs.add(2 * nodeId);
            }
            if (support.fixedY) {
                fixedDOFs.add(2 * nodeId + 1);
            }
        }
        return fixedDOFs;
    }

    /**
     * 全自由度数を元に、未知の自由度（＝固定されていないDOF）を返す
     */
    public static List<Integer> getFreeDOFs(int totalDOF, List<Integer> fixedDOFs) {
        List<Integer> freeDOFs = new ArrayList<>();
        for (int i = 0; i < totalDOF; i++) {
            if (!fixedDOFs.contains(i)) {
                freeDOFs.add(i);
            }
        }
        return freeDOFs;
    }
}
