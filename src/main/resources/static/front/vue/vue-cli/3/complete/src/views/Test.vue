<template>
<!--  <template v-if="this.type === 'name'">-->
    <label key="name-label">用户名：</label>
    <input placeholder="输入用户名" key="name-input">
<!--  </template>-->
<!--  <template v-else>-->
    <label key="mail-label">邮箱：</label>
    <input placeholder="输入邮箱" key="mail-input">
<!--  </template>-->
  <button @click="handleToggleClick">切换输入类型</button>
</template>

<script>
export default {
  name: "Test.vue",
  data () {
    return {
      type: 'name'
    }
  },
  methods: {
    handleToggleClick: () => {
      console.log(1)
      console.log(this.type)
      this.type = this.type === 'name' ? 'mail' : 'name'
    }
  }
}
</script>

<style scoped>

</style>