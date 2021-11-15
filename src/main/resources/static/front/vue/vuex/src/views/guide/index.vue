<template>
  <div>
    <div>Guide</div>
    <div>{{ count }}</div>
    <div>{{ count2 }}</div>
    <div>{{ count3 }}</div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "index",
  computed: mapState({
    count: state => state.count,
    count2: 'count',
    count3 (state) {
      return state.count
    }
  })
}
</script>

<style lang="less">

</style>