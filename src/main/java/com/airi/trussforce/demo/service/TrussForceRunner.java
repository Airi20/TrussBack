package com.airi.trussforce.demo.service;

import java.io.*;

public class TrussForceRunner {

    public static String runTrussForceApp(String jsonInput) throws IOException, InterruptedException {
        // 一時ファイルを作成（自動削除される）
        File inputFile = File.createTempFile("truss_input", ".json");
        File outputFile = File.createTempFile("truss_output", ".json");

        // JSONを入力ファイルに書き出し
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(jsonInput);
        }

      // src/main/resources にある jar を絶対パスに変換
File jarFile = new File("src/main/resources/TrussForceApp.jar");
ProcessBuilder pb = new ProcessBuilder(
    "java", "-jar", jarFile.getAbsolutePath(),
    inputFile.getAbsolutePath(), outputFile.getAbsolutePath()
);


        pb.redirectErrorStream(true); // 標準エラーも取得
        Process process = pb.start();

        // 実行中のログを読む（任意）
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[TrussForceApp] " + line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("TrussForceApp.jar failed with exit code: " + exitCode);
        }

        // 出力ファイルから結果を読み込む
        return new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
    }
}
