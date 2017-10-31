import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
const signin = resolve => require(['../components/signin/index'],resolve)

export default new Router({
  base: __dirname,
  routes: [
    // 根路由，重定向到 companyInfoList
    {path: '/',redirect: '/signin'},
    // 公司信息列表
    { path: '/signin', name: 'signin', component: signin},
  ]
})
