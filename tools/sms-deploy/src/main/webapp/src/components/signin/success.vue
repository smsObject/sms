<template>
    <div>
        <header class="top-header-wrap">
            <!-- 配置成单页应用需要开启router -->
            <el-menu theme="dark" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                <el-menu-item index="companyInfoList">公司信息</el-menu-item>
                <el-submenu index="1">
                    <template slot="title">用户管理</template>
                    <el-menu-item v-show="userPermission['userInfo:page']" index="userInfoList">用户信息</el-menu-item>
                    <el-menu-item index="priceList">价格设置</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">我的设备</template>
                    <el-menu-item v-show="userPermission['device:meterPage']" index="device">仪表</el-menu-item>
                    <el-menu-item v-show="userPermission['device:getMeterData']" index="deviceData">数据</el-menu-item>
                </el-submenu>
                <el-menu-item index="businessControl">营业管理</el-menu-item>
                <el-submenu index="3" v-if="userPermission['system:pageUser'] || userPermission['system:pageRole']">
                    <template slot="title">系统设置</template>
                    <el-menu-item index="authorityUser" v-show="userPermission['system:pageUser']">账号管理</el-menu-item>
                    <el-menu-item index="authorityJob" v-show="userPermission['system:pageRole']">岗位管理</el-menu-item>
                </el-submenu>
                <el-menu-item class="float-right" index="5">安全退出</el-menu-item>
            </el-menu>
        </header>
        <router-view>

        </router-view>
    </div>
</template>

<script type="text/babel">
    import {signOut,getUserPermission} from '../../api/post'
    import { mapGetters } from 'vuex'
    export default {
        componentName:'app',
        name: 'app',
        created(){
            this.fetchUserPermission()
            this.$on("active-nav",function(val){
                this.activeIndex = val
            })
        },
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        data(){
            return {
                queryModel:{},
                item1: "我的设备",
                item2: "GSM",
                title: "",
                activeIndex:"",
                waitDataMsg: "",
                intervalId: "",
                timeOutId: "",
                dialogVisible: false,
                gsmManager: {},
                meterManager: {
                    loadReadMeter: false,
                    loadOpenMeter: false,
                    loadCloseMeter: false,
                    loadManagerCenter: false,
                    loadTiming:false,
                    timingVisible:false,
                    disabledTiming:false,
                    disabledReadMeter: false,
                    disabledManagerCenter: false,
                    disabledOpenMeter: false,
                    disabledCloseMeter: false,
                    managerCenterVisible: false,
                    addMeterVisible: false,
                    mc1: "",
                    mc2: "",
                    mc3: "",
                    form: {},
                    form1: {},
                    form2: {},
                    formLabelWidth: 100,
                    page: 1,
                    isShow: false,
                    data: [],
                    gsmData: [],
                    day1:"0",
                    day2:"0",
                    day3:"0",
                    timing1:"",
                    timing2:"",
                    timing3:"",
                    addMeterData: {meterCode: "", controllerId: "0", meterName: "", fixDate: "", unit: "吨"},
                    total: 0,
                    row: null
                },
                meterDataManager: {
                    isShow: false,
                    page: 1,
                    data: [],
                    total: 20,
                    row: null
                },
                interval: {}
            }
        },
        methods:{
            handleSelect(key,keyPath) {
                if(key == 5){
                    signOut({params:{}}).then(res =>{
					window.location = "/auth/login"
				})
                }else {
                    this.$router.push({ name: key })
                }
            },
            fetchUserPermission() {
                getUserPermission({}).then(res =>{
                    console.log(res)
                    this.$store.dispatch("login",res.data)
                })
            }
        }
    }
</script>