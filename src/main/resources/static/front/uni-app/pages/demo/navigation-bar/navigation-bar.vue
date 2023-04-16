<template>
	<view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '导航条'
			})
			uni.setNavigationBarColor({
				frontColor: '#ffffff',
				backgroundColor: '#000000'
			})
			uni.showNavigationBarLoading();
			setTimeout(()=>{
				uni.hideNavigationBarLoading()
			}, 1500)
		}
	}
</script>

<style>

</style>
