# [uni-app](https://uniapp.dcloud.net.cn/)

---
## Reference
- [uniapp 零基础入门到项目打包](https://www.bilibili.com/video/BV1mT411K7nW)
---
## [HBuilder X](https://www.dcloud.io/hbuilderx.html)
### 预设快捷键方案
- 工具 → 预设快捷键方案切换 → Intellij Idea / Webstrom
### 配置服务器
1. 运行 → 运行到浏览器 → 配置web服务器
2. Chrome浏览器安装路径：`C:/Program Files/Google/Chrome/Application/chrome.exe`
3. 微信web开发者工具路径：`D:/Tencent/微信web开发者工具`
    - 微信开发者工具：设置 → 安全 → 服务端口：开
### Settings.json
#### 源码视图
```json5
{
  // 下面两个配置相当与上面的配置服务器
  "browsers.config" : {
    "Chrome.path" : "C:/Program Files/Google/Chrome/Application/chrome.exe"
  },
  "weApp.devTools.path" : "D:/Tencent/微信web开发者工具",
  "editor.colorScheme" : "Monokai",
  "editor.saveOnFocusLost" : true,
  "editor.codeassistKeyTab": true
}
```
---
## easycom
- [pages.json easycom](https://uniapp.dcloud.net.cn/collocation/pages.html#easycom)
- [easycom 组件规范](https://uniapp.dcloud.net.cn/component/#easycom%E7%BB%84%E4%BB%B6%E8%A7%84%E8%8C%83)
---
## 组件
### 自定义事件
- @事件[.native](https://uniapp.dcloud.net.cn/tutorial/vue-components.html#%E5%B0%86%E5%8E%9F%E7%94%9F%E4%BA%8B%E4%BB%B6%E7%BB%91%E5%AE%9A%E5%88%B0%E7%BB%84%E4%BB%B6)：将原生事件绑定到组件
- [.sync](https://uniapp.dcloud.net.cn/tutorial/vue-components.html#sync-%E4%BF%AE%E9%A5%B0%E7%AC%A6)：扩展为一个自动更新父组件属性的 v-on 监听器
---
## 生命周期
- [vue2 生命周期](https://uniapp.dcloud.net.cn/tutorial/vue-api.html#%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)
- [应用生命周期](https://uniapp.dcloud.net.cn/collocation/App.html#applifecycle)
- [页面生命周期](https://uniapp.dcloud.net.cn/tutorial/page.html#lifecycle)
---