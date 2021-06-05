# Axios
- 基于 Promise 的 HTTP 库
- 可用于浏览器和 node.js 
---
## 特性
- 支持 Promise API
- 拦截请求和响应
- 转换请求数据和响应数据
- 取消请求
- 自动转换 JSON 数据
- 客户端支持防御 XSRF
---
## 创建项目
    1. vue create demo
    2. Please pick a preset                                 Manually select features
    3. Check the features needed for your project           Babel + Router + Vuex + CSS Pre-procesors + Linter / Formatter
    4. Use history mode for router?                         Yes
    5. Pick a CSS pre-processor                             Less
    6. Pick a linter / formatter config                     ESLint with error prevention only
    7. Pick additional lint feautres                        Lint on Save
    8. Where do you prefer placing config for Babel, PostCSS, ESLint, etc.?     In dedicated config files
    9. Save this as a preset for future projects?           N                    
---
## 安装和使用
    1. npm i axios
    2. public 下新建 data.json 文件
    3. import axios from 'axios'
    4. axios.get('/data.json').then((res) => { console.log(res) }
---