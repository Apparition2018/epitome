<template>
	<view>
		<uniTitle :time="time" title="Demo" subtitle="demo" :list="[4,5,6]" user="{user: 'Mary', gender: 'female'}">
		</uniTitle>
		
		<uniEvent @myevent="onmyevent" @click.native="onclick"></uniEvent>
		
		<button @click="turnOn">开启</button>
		<!-- 第一种 -->
		<!-- <uniPopup :state="state" @turnoff="onturnoff"></uniPopup> -->
		<!-- 第二种 -->
		<!-- <uniPopup :state="state" @update:state="updateState"></uniPopup> -->
		<!-- 第三种 -->
		<uniPopup :state.sync="state"></uniPopup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				time: Date.now(),
				state: false
			};
		},
		methods: {
			onmyevent(e) {
				console.log(e);
			},
			onclick() {
				console.log('click');
			},
			turnOn() {
				this.state = true
			},
			// 第一种
			// onturnoff(e) {
			// 	this.state = e;
			// },
			// 第二种
			// updateState(e) {
			// 	this.state = e;
			// }
		}
	}
</script>

<style lang="scss">

</style>