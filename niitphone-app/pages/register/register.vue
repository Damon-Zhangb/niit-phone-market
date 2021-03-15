<template>
	<view class="wrap">
		<view class="title">欢迎注册NIIT</view>
		<u-form :model="model" :rules="rules" ref="uForm" :errorType="errorType">
			<u-form-item :leftIconStyle="{ color: '#888', fontSize: '32rpx' }" left-icon="account" label-width="120" label-position="top" label="用户名" prop="name">
				<u-input placeholder="请输入用户名" v-model="model.name" type="text" ></u-input>
			</u-form-item>
			<u-form-item :rightIconStyle="{ color: '#888', fontSize: '32rpx' }" right-icon="kefu-ermai" label-position="top" label="手机号码" prop="phone" label-width="150">
				<u-input placeholder="请输入手机号" v-model="model.phone" type="number"></u-input>
			</u-form-item>
			<u-form-item label-position="top" label="密码" prop="password">
				<u-input :password-icon="true" type="password" v-model="model.password" placeholder="请输入密码"></u-input>
			</u-form-item>
			<u-form-item label-position="top" label="确认密码" label-width="150" prop="rePassword">
				<u-input type="password" v-model="model.rePassword" placeholder="请确认密码"></u-input>
			</u-form-item>
			<u-form-item label-position="top" label="性别" prop="sex">
				<u-input type="select" :select-open="actionSheetShow" v-model="model.sex" placeholder="请选择性别" @click="actionSheetShow = true"></u-input>
			</u-form-item>
			<u-form-item label-position="top" label="年龄" prop="age" label-width="150">
				<u-input type="select" :select-open="selectShow" v-model="model.age" placeholder="请选择年龄" @click="selectShow = true"></u-input>
			</u-form-item>
			<u-form-item label-position="top" label="所在地区" prop="region" label-width="150">
				<u-input type="select" :select-open="pickerShow" v-model="model.region" placeholder="请选择地区" @click="pickerShow = true"></u-input>
			</u-form-item>
		</u-form>
		
		<u-toast :type="type" ref="uToast"></u-toast>
		<u-button class="button" :ripple="true" @click="submit" type="primary">提交</u-button>
		<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
		<u-select mode="single-column" :list="selectList" v-model="selectShow" @confirm="selectConfirm"></u-select>
		<u-picker mode="region" v-model="pickerShow" @confirm="regionConfirm"></u-picker>
	</view>
</template>

