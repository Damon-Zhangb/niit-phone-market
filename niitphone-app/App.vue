<script>
	export default {
		globalData:{
			token: '',
			phoneList:{
				productId: 0,
				productImg:'',
				productName:'',
				productClass: '',
				productPrice: 0
			}
		},
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
	/*每个页面公共css */
</style>
