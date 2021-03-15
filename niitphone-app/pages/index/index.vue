<template>
	<view class="content">
		<view class="u-demo-wrap">
			<view class="u-demo-area3">
				<u-toast ref="uToast"></u-toast>
				<u-search v-model="value" @custom="custom" @search="search" :shape="shape" :clearabled="clearabled" :show-action="showAction" :input-align="inputAlign"></u-search>
			</view>
			<view class="u-demo-area2">
				<u-toast ref="uToast"></u-toast>
				<u-swiper :height="250" :list="list2" :title="title" :effect3d="effect3d" :indicator-pos="indicatorPos" :mode="mode" :interval="3000" @click="click"></u-swiper>
			</view>
			<view class="u-demo-area1">
				<u-toast ref="uToast"></u-toast>
				<u-tabs
					v-if="control"
					bg-color="#fafafa"
					:bold="bold"
					:active-color="activeColor"
					:list="list"
					@change="change"
					:current="current"
					:is-scroll="isScroll"
					:offset="offset"
				></u-tabs>
		    </view>
			<view class="u-card-wrap">
				<u-card :padding="padding" :border="border">
					<view class="" slot="body"> 
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0" v-for="item in phoneList" :key="item.productId" @click="clickPhone(item)">
						<image :src="getImgSrc(item.productImg)" mode="aspectFill"></image>
						<view class="u-body-item-title u-line-2">
							<text class="phone_name">{{item.productName}}</text> 
							<view class="phone_brand">
							   <text> {{item.productClass}} </text>
							   <view class="shop-price">
							  	<text>¥</text>
							  	<text class="shop-price-text">{{item.productPrice}}</text>
							  </view>
							</view>
						</view>
					</view>
					</view>
				</u-card>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			//搜索框
			value: '',
			shape: 'round',
			clearabled: true,
			showAction: true,
			inputAlign: 'left',
			//Tab栏
			list: [],
			data: [
				{
					name: '推荐',
					count: 100
				},
				{
					name: '关注',
					count: 7
				},
				{
					name: '销量'
				},
				{
					name: '热门'
				},
				{
					name: '好评'
				},
				{
					name: '品牌'
				}
			],
			current: 0,
			sectionCurrent: 0,
			isScroll: true,
			tabCountIndex: 0,
			activeColor: this.$u.color['primary'],
			bold: true,
			control: true,
			offset: [5, -5],
			//轮播图
			list2: [
				{
					image: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
					title: '昨夜星辰昨夜风，画楼西畔桂堂东'
				},
				{
					image: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
					title: '身无彩凤双飞翼，心有灵犀一点通'
				},
				{
					image: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
					title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
				}
			],
			title: false,
			mode: 'round',
			indicatorPos: 'bottomCenter',
			effect3d: false,
			//卡片
			padding: 20,
			border: true,
			phoneList:[],
			token: ''
		};
	},
	onLoad() {
		this.token = getApp().globalData.token
		// console.log(this.token)
		this.list = this.data;
		uni.request({
			url: this.getUrl('product/selectAll'),
			header:{
				'Authorization' : this.token
			},
			success: (res) => {
				// console.log(res.data)
				if (res.data.code == 0) {
					this.phoneList = res.data.data
				}
			}
		})
	},
	methods: {
		clickPhone(index) {
			uni.request({
				header: {
					'Content-Type': 'application/json',
					'Authorization' : this.token
				},
				url: this.getUrl('product/popularPhone'),
				method: 'POST',
				data: {
					productId: index.productId,
					productName: index.productName,
					productClass: index.productClass,
					productPrice: index.productPrice
				},
				dataType: 'json',
				success: res => {
					console.log(res.data)
				}
			});
			getApp().globalData.phoneList = index
			uni.navigateTo({
				url:'../phone/phone'
			})
		},
		change(index) {
			this.current = index;
		},
		click(index) {
			this.$refs.uToast.show({
				title: `点击了第${index + 1}张图片`,
				type: 'success'
			});
		},
		custom(value) {
			//console.log(value);
			this.$u.toast('输入值为：' + value);
		},
		search(value) {
			this.$refs.uToast.show({
				title: '搜索内容为：' + value,
				type: 'success'
			});
		},
		clickImg(img) {
			//console.log(img);
		},
		click2(index) {
			console.log(index);
		},
		headClick(index) {
			console.log(index);
		}
	}
};
</script>

<style>
.u-demo-area3 {
	margin: 20rpx;
}
.u-demo-area2 {
	border: #71d5a1;
	border-style: double;
}
.wrap {
	padding: 30rpx;
	display: block;
}

.item-warp {
	display: flex;
	justify-content: space-between;
	flex-wrap: wrap;
}

.item-warp .item {
	flex: 0 0 335rpx;
	height: 400rpx;
	margin-bottom: 20rpx;
	border-radius: 10rpx;
	overflow: hidden;
}

.u-card-wrap { 
		background-color: $u-bg-color;
		padding: 1px;
	}
	
.u-body-item {
	   margin-top: 5px;
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}
		
.u-body-item image {
		width: 180rpx;
		flex: 0 0 180rpx;
		height: 180rpx;
		border-radius: 8rpx;
		margin-left: 2rpx;
	}
	
.u-body-item-title {
	margin-left: 25rpx;
	margin-top: -24rpx;
}

.phone_name {
	font-size: 22px;
}

.phone_brand {
	margin-top: 18rpx;
}

.shop-price {
		margin-top: 5px;
		font-size: 12px;
		color: #ff5a5f;
	}

	.shop-price-text {
		font-size: 16px;
	}
</style>
