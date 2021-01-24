<template>
  <div>
    Hello Info Component
    <button type="button" @click="add()">添加</button>
  </div>
</template>

<script>
import store from '@/store/index'

export default {
  name: 'Info',
  store,
  data () {
    return {
      msg: 'Hello Vue'
    }
  },
  mounted () {
    window.vue = this
  },
  methods: {
    add () {
      console.log('add Event from Info!')
      // alert('add Event from Info!')
      // debugger
      store.commit('increase')
    },
    output () {
      console.log('in output')
    }
  }
}
</script>

<style scoped>

</style>
