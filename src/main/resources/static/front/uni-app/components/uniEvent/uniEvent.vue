<template>
	<view>
		<view>
			<input type="text" placeholder="请输入…" @input="oninput">
		</view>
	</view>
</template>

<script>
	export default {
		name: "uniEvent",
		data() {
			return {};
		},
		props: {},
		methods: {
			oninput(e) {
				this.$emit("myevent", {
					value: e.detail.value,
					time: Date.now()
				});
			}
		}
	}
</script>

<style lang="scss">

</style>