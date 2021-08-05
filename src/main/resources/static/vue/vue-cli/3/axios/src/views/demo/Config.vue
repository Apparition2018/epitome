<template>
  <div class="home">
  </div>
</template>

<script>
// axios 基本配置
import axios from 'axios'

export default {
  name: 'Config',
  created () {
    axios.create({
      baseURL: 'http://localhost:8080', // 请求域名
      timeout: 1000,                    // 超时时长，ms
      url: '/data.json',                // 请求路径
      method: 'get',                    // 请求方法
      headers: {},                      // 请求头
      params: {},                       // URL 参数
      data: {},                         // 请求体参数
    })

    // 1. axios 全局配置
    axios.defaults.baseURL = 'http://localhost:8080'
    axios.defaults.timeout = 1000
    // 2. axios 实例配置
    let instance = axios.create({})
    instance.defaults.timeout = 3000
    // 3. axios 请求配置
    instance.get('/data.json', {
      timeout: 5000
    })
    
    // 实际开发
    // 有两种请求接口：
    // 1. http://localhost:9090
    // 2. http://localhost:9091
    let instance1 = axios.create({
      baseURL: 'http://localhost:9090',
      timeout: 1000
    })
    let instance2 = axios.create({
      baseURL: 'http://localhost:9091',
      timeout: 3000
    })
    instance1.get('/contactList', {
      params: {}
    }).then(res => {
      console.log(res)
    })
    instance2.get('/orderList', {
      timeout: 5000
    }).then(res => {
      console.log(res)
    })
  }
}
</script>
