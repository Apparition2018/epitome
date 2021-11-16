<template>
  <div>
    <h2>Guide</h2>
    <div class="nav">
      <router-link to="/guide/state/1">/state/1</router-link> |
      <router-link to="/guide/state/2">/state/2</router-link> |
      <router-link to="/guide/state/3">/state/3</router-link> |
      <router-link to="/guide/state/4">/state/4</router-link>
    </div>
    <div class="nav">
      <router-link to="/guide/getters/1">/getters/1</router-link>
    </div>
    <div class="nav">
      <router-link to="/guide/mutations/1">/mutations/1</router-link>
    </div>
    <div class="nav">
      <router-link to="/guide/actions/1">/actions/1</router-link>
    </div>
    <div class="nav">
      <router-link to="/guide/modules/1">/modules/1</router-link> |
      <router-link to="/guide/modules/2">/Shopping Cart Example</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
</script>

<style lang="less">

</style>