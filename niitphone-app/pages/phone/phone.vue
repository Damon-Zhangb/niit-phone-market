<template>
	<view>
	<view>
		<image :src="phoneList.productImg" mode="aspectFill"></image>
		<view >
			<text>{{ phoneList.productName }}</text>
			<view >
				<text>{{ phoneList.productClass }}</text>
				<view >
					<text>¥</text>
					<text>{{ phoneList.productPrice }}</text>
				</view>
			</view>
		</view>
	</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			phoneList:{}
		};
	},
	onLoad() {
		this.phoneList = getApp().globalData.phoneList
	},
	methods: {}
};
</script>

<style></style>
