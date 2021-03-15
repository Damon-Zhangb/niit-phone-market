import Vue from 'vue'
import App from './App'
// 引入全局uView
import uView from 'uview-ui';
Vue.use(uView);

Vue.config.productionTip = false

App.mpType = 'app'

Vue.prototype.getUrl = function(url) {
	return "http://192.168.43.30:10086/" + url
}

Vue.prototype.getImgSrc = function(img) {
	return "http://192.168.43.30:10086/img/" + img
}



const app = new Vue({
    ...App
})
app.$mount()
