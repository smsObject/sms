<template>
    <div class="signin">
        <h3 class="title">抄表系统登入</h3>
        <el-form :model="model">
            <el-form-item prop="userName">
                <el-input placeholder="手机号" v-model="model.userName" auto-complete="on"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" placeholder="密码" v-model="model.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm" class="signin-btn">登入</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<style lang="scss" rel="stylesheet/scss">
    .signin{
        width:300px;
        margin:20px auto;
        .title{
            padding:20px;
            text-align: center;
        }
        .signin-btn{
            width:100%;
        }
    }
</style>
<script type="text/babel">
    import {signin} from '../../api/post'
    export default {
        data() {
            return {
                model:{
                    userName:"",
                    password:""
                }
            }
        },
        methods:{
            submitForm(){
                signin({params:this.model}).then(res =>{
					window.location = "/sms/index"
				})
            }
        }
    }
</script>