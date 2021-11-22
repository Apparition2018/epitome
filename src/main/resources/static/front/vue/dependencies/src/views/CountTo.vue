<template>
  <div>
    <h3>
      <a href="https://github.com/PanJiaChen/vue-countTo" target="_blank">CountTo</a>
    </h3>
    <h3>
      <a href="https://panjiachen.github.io/countTo/demo/" target="_blank">CountTo Demo</a>
    </h3>
    <div>
      <countTo class="eg1" :startVal='startVal' :endVal='endVal' :duration='1000'/>
    </div>
  </div>
</template>

<script>
import countTo from 'vue-count-to';

export default {
  name: "CountTo",
  components: {countTo},
  data () {
    return {
      startVal: 0,
      endVal: 100
    }
  }
}
</script>

<style scoped>
.eg1 {
  font-size: 40px;
  color: #30B08F;
  display: block;
  margin: 10px 0;
}
</style>