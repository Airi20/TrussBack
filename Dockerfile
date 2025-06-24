# Java17の軽量イメージをベースにする
FROM openjdk:17-jdk-slim

# 作業ディレクトリを/appに設定
WORKDIR /app

# ビルド済みjarをコンテナにコピー
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Javaアプリを起動
CMD ["java", "-jar", "app.jar"]
