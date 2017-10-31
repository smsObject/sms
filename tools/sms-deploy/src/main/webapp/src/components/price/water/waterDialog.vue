<template>
    <el-dialog title="用水类型" :visible.sync="infoDialog" @open="handleOpen" size="tiny">
        <el-form :model="form" ref="form" label-width="100px">
            <el-form-item label="用水类型" prop="name">
                <el-input  v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="类型描述" prop="remarks">
                <el-input  v-model="form.remarks"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="infoDialog = false">取 消</el-button>
            <el-button type="primary" @click="confirm">保存</el-button>
        </div>
    </el-dialog>
</template>
<script type="text/babel">
    import formDialog from "../../../assets/js/mixins/formDialog"
    import Emitter from "../../../assets/js/mixins/emitter"
    import {numberToChinese} from "../../../assets/js/utils/util"
    import {addUseType,updateUseType} from "../../../api/get"

    export default {
        mixins:[formDialog,Emitter],
        data() {
            return {
                infoDialog:false,
                formLabelWidth:'100px',
                form:{
                    remarks:"",
                    name:""
                },

            }
        },
        methods:{
            confirm() {
                var params = Object.assign({},this.form)
                delete params.updateTime
                delete params.createTime
                if(this.type){
                    updateUseType({params}).then(res => {
                        this.$message.success("编辑成功")
                        this.infoDialog = false
                        this.dispatch("price.list","fetch")
                    })
                }else {
                    addUseType({params:this.form}).then(res =>{
                        console.log(res)
                        this.$message.success("添加成功")
                        this.infoDialog = false
                        this.dispatch("price.list","fetch")
                    })
                }
            }
        },
        filters:{
            numberToChinese:numberToChinese
        }
    }
</script>