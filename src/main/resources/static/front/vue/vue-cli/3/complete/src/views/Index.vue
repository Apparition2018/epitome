<template>
  <div class="main">
    <div class="left">
      <Left></Left>
    </div>
    <div class="right">
      <div class="top">
        <div class="title">
          Title
        </div>
      </div>
      <div class="bottom">
        <Right></Right>
      </div>
    </div>
  </div>
</template>

<script>
import Left from '@/components/Left'
import Right from '@/components/Right'

export default {
  name: "Index",
  components: {
    Left,
    Right
  }
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}

.main {
  width: 1200px;
  margin: 20px auto;
}

.left {
  width: 100px;
  float: left;
  margin-right: 10px;
}

.right {
  width: 1000px;
  float: left;
  margin-left: 10px;
}

.left, .right {
  background-color: #f5f5f5;
}

.top .title {
  width: 1000px;
  height: 180px;
  line-height: 180px;
  border: #1e1e1f 1px solid;
  background-color: #9f8ece;
}
</style>