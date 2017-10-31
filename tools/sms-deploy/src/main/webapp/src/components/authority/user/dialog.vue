<template>
    <el-dialog title="账户录入" :visible.sync="infoDialog" @open="handleOpen" size="little">
        <el-form :model="form" ref="form" label-width="100px">
            <el-form-item label="账户名称" prop="name">
                <el-input  v-model="form.name"></el-input>
            </el-form-item>

            <el-form-item label="手机号" prop="phone">
                <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="所属岗位选择" prop="roleId">
                <el-select v-model="form.roleId" >
                    <el-option :key="item.id" v-for="item of workers" :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="infoDialog = false">取 消</el-button>
            <el-button type="primary" @click="confirm">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script type="text/babel">
    import formDialog from "../../../assets/js/mixins/formDialog"
    import emitter from "../../../assets/js/mixins/emitter"
    import {roleList,authorityUserAdd,authorityUserUpdate} from "../../../api/get"
    export default {
        mixins:[formDialog,emitter],
        data() {
            return {
                formLabelWidth: '80px',
                workers:[],
                form:{
                    name: '',
                    phone: '',
                    password:'',
                    roleId: [],
                },
                infoDialog:false
            }
        },
        created() {
            this.queryJobList()
        },
        methods:{
            queryJobList(){
                roleList({param:{}}).then(res => {
                    this.workers = res.data
                })
            },
            confirm(){
                if(this.type){
                    var params = Object.assign({},this.form)
                    delete params.systemRoles
                    delete params.updateTime
                    delete params.createTime
                    authorityUserUpdate({params}).then(res => {
                        this.$message('success',"操作成功")
                        this.dispatch("authority.user","update-list")
                        this.infoDialog = false
                    }).catch(res => {
                        this.$message.success(res.statusText)
                    })
                }else {
                    authorityUserAdd({params:this.form}).then(res => {
                        this.$message.success("操作成功")
                        this.dispatch("authority.user","update-list")
                        this.infoDialog = false
                    }).catch(res => {
                        this.$message.error(res.statusText)
                    })
                }
            }
        }
    }
</script>