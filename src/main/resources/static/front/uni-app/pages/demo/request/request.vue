<template>
	<view>
		<view v-for="img in imgs" :key="img.id">
			<image :src="img.url" mode="aspectFill" @click="getImgSrc"></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgs: []
			}
		},
		methods: {
			getImgSrc() {
				uni.showLoading({
					mask: true
				})
				uni.request({
					url: "https://api.thecatapi.com/v1/images/search",
					data: {
						limit: 1
					},
					success: res => {
						this.imgs = res.data;
					},
					fail: err => {
						console.log(err);
					},
					complete: () => {
						uni.hideLoading();
					}
				})
			}
		},
		onLoad() {
			this.getImgSrc();
		}
	}
</script>

<style>

</style>