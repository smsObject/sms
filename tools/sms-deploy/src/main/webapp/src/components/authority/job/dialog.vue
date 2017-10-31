<template>
    <el-dialog title="岗位录入" :visible.sync="infoDialog" @open="handleOpen" size="tiny">
        <el-form :model="form" ref="form" label-width="100px">
            <el-form-item label="岗位姓名" prop="name">
                <el-input  v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="备注" prop="remarks">
                <el-input v-model="form.remarks"></el-input>
            </el-form-item>
            <el-form-item label="权限菜单" prop="menuIds">
                <el-select v-model="form.menuIds" multiple>
                    <el-option
                            v-for="item in menus"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
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
    import {authorityJobAdd,authorityJobUpdate} from "../../../api/get"
    export default {
        mixins:[formDialog,emitter],
        props:{
            menus:Array
        },
        data() {
            return {
                formLabelWidth: '80px',
                form:{
                    name: '',
                    remarks:'',
                    systemMenus:[],
                    menuIds:[]
                },
                infoDialog:false
            }
        },
        watch:{
            "form.systemMenus"(val){
                if(Array.isArray(val)){
                    this.form.menuIds = val.map(item =>  item.id)
                }
            }
        },
        methods:{
            confirm() {
                if(this.type){
                    var params = Object.assign({},this.form,{menuIds:this.form.menuIds+""})
                    delete params.systemMenus
                    delete params.updateTime
                    delete params.createTime
                    authorityJobUpdate({params}).then(res => {
                        this.$message.success("操作成功")
                        this.dispatch("authority.job","update-list")
                        this.infoDialog = false
                    }).catch(res => {
                        this.$message.success(res)
                    })

                }else {
                    var params = Object.assign({},this.form,{menuIds:this.form.menuIds+""})
                    authorityJobAdd({params}).then(res => {
                        delete params.systemMenus
                        this.$message.success("操作成功")
                        this.infoDialog = false
                        this.dispatch("authority.job","update-list")
                    }).catch(res => {
                        this.$message.error(res)
                    })
                }

            }
        }
    }
</script>