<template>
	<view class="home">
		<scroll-view scroll-x class="scrollNav">
			<view class="item" :class="index==navIndex ? 'active' : ''" v-for="(item, index) in navArr"
				@click="clickNav(index, item.id)" :key="item.id">
				{{item.classname}}
			</view>
		</scroll-view>
		<view class="content">
			<view class="row" v-for="item in newsArr">
				<newsBox :item="item" @click.native="goDetail(item)"></newsBox>
			</view>
		</view>
		<view class="nodata" v-if="!newsArr.length">
			<image src="../../static/image/nodata.png" mode="widthFix"></image>
		</view>
		<view class="loading" v-if="newsArr.length">
			<view v-if="loading==1">数据加载中……</view>
			<view v-if="loading==2">没有更多了&gt;_&lt;</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navIndex: 0,
				navArr: [],
				newsArr: [],
				curPage: 1,
				// 0 默认，1 加载中，2 没有更多了
				loading: 0,
			};
		},
		onLoad() {
			this.getNavData();
			this.getNewsData();
		},
		onReachBottom() {
			if (this.loading == 2) {
				return;
			}
			this.curPage++;
			this.loading = 1;
			this.getNewsData();
		},
		methods: {
			reset() {
				this.curPage = 1;
				this.newsArr = [];
				this.loading = 0;
			},
			clickNav(index, id) {
				this.navIndex = index;
				this.reset();
				this.getNewsData(id);
			},
			goDetail(item) {
				uni.navigateTo({
					url: `/pages/news/detail?cid=${item.classid}&id=${item.id}`
				})
			},
			getNavData() {
				uni.request({
					url: 'https://ku.qingnian8.com/dataApi/news/navlist.php',
					success: res => {
						this.navArr = res.data;
					}
				})
			},
			getNewsData(cid = 50) {
				uni.request({
					url: 'https://ku.qingnian8.com/dataApi/news/newslist.php',
					data: {
						page: this.curPage,
						num: 8,
						cid: cid
					},
					success: res => {
						if (res.data.length == 0) {
							this.loading = 2;
						}
						this.newsArr = [...this.newsArr, ...res.data];
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.scrollNav {
		height: 100rpx;
		background-color: #f7f8fa;
		white-space: nowrap;
		position: fixed;
		top: var(--window-top);
		left: 0;
		z-index: 10;

		/deep/ ::-webkit-scrollbar {
			width: 4px !important;
			height: 1px !important;
			overflow: auto !important;
			background: transparent !important;
			-webkit-appearance: auto !important;
			display: block;
		}

		.item {
			font-size: 40rpx;
			display: inline-block;
			line-height: 100rpx;
			padding: 0 30rpx;
			color: #333;

			&.active {
				color: #4e63dd;
			}
		}
	}

	.content {
		padding: 30rpx;
		padding-top: 130rpx;

		.row {
			border-bottom: 1rpx dotted #efefef;
			padding: 15rpx 0;
		}
	}

	.nodata {
		display: flex;
		justify-content: center;
		align-items: center;

		image {
			width: 360rpx;
		}
	}

	.loading {
		text-align: center;
		font-size: 26rpx;
		color: #888;
		line-height: 2em;
	}
</style>