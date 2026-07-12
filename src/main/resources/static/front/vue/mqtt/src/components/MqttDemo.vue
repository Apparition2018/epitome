<script setup>
import { ref } from 'vue'
import mqtt from 'mqtt'

// 消息质量取值数组
const qosList = [0, 1, 2]

// 定义链接参数的对象
const connectionInfo = ref({
  protocol: 'ws',
  host: '192.168.119.128',
  port: 8083,
  clientId: 'emqx_vue_client_' + Math.random().toString().substring(2, 8),
  username: 'ljh',
  password: '123',
})

// 建立链接
const clientInitData = ref({
  connected: false,
})
const client = ref(null)
const createConnection = () => {
  const { protocol, host, port, ...options } = connectionInfo.value
  const connectionUrl = `${protocol}://${host}:${port}/mqtt`
  client.value = mqtt.connect(connectionUrl, options)
  client.value.on('connect', () => {
    clientInitData.value.connected = true
    console.info('链接建立成功')
  })
  client.value.on('message', (topic, message) => {
    receiveMessages.value = topic + '---->' + message.toString()
  })
  client.value.on('error', (err) => {
    console.error('链接建立失败：', err)
  })
}

// 断开链接
const closeConnection = () => {
  client.value.end(false, () => {
    clientInitData.value.connected = false
    console.info('链接断开了')
  })
}

// 主题对象
const subscriptionInfo = ref({
  topic: '',
  qos: 0,
})

// 接收到的消息
const receiveMessages = ref('')

// 订阅主题
const subscribeInitData = ref({
  subscribed: false,
})
const subscribeTopicHandler = () => {
  const { topic, qos } = subscriptionInfo.value
  client.value.subscribe(topic, { qos }, (err, res) => {
    if (err) {
      console.error('订阅主题失败：', err)
      return
    }
    subscribeInitData.value.subscribed = true
  })
}

// 取消订阅
const unSubscribeTopicHandler = () => {
  const { topic, qos } = subscriptionInfo.value
  client.value.unsubscribe(topic, { qos }, (error, res) => {
    if (error) {
      console.error('取消订阅失败：', error)
      return
    }
    subscribeInitData.value.subscribed = false
  })
}

// 消息对象
const publishInfo = ref({
  topic: '',
  qos: 0,
  payload: '',
})

// 发送消息
const doPublish = () => {
  const { topic, qos, payload } = publishInfo.value
  client.value.publish(topic, payload, { qos }, (error, res) => {
    if (error) {
      console.error('发送消息失败：', error)
      return
    }
    console.info('发送消息成功：', res)
  })
}
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
            <el-form-item prop="clientId" label="客户端ID">
              <el-input v-model="connectionInfo.clientId" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item prop="username" label="用户名">
              <el-input v-model="connectionInfo.username" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item prop="password" label="密码">
              <el-input v-model="connectionInfo.password" />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-button
              type="primary"
              :disabled="clientInitData.connected"
              @click="createConnection"
            >
              建立连接
            </el-button>
            <el-button type="danger" :disabled="!clientInitData.connected" @click="closeConnection">
              断开连接
            </el-button>
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
              <el-input v-model="subscriptionInfo.topic" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="qos" label="QoS">
              <el-select v-model="subscriptionInfo.qos">
                <el-option v-for="qos in qosList" :key="qos" :label="qos" :value="qos" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button
              type="primary"
              class="sub-btn"
              :disabled="!clientInitData.connected || subscribeInitData.subscribed"
              @click="subscribeTopicHandler"
            >
              订阅主题
            </el-button>
            <el-button
              type="primary"
              class="sub-btn"
              :disabled="!clientInitData.connected || !subscribeInitData.subscribed"
              @click="unSubscribeTopicHandler"
            >
              取消订阅
            </el-button>
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
              <el-input v-model="publishInfo.topic" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="payload" label="Payload">
              <el-input v-model="publishInfo.payload" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="qos" label="QoS">
              <el-select v-model="publishInfo.qos">
                <el-option v-for="qos in qosList" :key="qos" :label="qos" :value="qos" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-col :span="24" class="text-right">
        <el-button
          type="primary"
          :disabled="!clientInitData.connected || !subscribeInitData.subscribed"
          @click="doPublish"
        >
          发布消息
        </el-button>
      </el-col>
    </el-card>

    <el-card>
      <h1>接收到的消息</h1>
      <el-col :span="24">
        <el-input v-model="receiveMessages" type="textarea" :rows="3" readonly />
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
