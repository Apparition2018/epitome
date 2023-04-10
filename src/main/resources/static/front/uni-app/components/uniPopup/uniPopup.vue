<template>
	<view>
		<view>---弹出框样式---</view>
		<view class="box" :style="{height: state ? '300rpx':'0'}"></view>
		<button size="mini" @click="turnOff">关闭</button>
	</view>
</template>

<script>
	export default {
		name: "uniPopup",
		data() {
			return {

			};   
		},
		props: {
			state: {
				type: Boolean,
				default: false
			}
		},
		methods: {
			turnOff() {
				// 第一种
				// this.$emit("turnoff", false)
				// 第二、三种
				this.$emit("update:state", false)
			}
		}
	}
</script>

<style lang="scss">
	.box {
		width: 300rpx;
		height: 300rpx;
		background-color: pink;
	}
</style>