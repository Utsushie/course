import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
//引用为全局变量
Vue.prototype.$ajax = axios;

new Vue({
	router,
	render: h => h(App),
}).$mount('#app')
