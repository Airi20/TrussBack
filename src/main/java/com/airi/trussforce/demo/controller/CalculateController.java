package com.airi.trussforce.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {

    @PostMapping("/calculate")
    public String calculate(
        @RequestParam("supportType") String supportType,
        @RequestParam("supportX") double supportX,
        @RequestParam("supportY") double supportY,
        @RequestParam("memberNumber") String memberNumber,
        @RequestParam("jointX") double jointX,
        @RequestParam("jointY") double jointY,
        @RequestParam("load") double load,
        Model model
    ) {
        // ここで受け取った値を使って計算とか処理を書く
        // 例えば計算結果をモデルにセットして画面に返す
        model.addAttribute("result", "ここに計算結果");

        // 結果表示用のテンプレート名を返す（例：result.html）
        return "result";
    }
}
