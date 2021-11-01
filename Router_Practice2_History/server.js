const express = require('express');
const path = require('path');

const app = express();

app.listen(process.env.PORT || 3000, () => {
  console.log("Server is running...");
});

// 정적 폴더 경로 설정
app.use(
  "/static",
  express.static(path.resolve(__dirname, "frontend", "static"))
);

// 어떤 요청이 들어오든 다 index.html로 전송
app.get("/*", (req, res) => {
  res.sendFile(path.resolve(__dirname, "frontend", "index.html"));
});

