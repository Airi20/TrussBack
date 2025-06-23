const express = require('express');
const app = express();
const PORT = 5000;

app.use(express.json());

app.post('/api/calculate', (req, res) => {
  console.log('受信データ:', req.body);
  res.json({ status: 'ok', message: '計算データ受信完了', data: req.body });
});

app.listen(PORT, () => {
  console.log(`🚀 サーバーが http://localhost:${PORT} で起動しました`);
});
