const Koa = require('koa')
const app = new Koa()
const KoaRouter = require('koa-router')
const koaRouter = new KoaRouter()
const Koa2Cors = require('koa2-cors')
const KoaBody = require('koa-body')
const api = require('./api')

app.use(Koa2Cors({
  origin: function (ctx) {
    return '*' // 允许来自所有域名请求
  },
  exposeHeaders: ['WWW-Authenticate', 'Server-Authorization'],
  maxAge: 5,
  credentials: true,
  allowMethods: ['GET', 'POST', 'DELETE', 'PUT', 'PATCH'],
  allowHeaders: ['Content-Type', 'Authorization', 'Accept']
}))

// logger
app.use(async (ctx, next) => {
  await next()
  const rt = ctx.response.get('X-Response-Time')
  console.log(`${ctx.method} ${ctx.url} - ${rt}`)
})

// x-response-time
app.use(async (ctx, next) => {
  const start = Date.now()
  await next()
  const ms = Date.now() - start
  ctx.set('X-Response-Time', `${ms}ms`)
})

// 装载所有子路由
app.use(KoaBody())

koaRouter.use('/api', api.routes())
// 加载路由中间件
app.use(koaRouter.routes())
app.use(koaRouter.allowedMethods())

// error
// app.on('error', (err, ctx) => {
//     log.error('server error', err, ctx)
// });

app.listen(9000, function () {
  console.log('服务启动成功')
})
