<template>
	<!-- vue2 组件：https://uniapp.dcloud.net.cn/tutorial/vue-components.html -->
	<view class="uniTitle">
		<view class="time">{{time}}</view>
		<view class="big">{{title}}</view>
		<view class="small">{{subtitle}}</view>
		<view class="list">{{list}}</view>
		<view class="user">{{user}}</view>
		<view class="line"></view>
	</view>
</template>

<script>
	export default {
		name: "uniTitle",
		// props: ["title", "subtitle"],
		props: {
			title: {
				type: String,
				default: "title"
			},
			subtitle: {
				type: String,
				default: "subtitle"
			},
			time: {
				type: Number,
				default: Date.now()
			},
			list: {
				type: Array,
				default () {
					return [1, 2, 3]
				}
			},
			user: {
				type: Object,
				default () {
					return {
						name: "Mary",
						pwd: "123"
					}
				}
			}
		},
		data() {
			return {};
		}
	}
</script>

<style lang="scss">
	.uniTitle {
		padding: 60rpx 30rpx;
		text-align: center;
		background-color: #b9edf8;

		.big {
			font-size: 50rpx;
			font-weight: 600;
			color: #1f6ed4;
		}

		.small {
			font-size: 28rpx;
			color: #888;
		}

		.line {
			display: inline-block;
			width: 100rpx;
			height: 10rpx;
			background-color: #0000A1;
			border-radius: 10rpx;
		}
	}
</style>