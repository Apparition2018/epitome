<template>
	<view>
		<button type="default" @click="setStorage('storage_key')">set</button>
		<button type="default" @click="getStorage('storage_key')">get</button>
		<button type="default" @click="removeStorage('storage_key')">remove</button>
		<button type="default" @click="clearStorage()">clear</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		},
		methods: {
			setStorage(key) {
				uni.setStorageSync('storage_key', {
					name: 'marry',
					age: '23'
				});
			},
			getStorage(key) {
				uni.showToast({
					title: uni.getStorageSync('storage_key').name,
					icon: 'none',
					mask: true
				})
			},
			removeStorage(key) {
				uni.removeStorageSync(key)
			},
			clearStorage() {
				uni.clearStorage();
			}
		},
		onLoad() {}
	}
</script>

<style lang="scss">

</style>