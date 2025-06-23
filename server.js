const express = require('express');
const app = express();
const PORT = 5000;

app.use(express.json());

// テスト用エンドポイント（ReactやPostmanから使う）
app.post('/api/calculate', (req, res) => {
  console.log('受信データ:', req.body);
  // 仮のレスポンス（あとでJava連携に差し替え予定）
  res.json({ status: 'ok', message: '計算データ受信完了', data: req.body });
});

app.listen(PORT, () => {
  console.log(`✅ TrussForce APIサーバー起動中：http://localhost:${PORT}`);
});
