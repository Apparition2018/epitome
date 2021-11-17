<template>
  <div>
    <hr>
    <h2>Products</h2>
    <ProductList/>
    <hr>
    <ShoppingCart/>
  </div>
</template>

<script>
import ProductList from '@/components/guide/ProductList.vue'
import ShoppingCart from '@/components/guide/ShoppingCart.vue'

export default {
  name: "ModulesExample",
  components: { ProductList, ShoppingCart }
}
</script>

<style scoped>

</style>