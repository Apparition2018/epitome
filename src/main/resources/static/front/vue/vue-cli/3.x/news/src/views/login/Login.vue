<template>
  <div>
    <form v-if="!isReg">
      <div>用户名：</div>
      <input type="text" v-model="name">
      <div>密码：</div>
      <input type="text" v-model="password">
      <div>
        <button type="button" @click="login()">登录</button>
        <button type="button" @click="reg()">注册</button>
      </div>
    </form>
    <form v-else>
      <div>用户名：</div>
      <input type="text" v-model="name">
      <div>密码：</div>
      <input type="password" v-model="password">
      <div>再次输入密码：</div>
      <input type="password" v-model="repeat">
      <div>
        <button type="button" @click="addUser()">确定</button>
        <button type="button" @click="cancel()">取消</button>
      </div>
    </form>
  </div>
</template>

<script>
// 课程地址：https://www.imooc.com/learn/1091
// 项目源码：https://github.com/root-lucas/vue-news-info
// 项目演示：https://root-lucas.github.io/vue-news-info/dist/
export default {
  name: 'Login',
  data () {
    return {
      isReg: false,
      name: '',
      password: '',
      repeat: ''
    }
  },
  methods: {
    login () {
      if (localStorage.getItem('name') === this.name &&
        localStorage.getItem('password') === this.password) {
        this.name = ''
        this.password = ''
        this.$router.push('/home/list')
      } else {
        alert('用户名或密码不正确！')
      }
    },
    reg () {
      this.isReg = true
    },
    cancel () {
      this.isReg = false
    },
    addUser () {
      if (this.password === this.repeat) {
        localStorage.setItem('name', this.name)
        localStorage.setItem('password', this.password)
        this.name = ''
        this.password = ''
        this.repeat = ''
        this.isReg = false
      } else {
        alert('两次密码输入输入不一致！')
      }
    }
  }
}
</script>

<style scoped>

</style>
