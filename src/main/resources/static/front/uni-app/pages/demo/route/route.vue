<template>
	<view>
		<button type="default" @click="goto('/pages/demo/route/route2?name=mary&age=123')">route2</button>
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
