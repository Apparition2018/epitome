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
import {ContactEdit, ContactList, Popup, Toast} from 'vant'

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
      // 编辑弹窗的显隐
      showEdit: false,
      // 正在编辑的联系人
      editingContact: {},
      // 控制编辑或新建
      isEdit: false
    }
  },
  created () {
    this.getList()
  },
  methods: {
    // 获取联系人列表
    async getList () {
      let res = await this.$Http.getContactList()
      this.list = res.data
    },
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
    async onSave (info) {
      if (this.isEdit) {
        // 编辑保存
        let res = await this.$Http.editContact(info)
        if (res.code === 200) {
          await this.getList()
          Toast('编辑成功')
          this.showEdit = false
        }
      } else {
        // 新建保存
        let res = await this.$Http.newContactJson(info)
        if (res.code === 200) {
          await this.getList()
          Toast('新建成功')
          this.showEdit = false
        }
      }
    },
    // 删除联系人
    async onDelete (info) {
      let res = await this.$Http.deleteContact({
        id: info.id
      })
      if (res.code === 200) {
        await this.getList()
        Toast('删除成功')
        this.showEdit = false
      }
    }
  }
}
</script>
<style scoped>
.van-contact-list__add {
  z-index: 0;
}

.van-popup {
  height: 100%;
}
</style>
