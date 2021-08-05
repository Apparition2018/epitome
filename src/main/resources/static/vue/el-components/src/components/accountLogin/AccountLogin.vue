<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-form-item prop="username">
        <el-input
            v-model="ruleForm.username"
            placeholder="请输入用户名">
          <i slot="prefix" class="el-icon-user"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            v-model="ruleForm.password"
            placeholder="请输入密码"
            show-password>
          <i slot="prefix" class="el-icon-lock"></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
            @click="login"
            type="primary"
            style="width: 100%;">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'AccountLogin',
  props: {
    ruleForm: {
      type: Object,
      required: true
    },
    rules: {
      type: Object,
      required: true
    }
  },
  methods: {
   login() {
     this.$refs.ruleForm.validate((valid) => {
       if (valid) {
         this.$emit('submit')
       } else {
         this.$emit('errHandle')
       }
     })
   } 
  }
}
</script>

<style scoped>

</style>