<template>
  <div class="home">
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'RequestMethod',
  created () {
    /** get */
    axios.get('/data.json', {
      params: {
        id: 12
      }
    }).then((res) => {
      console.log(res)
    })
    axios({
      method: 'get',
      url: '/data.json',
      params: {
        id: 12
      }
    }).then((res) => {
      console.log(res)
    })

    /** post */
    let data = {
      id: 12
    }
    // application/json
    axios.post('post', data).then(res => {
      console.log(res)
    })
    axios({
      method: 'post',
      url: '/post',
      data: data
    }).then((res) => {
      console.log(res)
    })
    // multipart/form-data 表单提交（图片上传，文件上传）
    let formData = new FormData()
    for (let key in data) {
      formData.append(key, data[key])
    }
    axios.post('/post', formData).then(res => {
      console.log(res)
    })

    /** put */
    axios.put('/put', data).then(res => {
      console.log(res)
    })

    /** patch */
    axios.patch('/patch', data).then(res => {
      console.log(res)
    })

    /** delete */
    // Query String Parameters
    axios.delete('delete', {
      params: {
        id: 12
      }
    }).then(res => {
      console.log(res)
    })
    // application/json
    axios.delete('delete', {
      data: {
        id: 12
      }
    }).then(res => {
      console.log(res)
    })
  }
}
</script>
