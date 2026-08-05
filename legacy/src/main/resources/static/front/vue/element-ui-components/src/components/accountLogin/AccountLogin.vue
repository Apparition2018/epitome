<template>
  <div>
    <el-form ref="ruleForm" :model="form" :rules="rules">
      <el-form-item prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名">
          <template #prefix>
            <i class="el-icon-user"></i>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password" placeholder="请输入密码" show-password>
          <template #prefix>
            <i class="el-icon-lock"></i>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="login"> 登录 </el-button>
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
      required: true,
    },
    rules: {
      type: Object,
      required: true,
    },
  },
  emits: ['submit', 'errHandle'],
  data() {
    return {
      form: { ...this.ruleForm },
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
    },
  },
}
</script>

<style scoped></style>
