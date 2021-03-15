<template>
	<view class="wrap">
		<view class="top"></view>
		<view class="content">
			<view class="title">欢迎登录NIIT</view>
			<view class="u-demo-area">
				<u-field v-model="mobile" label="手机号" :error-message="errorMessage1" placeholder="请填写手机号"></u-field>
				<u-field v-model="password" label="密码" :password="psd" placeholder="请填写密码"></u-field>
				<u-row>
					<u-col span="8"><u-field v-model="code" :error-message="errorMessage2" label="验证码" placeholder="请填写验证码"></u-field></u-col>
					<u-col span="4"><image :src="src" style="width: 100%; height: 40px;" @click="authcode"></image></u-col>
				</u-row>
			</view>
			<button @tap="submit" :style="[inputStyle]" class="getCaptcha">登录</button>
			<view class="alternative">
				<view class="password" @click="register">注册</view>
				<view class="issue">遇到问题</view>
			</view>
		</view>
		<view class="buttom">
			<view class="hint">
				登录代表同意
				<text class="link">NIIT手机商城用户协议、隐私政策，</text>
				并授权使用您的NIIT手机商城账号信息（如昵称、头像、收获地址）以便您统一管理
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			src: '',
			mobile: '',
			code: '',
			password: '',
			errorMessage1: '',
			errorMessage2: '',
			token: '',
			psd: true
		};
	},
	onLoad() {
		uni.request({
		    url:this.getUrl('sso/authcode'),
			success: res => {
				this.src = res.data.data.img_src;
				this.token = res.data.data.token;
			}
		});
	},
	computed: {
		inputStyle() {
			let style = {};
			if (this.mobile && this.code && this.password) {
				style.color = '#fff';
				style.backgroundColor = this.$u.color['warning'];
			}
			return style;
		}
	},
	methods: {
		register() {
			this.$u.route({
				url: 'pages/register/register'
			});
		},
		submit() {
			this.errorMessage1 = '';
			this.errorMessage2 = '';
			if (this.$u.test.mobile(this.mobile)) {
				uni.request({
					header: {
						'Content-Type': 'application/json'
					},
					url: this.getUrl('sso/login'),
					method: 'POST',
					data: {
						userPhone: this.mobile,
						password: this.password,
						code: this.code,
						token: this.token
					},
					dataType: 'json',
					success: res => {
						if (res.data.code == 0) {
							getApp().globalData.token = res.data.data.token
							uni.switchTab({
								url:'../index/index'
							})
						} else {
							if (res.data.code == 1001) {
								this.errorMessage1 = res.data.msg;
							} else if (res.data.code == 1002) {
								this.errorMessage2 = res.data.msg;
							}
							uni.request({
								url: this.getUrl('sso/authcode'),
								success: res => {
									this.src = res.data.data.img_src;
									this.token = res.data.data.token;
								}
							});
						}
					}
				});
			}
		},
		errorMessageChange(index) {
			//TODO
			this.errorMessage = index == 0 ? '手机号有误' : false;
		},
		authcode() {
			
			uni.request({
				url:  this.getUrl('sso/authcode') ,
				success: res => {
					this.src = res.data.data.img_src;
					this.token = res.data.data.token;
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.wrap {
	font-size: 28rpx;
	.content {
		width: 600rpx;
		margin: 80rpx auto 0;

		.title {
			text-align: left;
			font-size: 60rpx;
			font-weight: 500;
			margin-bottom: 100rpx;
		}
		input {
			text-align: left;
			margin-bottom: 10rpx;
			padding-bottom: 6rpx;
		}
		.tips {
			color: $u-type-info;
			margin-bottom: 60rpx;
			margin-top: 8rpx;
		}
		.getCaptcha {
			margin-top: 30rpx;
			background-color: rgb(253, 243, 208);
			color: $u-tips-color;
			border: none;
			font-size: 30rpx;
			padding: 12rpx 0;

			&::after {
				border: none;
			}
		}
		.alternative {
			color: $u-tips-color;
			display: flex;
			justify-content: space-between;
			margin-top: 30rpx;
		}
	}
	.buttom {
		.loginType {
			display: flex;
			padding: 350rpx 150rpx 150rpx 150rpx;
			justify-content: space-between;

			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				color: $u-content-color;
				font-size: 28rpx;
			}
		}

		.hint {
			margin-top: 350rpx;
			margin-left: 20px;
			margin-right: 20px;
			padding: 40rpx 40rpx;
			font-size: 20rpx;
			color: $u-tips-color;

			.link {
				color: $u-type-warning;
			}
		}
	}
}
</style>
