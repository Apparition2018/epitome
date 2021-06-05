<template>
  <div class="home">
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'RequestMethod',
  components: {},
  created () {
    // http://localhost:8080/data.json?id=12
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
  }
}
</script>
