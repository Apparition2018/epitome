<template>
	<view class="user">
		<view class="top">
			<image src="../../static/image/history.png" mode=""></image>
			<view class="text">浏览历史</view>
		</view>
		<view class="content">
			<view class="row" v-for="item in historyArr">
				<newsBox :item="item" @click.native="goDetail(item)"></newsBox>
			</view>
		</view>
		<view class="nohistory" v-if="!historyArr.length">
			<image src="../../static/image/nodata.png" mode="widthFix"></image>
			<view class="text">暂无浏览记录</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				historyArr: []
			};
		},
		onShow() {
			this.getData();
		},
		methods: {
			goDetail(item) {
				uni.navigateTo({
					url: `/pages/news/detail?cid=${item.classid}&id=${item.id}`
				})
			},
			getData() {
				this.historyArr = uni.getStorageSync('historyArr') || []
			}
		}
	}
</script>

<style lang="scss">
	.user {
		.top {
			padding: 50rpx 0;
			background-color: #f8f8f8;
			color: #555;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;

			image {
				width: 150rpx;
				height: 150rpx;
			}

			.text {
				font-size: 38rpx;
				padding-top: 20rpx;
			}
		}
	}

	.content {
		padding: 30rpx;

		.row {
			border-bottom: 1rpx dotted #efefef;
			padding: 15rpx 0;
		}
	}

	.nohistory {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		image {
			width: 360rpx;
		}

		.text {
			font-size: 26rpx;
			color: #888;
		}
	}
</style>