<template>
	<view class="detail">
		<view class="title">{{detail.title}}</view>
		<view class="info">
			<view class="author">编辑：{{detail.author}}</view>
			<view class="time">发布日期：{{detail.posttime}}</view>
		</view>
		<view class="content">
			<rich-text :nodes="detail.content"></rich-text>
		</view>
		<view class="desc">免责声明：……</view>
	</view>
</template>

<script>
	import {parseTime} from '@/utils/tool.js'
	export default {
		data() {
			return {
				option: null,
				detail: {}
			};
		},
		onLoad(option) {
			this.option = option;
			this.getDetail();
		},
		methods: {
			getDetail() {
				uni.request({
					url: 'https://ku.qingnian8.com/dataApi/news/detail.php',
					data: this.option,
					success: res => {
						res.data.posttime = parseTime(res.data.posttime);
						res.data.content = res.data.content.replace(/<img/gi, '<img style="max-width:100%"');
						this.detail = res.data;
						this.addHistory();
						uni.setNavigationBarTitle({
							title: this.detail.title
						})
					}
				})
			},
			addHistory() {
				let historyArr = uni.getStorageSync('historyArr') || [];
				let item = {
					id: this.detail.id,
					classid: this.detail.classid,
					picurl: this.detail.picurl,
					title: this.detail.title,
					looktime: parseTime(Date.now())
				};
				let index = historyArr.findIndex(i => {
					return i.id == this.detail.id
				});
				if (index >= 0) {
					// 从数组指定索引位置删除1个元素
					historyArr.splice(index, 1);
				};
				// 将元素添加到数组的开头
				historyArr.unshift(item);
				// 保留10条浏览记录
				historyArr = historyArr.slice(0, 10);
				uni.setStorageSync('historyArr', historyArr);
			}
		}
	}
</script>

<style lang="scss">
	.detail {
		padding: 30rpx;

		.title {
			font-size: 46rpx;
			color: #333;
		}

		.info {
			background-color: #f6f6f6;
			padding: 20rpx;
			font-size: 25rpx;
			color: #666;
			display: flex;
			justify-content: space-between;
			margin: 40rpx 0;
		}

		.content {
			padding-bottom: 50rpx;
		}

		.desc {
			background-color: #fef0f0;
			font-size: 26rpx;
			padding: 20rpx;
			color: #f89898;
			line-height: 1.8em;
		}
	}
</style>