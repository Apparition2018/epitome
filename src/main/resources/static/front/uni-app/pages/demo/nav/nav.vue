<template>
	<view>
		<view class="nav">
			<view class="item" :class="navIndex == index ? 'active' : ''" v-for="(item,index) in titles" :key="index" @click="clickNav(index)">{{item}}
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				titles: [
					"首页", "介绍", "教程", "组件"
				],
				navIndex: 0
			}
		},
		methods: {
			clickNav(e) {
				this.navIndex = e;
			}
		}
	}
</script>

<style lang="scss">
	.nav {
		display: flex;
		justify-content: space-around;
		align-items: center;

		.item {
			flex: 1;
			line-height: 90rpx;
			background-color: #ccc;
			text-align: center;

			&.active {
				background-color: #1AA03A;
				color: #fff;

			}
		}
	}
</style>