<script>
export default {
	data() {
		let that = this;
		return {
			model: {
				name: '',
				sex: '',
				region: '',
				age: '',
				phone: '',
				password: '',
				rePassword: '',
				agreement: false,
				
			},
			rules: {
				name: [
					{
						required: true,
						message: '请输入用户名',
						trigger: 'blur' ,
					},
					{
						min: 2,
						max: 5,
						message: '姓名长度在2到5个字符',
						trigger: ['change','blur'],
					},
					{
						// 此为同步验证，可以直接返回true或者false，如果是异步验证，稍微不同，见下方说明
						validator: (rule, value, callback) => {
							// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
							return this.$u.test.chinese(value);
						},
						message: '姓名必须为中文',
						// 触发器可以同时用blur和change，二者之间用英文逗号隔开
						trigger: ['change','blur'],
					},
					// 异步验证，用途：比如用户注册时输入完账号，后端检查账号是否已存在
					// {
					// 	trigger: ['blur'],
					// 	// 异步验证需要通过调用callback()，并且在里面抛出new Error()
					// 	// 抛出的内容为需要提示的信息，和其他方式的message属性的提示一样
					// 	asyncValidator: (rule, value, callback) => {
					// 		this.$u.post('/ebapi/public_api/index').then(res => {
					// 			// 如果验证出错，需要在callback()抛出new Error('错误提示信息')
					// 			if(res.error) {
					// 				callback(new Error('姓名重复'));
					// 			} else {
					// 				// 如果没有错误，也要执行callback()回调
					// 				callback();
					// 			}
					// 		})
					// 	},
					// }
				],
				sex: [
					{
						required: true,
						message: '请选择性别',
						trigger: 'change'
					},
				],


				region: [
					{
						required: true,
						message: '请选择地区',
						trigger: 'change',
					}
				],
				age: [
					{
						required: true,
						message: '请选择年龄',
						trigger: 'change',
					}
				],
				phone: [
					{
						required: true,
						message: '请输入手机号',
						trigger: ['change','blur'],
					},
					{
						validator: (rule, value, callback) => {
							// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
							return this.$u.test.mobile(value);
						},
						message: '手机号码不正确',
						// 触发器可以同时用blur和change，二者之间用英文逗号隔开
						trigger: ['change','blur'],
					}
				],
				password: [
					{
						required: true,
						message: '请输入密码',
						trigger: ['change','blur'],
					},
					{
						// 正则不能含有两边的引号
						pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/,
						message: '需同时含有字母和数字，长度在6-12之间',
						trigger: ['change','blur'],
					}
				],
				rePassword: [
					{
						required: true,
						message: '请重新输入密码',
						trigger: ['change','blur'],
					},
					{
						validator: (rule, value, callback) => {
							return value === this.model.password;
						},
						message: '两次输入的密码不相等',
						trigger: ['change','blur'],
					}
				],
			},

			actionSheetList: [
		    	{
		    		text: '男'
		    	},
		    	{
		    		text: '女'
		    	},
		    ],
		    actionSheetShow: false,
			pickerShow: false,
			selectList: [
				
				{
					value: 18,
					label: 18
				},
				{
					value: 19,
					label: 19
				},
				{
					value: 20,
					label: 20
				},
				{
					value: 21,
					label: 21
				},
				{
					value: 22,
					label: 22
				},
				{
					value: 23,
					label: 23
				},
				{
					value: 24,
					label: 24
				},
				{
					value: 25,
					label: 25
				},
				{
					value: 26,
					label: 26
				},
				{
					value: 27,
					label: 27
				},
				{
					value: 28,
					label: 28
				},
				{
					value: 29,
					label: 29
				},
				{
					value: 30,
					label: 30
				},
				{
					value: 31,
					label: 31
				},
				{
					value: 32,
					label: 32
				},
				{
					value: 33,
					label: 33
				},
				{
					value: 34,
					label: 34
				},
				{
					value: 35,
					label: 35
				},
				{
					value: 36,
					label: 36
				},
				{
					value: 37,
					label: 37
				},
				{
					value: 38,
					label: 38
				},
				{
					value: 39,
					label: 39
				},
				{
					value: 40,
					label: 40
				},
				{
					value: 41,
					label: 41
				},
				{
					value: 42,
					label: 42
				},
				{
					value: 43,
					label: 43
				},
				{
					value: 44,
					label: 44
				},
				{
					value: 45,
					label: 45
				},
				{
					value: 46,
					label: 46
				},
				{
					value: 47,
					label: 47
				},
				{
					value: 48,
					label: 48
				},
				{
					value: 49,
					label: 49
				},
				{
					value: 50,
					label: 50
				},
				{
					value: 51,
					label: 51
				},
				{
					value: 52,
					label: 52
				},
				{
					value: 53,
					label: 53
				},
				{
					value: 54,
					label: 54
				},
				{
					value: 55,
					label: 55
				},
				{
					value: 56,
					label: 56
				},
				{
					value: 57,
					label: 57
				},
				{
					value: 58,
					label: 58
				},
				{
					value: 59,
					label: 59
				}
				
				
			],
			selectShow: false,
			errorType: ['message'],
			type: 'success',
			title: '',
			icon: true,
			position: 'center',
			url: 'pages/login/index'

		}
	},
	onReady() {
		this.$refs.uForm.setRules(this.rules);
	},
	methods: {
		submit() {
			this.$refs.uForm.validate(valid => {
				if (valid) {
					console.log('验证成功');
						uni.request({
							header:{
							  'Content-Type': 'application/json'
							},
							url: this.getUrl('sso/addUser'),
							method:'POST',
							data:{
								"userName" : this.model.name,
								"userPhone" : this.model.phone,
						        "password" : this.model.password,
							    "userSex" : this.model.sex,
							    "userAge" : parseInt(this.model.age),
							    "userAddress" : this.model.region
							},
							dataType:'json',
							success: (res) => {
								if(res.data.code == 0){
									this.title = res.data.msg + "\n请登录!"
									this.show();
		
								} else {
									if(res.data.code == 1002) {
										this.type = "error"
										this.url = ''
										this.title = res.data.msg
										this.show();
									}
								}
							}
						})
				} else {
					console.log('验证失败');
				}
			});
		},
        show() {
			this.$refs.uToast.show({
				title: this.title,
				position: this.position,
				type: this.type,
				icon: this.icon,
				url: this.url,
			});
		},
		hide() {
			this.$refs.uToast.hide();
		},
		// 点击actionSheet回调
		actionSheetCallback(index) {
			uni.hideKeyboard();
			this.model.sex = this.actionSheetList[index].text;
		},
	    // 选择地区回调
	    regionConfirm(e) {
	    	this.model.region = e.province.label + '-' + e.city.label + '-' + e.area.label;
	    },
	    // radio选择发生变化
	    // 选择商品类型回调
	    selectConfirm(e) {
	    	this.model.age = '';
	    	e.map((val, index) => {
	    		this.model.age += this.model.age == '' ? val.label : '-' + val.label;
	    	})
	    },
	}
}
</script>

<style>
.wrap {
	padding: 30rpx;
}
.button {
	margin-top: 60rpx;
	padding: 30rpx;
}
.title {
			text-align: left;
			font-size: 60rpx;
			font-weight: 500;
			margin-bottom: 30rpx;
		}
</style>
