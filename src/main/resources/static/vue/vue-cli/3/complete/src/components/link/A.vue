<template>
  <div>
    <p>我是A......</p>
    <p><router-link to="/link">返回</router-link></p>
    <p><router-link to="/a1">转向A1</router-link></p>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'A'
}
</script>

<style scoped>

</style>
