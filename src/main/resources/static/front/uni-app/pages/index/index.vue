<template>
	<div class="box">
		<h1>uni-app<span class="zero">零基础</span>入门</h1>
		<view>从基础到进阶</view>
		<navigator url="/pages/news/news">新闻列表</navigator>
		<navigator url="/pages/test/test">测试</navigator>
	</div>
</template>

<script>
	export default {
	}
</script>

<style lang="scss">
.box {
	height: 100px;
	background: pink;
	h1 {
		font-style: 40rpx;
		color: red;
		.zero {
			font-style: italic;
		}
	}
}
</style>
