<template>
  <view />
</template>

<script>
export default {
  data () {
    return {}
  },
  onReady () {
    uni.setNavigationBarTitle({
      title: '导航条'
    })
    uni.setNavigationBarColor({
      frontColor: '#fff',
      backgroundColor: '#000'
    })
    uni.showNavigationBarLoading()
    setTimeout(() => {
      uni.hideNavigationBarLoading()
    }, 1500)
  }
}
</script>

<style></style>
