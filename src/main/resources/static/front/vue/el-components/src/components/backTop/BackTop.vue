<template>
  <div class="pages-content-scroll">
    <!-- target 为最外层容器的类名 -->
    <el-backtop target=".pages-content-scroll"></el-backtop>
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'BackTop'
}
</script>

<style scoped lang="scss">
.pages-content-scroll {
  /* 必须撑满整个屏幕 */
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  overflow-x: hidden;
}
</style>