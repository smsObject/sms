import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)


const setDocumentTitle = ({title="远程抄表管理系统",next}) => {
  document.title = title;
  document.body.scrollTop = 0
  // 微信端兼容
  // if (/ip(hone|od|ad)/i.test(navigator.userAgent)) {
  //   var i = document.createElement('iframe');
  //   i.src = '/favicon.ico';
  //   i.style.display = 'none';
  //   i.onload = function() {
  //     setTimeout(function(){
  //       i.remove();
  //     }, 9)
  //   }
  //   document.body.appendChild(i);
  // }
  next()
}

const companyInfoList = resolve => require(['../components/companyInfo/list'], resolve)

// const device = resolve => require(['../components/device/Device'], resolve)

const userInfoList = resolve => require(['../components/userInfo/list'], resolve)

const authority = resolve => require(['../components/authority/index'],resolve)

const authorityUser = resolve => require(['../components/authority/user/list'],resolve)

const authorityJob = resolve => require(['../components/authority/job/list'],resolve)

const priceList = resolve => require(['../components/price/index'],resolve)

const businessControl = resolve => require(['../components/businessManagement/index'],resolve)

const device = resolve => require(['../components/device/device'],resolve)

const deviceData = resolve => require(['../components/device/data'],resolve)

const signin = resolve => require(['../components/signin/index'],resolve)

const success = resolve => require(['../components/signin/success'],resolve)

export default new Router({
  base: __dirname,
  routes: [
    // 根路由，重定向到 companyInfoList
    {path: '/',redirect: '/companyInfoList'},
    { path: '/signin', name: 'signin', component: signin},
    // 公司信息列表
    { path: '/in', name: 'success', component: success,children:[
      { path: '/companyInfoList', name: 'companyInfoList', component: companyInfoList},

      // 用户管理--用户信息
      { path: '/userInfoList', name: 'userInfoList', component: userInfoList},

      // 我的设备--仪表
      { path: '/device', name: 'device', component: device},

      // 我的设备--数据
      { path: '/deviceData', name: 'deviceData', component: deviceData},

      //  水费设置
      { path: '/priceList', name: 'priceList', component: priceList},
      { path: '/businessControl', name: 'businessControl', component: businessControl},

      // 权限管理
      { path: '/authority', name: 'authority', component: authority,children:[
        { path: 'user', name: 'authorityUser', component: authorityUser},
        { path: 'job', name: 'authorityJob', component: authorityJob},
      ]},
    ]},
  ]
})
