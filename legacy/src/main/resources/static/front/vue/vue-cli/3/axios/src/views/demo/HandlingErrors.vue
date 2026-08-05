<template>
  <div class="home">
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HandlingErrors',
  created () {
    // 统一请求错误处理
    let instance = axios.create({})
    instance.interceptors.request.use(config => {
      return config
    }, err => {
      $('#modal').show()
      setTimeout(() => {
        $('#modal').hide()
      }, 2000)
      return Promise.reject(err)
    })
    // 统一响应错误处理
    instance.interceptors.response.use(config => {
      return config
    }, err => {
      $('#modal').show()
      setTimeout(() => {
        $('#modal').hide()
      }, 2000)
      return Promise.reject(err)
    })

    instance.get('/data.json').then(res => {
      console.log(res)
    })
    // 不加 catch() 则使用上面的统一错误处理
    .catch(err => {
      console.log(err)
    })
  }
}
</script>
