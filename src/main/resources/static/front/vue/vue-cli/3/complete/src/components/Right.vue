<template>
  <div>
    <div v-if="current === 1">
      <GoodsList :goodsId="1"></GoodsList>
    </div>
    <div v-else-if="current === 2">
      <GoodsList :goodsId="2"></GoodsList>
    </div>
    <div v-else-if="current === 3">
      <GoodsList></GoodsList>
    </div>
    <div v-else-if="current === 4">
      <GoodsList></GoodsList>
    </div>
    <div v-else>
      <GoodsList :goods-id="0"></GoodsList>
    </div>
  </div>
</template>

<script>
import {EventBus} from '@/api/event-bus'
import GoodsList from "@/components/GoodsList";

export default {
  name: "Right",
  components: {
    GoodsList
  },
  data () {
    return {
      current: 0
    }
  },
  mounted () {
    const _this = this
    EventBus.$on('val', (m) => {
      _this.current = m
    })
  }
}
</script>

<style scoped>

</style>