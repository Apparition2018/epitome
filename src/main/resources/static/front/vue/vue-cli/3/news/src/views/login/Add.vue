<template>
  <div>
    <p><label for="title">标题</label></p>
    <input id="title" type="text" v-model="title"/>
    <p><label for="content">内容</label></p>
    <textarea id="content" type="text" v-model="content"/>
    <p class="btn" @click="add()">添加</p>
  </div>
</template>

<script>
export default {
  name: 'Add',
  data () {
    return {
      title: '',
      content: ''
    }
  },
  methods: {
    add () {
      this.$store.commit('addItem', {
        title: this.title,
        content: this.content
      })
      this.title = ''
      this.content = ''
      this.$router.push('/home/list')
    }
  }
}
</script>

<style scoped>

</style>
