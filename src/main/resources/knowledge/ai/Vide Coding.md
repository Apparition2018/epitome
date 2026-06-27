# Vide Coding

---
## 全局 prompt
1. 回答风格：用中文回答、简洁一点、先给结论、不要长篇解释、技术问题多给示例。
2. 工作方式：改代码前先读项目结构、优先跑测试、不要随便重构、改完总结改了什么。
3. 代码偏好：TypeScript 严格模式、React 用函数组件、Python 用 ruff、提交前跑 pnpm test。
4. 安全边界：不要删除文件、不要执行破坏性命令、不要自动提交 git、需要我确认后再动数据库。
5. 项目协作习惯：遇到不确定先问、能自己查就自己查、尽量给可复制命令、文件路径要写清楚。
---
## [ccx](https://github.com/BenedictKing/ccx)
1. [下载二进制文件 ccx-windows-amd64.exe](https://github.com/BenedictKing/ccx/releases/latest)
2. 在二进制文件旁边创建一个 `.env` 文件
    ```
    PROXY_ACCESS_KEY=your-proxy-access-key
    PORT=3688
    ENABLE_WEB_UI=true
    APP_UI_LANGUAGE=zh-CN
    ```
3. 运行二进制文件并打开 http://localhost:3000
4. 添加渠道：
```
上游模型        OpenAI Chat
Base URL       https://api.deepseek.com
模型重定向      gpt-5.5      →       deepseek-v4-flash
API密钥管理s    sk-65e***884
```
---
## [CC Switch](https://github.com/farion1231/cc-switch)
### ccx
```
API            your-proxy-access-key
API 请求地址    http://localhost:3000/v1
模型名称        gpt-5.5
```
---
## [Agent Skill](https://www.bilibili.com/video/BV1ugdgBQED4/)
1. SKILL.md：开发流程
    ```markdown
    ---
    name: ……
    description: ……
    ---
    ……
    ```
2. references：参考文档
3. scripts：开发工具
4. assets：静态资源
### SKILL Repository
1. [anthropics/skills](https://github.com/anthropics/skills/tree/main/skills)
2. [vercel-labs/agent-skills](https://github.com/vercel-labs/agent-skills)
3. [antfu/skills: Anthony Fu](https://github.com/antfu/skills)
4. [JackyST0/awesome-agent-skills](https://github.com/JackyST0/awesome-agent-skills)
5. [ZhanlinCui/Agent-Skills-Hunter](https://github.com/ZhanlinCui/Agent-Skills-Hunter)
### SKILL Website
1. [SkillsMP](https://skillsmp.com/)
2. [Find awesome Agent Skills](https://agent-skills.md/)
3. [Skills Directory](https://www.skillsdirectory.com/)
4. [Skillstore](https://skillstore.io/zh-hans)
5. [The Agent Skills Directory](https://www.skills.sh)
---
## [Codex](https://openai.com/zh-Hans-CN/codex/)
- [Codex 新手教程](https://www.bilibili.com/video/BV1SjGi6nEZ6/)
- [Codex 新手教程](https://www.bilibili.com/video/BV1Nc5163EA3/)
### Reference
1. 设计文件就用项目
2. 一个任务一个对话
3. 复杂任务先出计划
4. 写清楚什么叫完成
5. 经常提交
6. codex 自我审查
### 记忆
1. 当前项目：根目录创建 AGENTS.md
    - 让 codex 帮忙写：通读当前项目，把你学到的关于项目的信息保存到 AGENTS.md。用中文，格式要清晰
    - 适合写的内容：
        ```
        项目使用的包管理器、测试命令、构建命令
        修改代码后必须执行的验证步骤
        不能触碰的目录、密钥、生成文件或外部系统
        团队约定的代码风格和提交前检查
        特定子目录的例外规则，例如后端、前端、脚本目录各自的验证命令
        ```
    - 不适合写的内容：
        ```
        大段产品背景、历史会议纪要、完整 API 文档
        会频繁变化的临时任务列表
        密钥、账号、Token 或只能给人看的内部凭证
        可以通过命令自动发现的信息，例如完整文件树
        ```
2. 全局：设置 → 个性化 → 自定义指令（全局创建 AGENTS.md）
### Skills
1. 插件 → 技能
2. 第三方/自定义
    1. 下载第三方或自定义 Skills
    2. 新建任意名称文件夹 → 进入并新建 .codex 文件夹 → 进入并新建 skills 文件夹 → 进入并放置 Skills
    3. 添加到
### MCP
- Model Context Protocol
### 设置
#### 常规
- 权限：取消完全访问权限
- 编辑器：
    - 跟进行为：排队
#### 配置
- 沙盒设置：工作区写入
    - 启用允许网络访问
#### 个性化
- 个性：务实
- 自定义指令
- 记忆：启用记忆
#### 钩子
- 每次 Codex 改完文件后，自动跑 formatter
- 每次准备执行命令前，检查命令是否危险
- 每次任务结束后，自动跑测试
- 每次生成补丁后，自动做 lint
---
## [Reasonix](https://esengine.github.io/DeepSeek-Reasonix/)

---
