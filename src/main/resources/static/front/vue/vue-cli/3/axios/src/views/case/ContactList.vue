<template>
  <div class="home">
    <!-- 联系人列表 -->
    <van-contact-list
        :list="list"
        @add="onAdd"
        @edit="onEdit"
    />
    <!-- 联系人编辑 -->
    <van-popup v-model="showEdit" position="bottom">
      <van-contact-edit
          is-edit
          show-set-default
          :contact-info="editingContact"
          set-default-label="设为默认联系人"
          @save="onSave"
          @delete="onDelete"
      />
    </van-popup>
  </div>
</template>

<script>
import {ContactList, Toast, ContactEdit, Popup} from 'vant'
import axios from 'axios'

export default {
  name: 'ContactList',
  components: {
    [ContactList.name]: ContactList,
    [ContactEdit.name]: ContactEdit,
    [Popup.name]: Popup
  },
  data () {
    return {
      // {
      //    id: 1,
      //    name: '',
      //    tel: ''
      // }
      list: [],
      // instance 实例
      instance: null,
      // 编辑弹窗的显隐
      showEdit: false,
      // 正在编辑的联系人
      editingContact: {},
      // 控制编辑或新建
      isEdit: false
    }
  },
  created () {
    this.instance = axios.create({
      baseURL: 'http://localhost:9000/api',
      timeout: 1000
    })
    this.instance.get('contactList').then(res => {
      this.list = res.data.data
    }).catch(err => {
      console.log(err)
      Toast('请求失败，请稍后重试')
    })
  },
  methods: {
    // 添加联系人
    onAdd () {
      this.showEdit = true
      this.isEdit = false
    },
    // 编辑联系人
    onEdit (info) {
      this.showEdit = true
      this.isEdit = true
      this.editingContact = info
    },
    // 保存联系人
    onSave () {
      this.showEdit = true
      this.isEdit = false

    },
    // 删除联系人
    onDelete () {

    }
  }
}
</script>
<style scoped>
.van-contafct-list__add {
  z-index: 0;
}
.van-popup {
  height: 100%;
}
</style>
