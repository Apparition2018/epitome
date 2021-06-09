# Node.js

---
## 参考网站
1. [一文看懂npm、yarn、pnpm之间的区别](https://zhuanlan.zhihu.com/p/37653878)
2. [IntelliJ IDEA：npm和Yarn](https://www.w3cschool.cn/intellij_idea_doc/intellij_idea_doc-drm430d9.html)
### npm
>1. [windows安装npm教程](https://www.cnblogs.com/jianguo221/p/11487532.html)
>2. [npm 安装文档 | npm 中文网](http://caibaojian.com/npm/)
>3. [package.json | npm 中文文档](https://www.npmjs.cn/files/package.json/)
>4. [npm 常用命令详解](https://www.cnblogs.com/PeunZhang/p/5553574.html#npm-install)
### yarn
>1. [yarn.lock 是干什么的](https://www.cnblogs.com/yangzhou33/p/11494819.html)
>2. [yarn install 相关命令](https://www.jianshu.com/p/0caebd39ac09)
---
## 常用命令
    node -v

    cnpm -v

    yarn conifg set registry https://registry.npm.taobao.org -g
    yarn config set global-folder "E:\nodejs\yarn_global"
    yarn config set cache-folder "E:\nodejs\yarn_cache"
    yarn config list
    yarn global add @vue/cli
    yarn global remove @vue/cli
    yarn install
    yarn run serve
---
## [npm 命令](https://www.npmjs.cn/cli/run-script/)
    npm -v
    npm config set registry=http://registry.npm.taobao.org
    npm config set prefix "D:\nodejs\node_global"
    npm config set cache "D:\nodejs\node_cache"
    npm config list
    npm root -g
    npm i -g npm
    npm uni -g npm
    npm install -g yarn --registry=https://registry.npm.taobao.org
    npm install -g cnpm --registry=https://registry.npm.taobao.org
    npm uninstall -g cnpm
    npm info yarn
    npm list -g --depth=0
    npm init -y | -f
    npm run-script <command> [--silent] [-- <args>...]                  运行包脚本