# Axios
- 基于 Promise 的 HTTP 库
- 可用于浏览器和 node.js 
---
## 参考网站
1. axios - npm(https://www.npmjs.com/package/axios)
2. [axios在vue中的使用-慕课网](https://www.imooc.com/learn/1152)
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
## 请求方法
1. get
```javascript
axios.get('/get', { params: { id: 12 }}).then(res => {})
```
2. post
```javascript
let data = { id: 12 }
// application/json
axios.post('/post', data).then(res => {})
// multipart/form-data
let formData = new FormData()
for (let key in data) {
    formData.append(key, data[key])
}
axios.post('/post', formData).then(res => {})
```
3. put/patch
```javascript
axios.put('/put', { id: 12 }).then(res => {})
```
3. delete
```javascript
// Query String Parameters
axios.delete('/delete', { params: { id: 12 }}).then(res => {})
// application/json
axios.delete('/delete', { data: { id: 12 }}).then(res => {})
```
---
## [并发](https://www.npmjs.com/package/axios#concurrency-deprecated)
- 同时进行多个请求，并统一处理返回值
- 已过时，使用 Promise.all 代替此功能
```javascript
axios.all([axios.get('/data.json'), axios.get('/city.json')]).then(
    axios.spread((dataRes, cityRes) => {})
)
```
---
## [创建实例](https://www.npmjs.com/package/axios#creating-an-instance)
- 后端接口域名有多个，或部分接口配置需求不一致
```javascript
let instance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000
})
let instance2 = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000
})
instance.get('/data.json').then(res => {})
instance2.get('/city.json').then(res => {})
 ```
---
## [配置](https://www.npmjs.com/package/axios#request-config)
```
baseURL                     请求域名
timeout                     超时时长，ms
url                         请求路径
method                      请求方法
headers                     请求头
params                      URL 参数
data                        请求体参数
```
```javascript
// 1. axios 全局配置
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.timeout = 1000
// 2. axios 实例配置
let instance = axios.create({
    timeout: 2000
})
instance.defaults.timeout = 3000
// 3. axios 请求配置
instance.get('/data.json', {
    timeout: 5000
})
```
---
## [拦截器](https://www.npmjs.com/package/axios#interceptors)
```javascript
// 1. 请求拦截器
axios.interceptors.request.use(config => { return config }, err => { return Promise.reject(err) })
// 2. 响应拦截器
axios.interceptors.response.use(res => { return res }, err => { return Promise.reject(err) })
// 3. 取消拦截器
let myInterceptor = axios.interceptors.request.use(config => {
    config.headers.auth = true
    return config
})
axios.interceptors.request.eject(myInterceptor)
```
---
## 封装 axios
1. @see ruoyi-ui#@/utils/request
2. @see axios#@/service
---
## 其它
1. [错误处理](https://www.npmjs.com/package/axios#handling-errors)
2. [取消请求](https://www.npmjs.com/package/axios#cancellation)
---
## [Vant](https://youzan.github.io/vant/#/zh-CN/)
```
npm i vant -S
npm i babel-plugin-import -D
```
```javascript
# babel.config.js
module.exports = {
    plugins: [
        ['import', {
            libraryName: 'vant',
            libraryDirectory: 'es',
            style: true
        }, 'vant']
    ]
};

# Xxx.vue
import {Button} from 'vant'
export default {
    name: 'Xxx',
    comments: {
        [Button.name]: Button
    }
}
```
---