<script setup>
import { ref } from 'vue'

// 消息质量取值数组
const qosList = [0, 1, 2]

// 定义链接参数的对象
const connectionInfo = ref({
  protocol: 'ws',
  host: '192.168.119.128',
  port: 8083,
  clientId: 'emqx_vue_client_' + crypto.randomUUID(),
  username: 'Henry',
  password: '123',
})
</script>

<template>
  <div class="mqtt-demo">
    <el-card>
      <h1>配置信息</h1>
      <el-form label-position="top">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item prop="protocol" label="选择协议">
              <el-select v-model="connectionInfo.protocol">
                <el-option label="ws://" value="ws" />
                <el-option label="wss://" value="wss" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item prop="host" label="主机地址">
              <el-input v-model="connectionInfo.host" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item prop="port" label="端口号">
              <el-input v-model="connectionInfo.port" type="number" placeholder="8083/8084" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item v-model="connectionInfo.clientId" prop="clientId" label="客户端ID">
              <el-input />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item v-model="connectionInfo.username" prop="username" label="用户名">
              <el-input />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item v-model="connectionInfo.password" prop="password" label="密码">
              <el-input />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-button type="primary"> 建立连接 </el-button>
            <el-button type="danger"> 断开连接 </el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card>
      <h1>订阅主题</h1>
      <el-form label-position="top">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item prop="topic" label="Topic">
              <el-input />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="qos" label="QoS">
              <el-select>
                <el-option v-for="qos in qosList" :key="qos" :label="qos" :value="qos" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button type="primary" class="sub-btn"> 订阅主题 </el-button>
            <el-button type="primary" class="sub-btn"> 取消订阅 </el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <el-card>
      <h1>发布消息</h1>
      <el-form label-position="top">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item prop="topic" label="Topic">
              <el-input />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="payload" label="Payload">
              <el-input />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="qos" label="QoS">
              <el-select>
                <el-option v-for="qos in qosList" :key="qos" :label="qos" :value="qos" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-col :span="24" class="text-right">
        <el-button type="primary"> 发布消息 </el-button>
      </el-col>
    </el-card>

    <el-card>
      <h1>接收到的消息</h1>
      <el-col :span="24">
        <el-input type="textarea" :rows="3" readonly />
      </el-col>
    </el-card>
  </div>
</template>

<style>
.mqtt-demo {
  max-width: 1200px;
  margin: 32px auto 0 auto;
}

h1 {
  font-size: 16px;
  margin-top: 0;
}

.el-card {
  margin-bottom: 32px;
}
.el-card__body {
  padding: 24px;
}

.el-select {
  width: 100%;
}

.text-right {
  text-align: right;
}

.sub-btn {
  margin-top: 30px;
}
</style>
