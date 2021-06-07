<template>
  <div class="home">
  </div>
</template>

<script>
// 拦截器：在请求或响应被处理前拦截
import axios from 'axios'

export default {
  name: 'Interceptors',
  created () {
    // 1. 请求拦截器
    axios.interceptors.request.use(config => {
      return config
    }, err => {
      return Promise.reject(err)
    })
    // 2. 响应拦截器
    axios.interceptors.response.use(res => {
      return res
    }, err => {
      return Promise.reject(err)
    })
    // 3. 取消拦截器
    let myInterceptor = axios.interceptors.request.use(config => {
      config.headers.auth = true
      return config
    })
    axios.interceptors.request.eject(myInterceptor)

    // 例子 1
    // 需要登录的接口（token: 'xxx'）
    let logged = axios.create({})
    logged.interceptors.request.use(config => {
      config.headers.token = 'xxx'
      return config
    })
    // 不需要登录的接口
    let notLogged = axios.create({})

    // 例子 2
    // 接口请求等待返回样式
    let wait = axios.create({})
    wait.interceptors.request.use(config => {
      $('#wait').show()
      return config
    })
    wait.interceptors.response.use(res => {
      $('#wait').hide()
      return res
    })
  }
}
</script>
