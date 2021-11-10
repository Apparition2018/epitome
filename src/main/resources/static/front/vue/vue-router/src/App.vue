<template>
  <div id="app">
    <div class="nav">
      <router-link to="/dynamic/foo">/dynamic/foo</router-link> |
      <router-link to="/dynamic/bar">/dynamic/bar</router-link>
    </div>
    <div class="nav">
      <router-link to="/nested">/nested</router-link> |
      <router-link to="/nested/1">/nested/1</router-link> |
      <router-link to="/nested/2">/nested/2</router-link>
    </div>
    <div class="nav">
      <router-link :to="{ name: 'NamedRoutes', params: { id: 123 } }">/named-routes</router-link> |
      <router-link to="/named-views">/named-views</router-link>
    </div>
    <div class="nav">
      <router-link to="/redirect">/redirect</router-link> |
      <router-link to="/alias">/alias</router-link>
    </div>
    <div class="nav">
      <router-link to="/props/boolean/Henry">/props/boolean/Henry</router-link> |
      <router-link to="/props/object/static">/props/object/static</router-link> |
      <router-link to="/props/function/Henry">/props/function/Henry</router-link>
    </div>
    <router-view/>
    <router-view name="a"></router-view>
    <router-view name="b"></router-view>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.nav {
  padding: 5px;
}

.nav a {
  font-weight: bold;
  color: #2c3e50;
}

.nav a.router-link-exact-active {
  color: #42b983;
}
</style>
