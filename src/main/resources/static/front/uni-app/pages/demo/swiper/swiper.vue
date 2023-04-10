<template>
	<view class="uni-margin-wrap">
		<swiper class="swiper" circular indicator-dots autoplay interval="3000" duration="500">
			<swiper-item>
				<view class="swiper-item uni-bg-red">A</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item uni-bg-green">B</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item uni-bg-blue">C</view>
			</swiper-item>
		</swiper>
	</view>
</template>

<style lang="scss">
	@import '@/static/css/global.css';

	.uni-margin-wrap {
		width: 690rpx;
		width: 100%;

		.swiper {
			height: 300rpx;

			.swiper-item {
				display: block;
				height: 300rpx;
				line-height: 300rpx;
				text-align: center;
			}
		}
	}
</style>