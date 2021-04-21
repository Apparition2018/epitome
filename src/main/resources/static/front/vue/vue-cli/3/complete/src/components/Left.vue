<template>
  <div>
    <div class="title">热门推荐</div>
    <ul class="menu">
      <li @click="menu1">笔记本电脑</li>
      <li @click="menu2">手机</li>
      <li>笔记本电脑</li>
      <li>手机</li>
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
import Msg from './msg'
export default {
  name: "Left",
  methods: {
    menu1: () => {
      Msg.$emit("val", "1")
    },
    menu2: () => {
      Msg.$emit("val", "2")
    }
  }
}
</script>

<style scoped>
.title {
  width: 100px;
  color: red;
}

.menu {
  padding: 0;
}

.menu li {
  list-style: none;
  height: 50px;
  line-height: 50px;
  margin-bottom: 2px;
  background-color: white;

}
</style>