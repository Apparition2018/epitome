<template>
  <ul>
    <li v-for="product in products" :key="product.id">
      {{ product.title }} - {{ currency(product.price) }}
      <br />
      <button :disabled="!product.inventory" @click="addProductToCart(product)">Add to cart</button>
    </li>
  </ul>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import { currency } from '@/api/currency'

export default {
  name: 'ProductList',
  computed: mapState({
    products: (state) => state.products.all,
  }),
  created() {
    this.$store.dispatch('products/getAllProducts')
  },
  methods: {
    ...mapActions('cart', ['addProductToCart']),
    currency,
  },
}
</script>

<style scoped></style>
