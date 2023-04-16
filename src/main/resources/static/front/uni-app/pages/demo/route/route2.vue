<template>
	<view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		onLoad(option) {
			// option为object类型，会序列化上个页面传递的参数
			console.log(option);
			//  获取当前的页面栈
			console.log(getCurrentPages());
		}
	}
</script>

<style>

</style>
