# 低代码自动化

---
## AI 体系
- 低代码：Coze, n8n, Dify, Make

    | Platform     | Coze | n8n | Dify | Make |
    |--------------|:----:|:---:|:----:|:----:|
    | Local Deploy |  ×   |  √  |  √   |  ×   |
    | Ecosystemm   |  B   |  B  |  C   |  A   |
    | Easy To Use  |  B   |  B  |  B   |  A   |
- 大模型：LangChain, LangGraph
---
## Coze
- 产品生态：
    1. [扣子开发平台](https://code.coze.cn/home)
    2. [扣子空间](https://www.coze.cn/space-preview)
    3. [扣子罗盘](https://loop.coze.cn/console/enterprise/personal/space)
    4. [Eino框架](https://github.com/cloudwego/eino)
- 提示词：Coze Star

    | 模块        | 说明       | 示例                                     |
    |:----------|:---------|:---------------------------------------|
    | Context   | 任务背景与上下文 | 你是电商客服，需解答用户关于iPhone15的咨询，知识库包含最新价格和库存 |
    | Objective | 核心目标     | 准确回答价格、发货时间，推荐适配配件                     |
    | Steps     | 执行步骤     | 1.识别用户问题类型；2.检索知识库；3.用亲切语气整理回复         |
    | Tone      | 语言风格     | 口语化，避免专业术语，使用亲~、呢等语气词                  |
    | Audience  | 目标用户     | 20-35岁年轻消费者，对价格敏感，关注性价比                |
    | Response  | 输出格式     | 价格：XXX元\n库存：XXX件\n推荐配件：XXX（链接）         |
- 资源：
    - 插件
    - 知识库
    - 数据库
### [实战一：成语接龙](https://www.bilibili.com/video/BV1tWorBTEKm?p=53)

### Reference
- [Coze 从入门到精通](https://www.bilibili.com/video/BV1tWorBTEKm/)
---
## [n8n](https://docs.n8n.io/)
- [docker](./n8n)
### [Built-in nodes](https://docs.n8n.io/integrations/builtin/node-types/)
- NASA：[Generate API Key](https://api.nasa.gov/)
- PostBin：[Create bin](https://www.postb.in/)
- Airtable：[在线可视化数据库](https://airtable.com/)
- 反扒 Refer
> Reference：https://cloud.fynote.com/share/md/YJHGHAgMqE
---
