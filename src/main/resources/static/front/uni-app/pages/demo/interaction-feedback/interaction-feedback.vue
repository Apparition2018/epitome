<template>
	<view>
		<button @click="showToast">showToast</button>
		<button @click="showLoading">showLoading</button>
		<button @click="showModal">showModal</button>
		<button @click="showActionSheet">showActionSheet</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {
			showToast() {
				uni.showToast({
					title: 'showToast',
					icon: 'none',
					mask: true
				})
			},
			showLoading() {
				uni.showLoading({
					title: 'loading',
					mask: true
				});
				setTimeout(() => {
					uni.hideLoading();
				}, 1500)
			},
			showModal() {
				uni.showModal({
					title: 'modal',
					content: 'content'
				})
			},
			showActionSheet() {
				uni.showActionSheet({
					itemList: ['A', 'B', 'C'],
					success: function(res) {
						console.log('选中了第' + (res.tapIndex + 1) + '个按钮');
					},
					fail: function(res) {
						console.log(res.errMsg);
					}
				});
			}
		}
	}
</script>

<style>
</style>