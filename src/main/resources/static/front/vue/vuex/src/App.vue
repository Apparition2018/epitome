<template>
  <div id="app">
    <div id="guide">
      <router-link to="/guide">Guide</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#guide {
  position: absolute;
  top: 53px;
  right: 53px;
  z-index: 1;

  a {
    color: black;
    font-weight: bold;
    text-decoration: none;
  }
}
</style>
