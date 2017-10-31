<template>
    <div >
        <div class="cr-relative">
            <el-tabs v-model="activeName">
                <el-tab-pane label="账号管理" name="authorityUser" v-if="userPermission['system:pageUser']"></el-tab-pane>
                <el-tab-pane label="岗位管理" name="authorityJob" v-if="userPermission['system:pageRole']"></el-tab-pane>
            </el-tabs>
            <div class="cr-absolute-right btn-wrap">
                <el-button size="small"
                           @click="handleAdd('authority.user')"
                           v-if="activeName == 'authorityUser'"
                           v-show="userPermission['system:addUser']">新增账户</el-button>
                <el-button size="small"
                           @click="handleAdd('authority.job')"
                           v-if="activeName == 'authorityJob'"
                           v-show="userPermission['system:addRole']">新增岗位</el-button>
            </div>
        </div>
        <router-view></router-view>
    </div>
</template>
<script type="text/babel">
    import Emitter from "../../assets/js/mixins/emitter"
    import { mapGetters } from 'vuex'
    export default {
        mixins:[Emitter],
        data() {
            return {
                activeName:this.$route.name
            }
        },
        created(){
            this.dispatch("app","active-nav",[this.$route.name])
        },
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        watch:{
            activeName(val) {
                this.$router.replace({name:val})
            }
        },
        beforeRouteUpdate (to, from, next) {
            this.activeName = to.name
            next()
        },
        methods:{
            handleAdd(componentName) {
                this.broadcast(componentName,"show-dialog")
            }
        }
    }
</script>