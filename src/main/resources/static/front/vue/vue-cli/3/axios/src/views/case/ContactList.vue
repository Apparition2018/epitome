<template>
  <div class="home">
    <van-button type="default">默认按钮</van-button>
  </div>
</template>

<script>
import {Button} from 'vant'

export default {
  name: 'ContactList',
  comments: {
    [Button.name]: Button
  },
  created () {
  }
}
</script>
