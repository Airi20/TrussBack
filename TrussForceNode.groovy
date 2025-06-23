const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const { spawn } = require('child_process');

const app = express();
const PORT = 5000;

app.use(cors());
app.use(bodyParser.json());

app.post('/solve', (req, res) => {
  const input = JSON.stringify(req.body);

  // Javaアプリを実行
  const javaProcess = spawn('java', ['-jar', 'app.jar']);

  let stdout = '';
  let stderr = '';

  // stdoutを受け取る
  javaProcess.stdout.on('data', (data) => {
    stdout += data.toString();
  });

  // stderrも受け取っておく（エラー用）
  javaProcess.stderr.on('data', (data) => {
    stderr += data.toString();
  });

  // 入力をJavaプロセスへ送信し、終了
  javaProcess.stdin.write(input);
  javaProcess.stdin.end();

  // 終了後の処理
  javaProcess.on('close', (code) => {
    if (code !== 0 || stderr) {
      console.error('Java stderr:', stderr);
      return res.status(500).json({ error: 'Javaアプリ実行エラー', detail: stderr });
    }

    try {
      const output = JSON.parse(stdout); // JSONとして返す
      res.json(output);
    } catch (e) {
      console.error('JSON parse error:', e);
      res.status(500).json({ error: 'Java出力の解析に失敗しました', rawOutput: stdout });
    }
  });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
