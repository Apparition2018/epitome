<template>
	<view>
		<scroll-view scroll-y class="scroll-Y">
			<view id="demo1" class="scroll-view-item uni-bg-red">A</view>
			<view id="demo2" class="scroll-view-item uni-bg-green">B</view>
			<view id="demo3" class="scroll-view-item uni-bg-blue">C</view>
		</scroll-view>
	</view>
</template>

<style lang="scss">
	@import '@/static/css/global.css';

	.scroll-Y {
		height: 300rpx;

		.scroll-view-item {
			height: 300rpx;
			line-height: 300rpx;
			text-align: center;
			font-size: 36rpx;
		}
	}
</style>