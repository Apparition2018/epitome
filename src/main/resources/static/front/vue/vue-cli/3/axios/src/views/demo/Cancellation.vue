<template>
  <div class="home">
  </div>
</template>

<script>
// 取消请求：用于取消正在进行的请求
import axios from 'axios'

export default {
  name: 'Cancellation',
  created () {
    let source = axios.CancelToken.source()
    axios.get('/data.json', {
      cancelToken: source.token
    }).then(res => {
      console.log(res)
    }).catch(err => {
      console.log(err)
    })
    // 取消请求
    source.cancel('cancel http')
  }
}
</script>
