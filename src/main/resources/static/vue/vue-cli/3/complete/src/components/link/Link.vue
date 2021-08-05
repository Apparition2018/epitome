<template>
  <div>
    <router-link to="/a">转向A页面</router-link>|
    <router-link to="/b">转向B页面</router-link>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'First'
}
</script>

<style scoped>

</style>
