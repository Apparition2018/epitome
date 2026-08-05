<template>
  <li class="item" @click="handleDelete">{{ content }}</li>
</template>

<script>
export default {
  props: {
    content: { type: String, required: true },
    index: { type: Number, required: true },
  },
  emits: ['delete'],
  methods: {
    handleDelete() {
      this.$emit('delete', this.index)
    },
  },
}
</script>
<style scoped>
.item {
  color: green;
}
</style>
