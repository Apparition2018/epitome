<template>
  <div class="home">
  </div>
</template>

<script>
// axios 实例：后端接口域名有多个，或部分接口配置需求不一致
import axios from 'axios'

export default {
  name: 'AxiosInstance',
  created () {
    let instance = axios.create({
      baseURL: 'http://localhost:8080',
      timeout: 1000
    })
    let instance2 = axios.create({
      baseURL: 'http://localhost:9090',
      timeout: 5000
    })
    instance.get('/data.json').then(res => {
      console.log(res)
    })
  }
}
</script>
