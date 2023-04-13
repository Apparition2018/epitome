<template>
	<view>
		<button type="default" @click="goto('/pages/demo/swiper/swiper')">swiper</button>
		<button type="default" @click="goto('/pages/demo/scroll-view/scroll-view')">scroll-view</button>
		<button type="default" @click="goto('/pages/demo/nav/nav')">nav</button>
		<button type="default" @click="goto('/pages/demo/components/components')">components</button>
		<button type="default" @click="goto('/pages/demo/route/route')">route</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			};
		},
		methods: {
			goto(url) {
				uni.navigateTo({
					url: url
				})
			}
		}
	}
</script>

<style lang="scss">

</style>