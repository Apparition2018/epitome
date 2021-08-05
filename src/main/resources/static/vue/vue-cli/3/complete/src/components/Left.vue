<template>
  <div>
    <div class="title">热门推荐</div>
    <ul class="menu">
      <li @click="menu1">笔记本电脑</li>
      <li @click="menu2">手机</li>
      <li @click="menu3">笔记本电脑</li>
      <li @click="menu4">手机</li>
      <li>笔记本电脑</li>
      <li>手机</li>
      <li>笔记本电脑</li>
      <li>手机</li>
      <li>笔记本电脑</li>
      <li>手机</li>
    </ul>
  </div>
</template>

<script>
import {EventBus} from '@/api/event-bus'

export default {
  name: "Left",
  methods: {
    menu1: () => {
      EventBus.$emit("val", 1)
    },
    menu2: () => {
      EventBus.$emit("val", 2)
    },
    menu3: () => {
      EventBus.$emit("val", 3)
    },
    menu4: () => {
      EventBus.$emit("val", 4)
    }
  }
}
</script>

<style scoped>
.title {
  width: 100px;
  height: 50px;
  line-height: 50px;
  color: red;
}

.menu {
  padding: 0;
  margin: 0;
}

.menu li {
  list-style: none;
  height: 50px;
  line-height: 50px;
  margin-bottom: 2px;
  background-color: white;
  cursor: pointer;
}
</style>