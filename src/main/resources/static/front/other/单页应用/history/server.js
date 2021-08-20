let express = require('express');
let path = require('path');
let app = express();
 
app.get('/', function (req, res) {
   res.sendFile(path.resolve(__dirname,  './www/history.html'));
});

app.get('*', function (req, res) {
   res.sendFile(path.resolve(__dirname,  './www/history.html'));
});

const port = 3333;

let server = app.listen(port, function () {
  console.log("访问地址为 http://localhost:" + port)
});