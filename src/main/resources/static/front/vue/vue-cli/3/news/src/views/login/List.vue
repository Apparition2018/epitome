<template>
  <div>
    <ul>
      <li v-for="(item, index) in pageLists"
          :key="index">
        {{ item.title }}-{{ item.content }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'List',
  computed: {
    pageLists () {
      return this.$store.state.lists
    }
  }
}
</script>

<style scoped>

</style>
