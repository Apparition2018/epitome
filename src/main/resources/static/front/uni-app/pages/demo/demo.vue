<template>
	<view>
		<button type="default" @click="goto('/pages/demo/swiper/swiper')">swiper</button>
		<button type="default" @click="goto('/pages/demo/scroll-view/scroll-view')">scroll-view</button>
		<button type="default" @click="goto('/pages/demo/nav/nav')">nav</button>
		<button type="default" @click="goto('/pages/demo/components/components')">components</button>
		<button type="default" @click="goto('/pages/demo/route/route')">route</button>
		<button type="default"
			@click="goto('/pages/demo/interaction-feedback/interaction-feedback')">interaction-feedback</button>
		<button type="default" @click="goto('/pages/demo/navigation-bar/navigation-bar')">navigation-bar</button>
		<button type="default" @click="goto('/pages/demo/request/request')">request</button>
		<button type="default" @click="goto('/pages/demo/storage/storage')">storage</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		},
		methods: {
			goto(url) {
				uni.navigateTo({
					url: url
				})
			}
		},
		onReady() {
			uni.showTabBarRedDot({
				index: 2
			})
		}
	}
</script>

<style lang="scss">

</style>