<template>
	<view class="out">
		<navigator url="/pages/index/index">首页</navigator>
		<navigator url="/pages/list/list">新闻列表</navigator>
		<view class="box" :style="{'background-color': bgColor}" @click="changeBgColor"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				bgColor: 'yellow'
			}
		},
		methods: {
			changeBgColor() {
				this.bgColor = 'purple'
			}
		}
	}
</script>

<style lang="scss">
	.box {
		width: 200rpx;
		height: 200rpx;
		background-color: pink;
	}
</style>