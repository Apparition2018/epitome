<template>
  <div>
    {{ kk }}
  </div>
</template>

<script>
import Msg from './msg'
export default {
  name: "Right",
  data () {
    return {
      kk: 0
    }
  },
  mounted: () => {
    const _this = this
    Msg.$on('val', (m) => {
      _this.kk = m
    })
  }
}
</script>

<style scoped>

</style>