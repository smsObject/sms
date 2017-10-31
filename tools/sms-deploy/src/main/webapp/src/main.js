// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import './assets/css/reset.css'
import Vue from 'vue'
import store from './store'
import Resource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
import './assets/css/layout.css'
import App from './App'
import router from './router'
import tag from './components/utils/tag'
Vue.use(Resource)
Vue.use(ElementUI)
Vue.config.productionTip = false

Vue.component(tag.name, tag);

// Vue.http.options.xhr = { withCredentials: true }
Vue.http.options.root = '/';
Vue.http.options.emulateJSON = true;
new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
import 'jquery'