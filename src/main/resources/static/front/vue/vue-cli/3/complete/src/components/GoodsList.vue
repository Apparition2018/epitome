<template>
  <div>
    <ul class="goodsList">
      <li v-for="goods in goodsList">
        <div class="image">Image</div>
        <p>{{ goods.goodsName }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "GoodsList",
  props: {
    goodsId: Number
  },
  data () {
    return {
      goodsList: []
    }
  },
  mounted () {
    this.goodsIdChangeEvent()
  },
  watch: {
    goodsId () {
      this.goodsIdChangeEvent()
    }
  },
  methods: {
    goodsIdChangeEvent () {
      let url = ""
      if (this.goodsId === 1 || this.goodsId === 0) {
        url = "json/laptop.json"
      } else if (this.goodsId === 2) {
        url = "json/mobile.json"
      }
      let _this = this
      this.$axios.get(url).then((res) => {
        _this.goodsList = res.data
      })
    }
  }
}
</script>

<style scoped>
.goodsList {
  padding: 0;
  list-style-type: none;
}

.goodsList li {
  margin-bottom: 35px;
  width: 200px;
  height: 200px;
  float: left;
}

.image {
  display: inline-block;
  width: 180px;
  height: 180px;
  line-height: 180px;
  border: #1e1e1f 1px solid;
  background-color: #a8d097;
}
</style>