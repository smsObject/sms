<template>
    <el-dialog title="公司信息录入" :visible.sync="infoDialog" @open="handleOpen">
        <el-form :model="form" ref="form" label-width="100px">
            <el-row>
                <el-col :span="16">
                    <el-form-item label="公司名称" :label-width="formLabelWidth" prop="name">
                        <el-input  v-model="form.companyName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="更新日期">
                        <el-date-picker :disabled="true" v-model="form.updateTime" type="date"></el-date-picker>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label="企业代码" :label-width="formLabelWidth" prop="code">
                        <el-input v-model="form.companyCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业税号" prop="taxNo">
                        <el-input v-model="form.taxNo"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="8">
                    <el-form-item label="服务电话" :label-width="formLabelWidth" prop="serviceCall">
                        <el-input v-model="form.serviceCall"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="服务传真" prop="serviceFax">
                        <el-input v-model="form.serviceFax"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="服务邮箱" prop="serviceEmail">
                        <el-input v-model="form.serviceEmail"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="8">
                    <el-form-item label="报障电话" :label-width="formLabelWidth" prop="complaintCall">
                        <el-input v-model="form.complaintCall"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="16">
                    <el-form-item label="服务网站" prop="serviceWebsite">
                        <el-input v-model="form.serviceWebsite"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="联系地址" :label-width="formLabelWidth" prop="address">
                        <el-input v-model="form.address"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="邮编" label-width="60px" prop="zipCode">
                        <el-input v-model="form.zipCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="7" prop="contactPerson">
                    <el-form-item label="业务联系人">
                        <el-input v-model="form.contactPerson"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label="开户行" :label-width="formLabelWidth" prop="bankName">
                        <el-input v-model="form.bankName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="银行账号" prop="bankAccount">
                        <el-input v-model="form.bankAccount"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="8">
                    <el-form-item label="所在区域" :label-width="formLabelWidth" prop="allocatedRegion">
                        <el-input v-model="form.allocatedRegion"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="辖区用户总量" label-width="130px" prop="totalAmount">
                        <el-input v-model="form.totalAmount"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item class="" label="仪表正常使用数量" label-width="150px" prop="regularUseAmount">
                        <el-input v-model="form.regularUseAmount"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="infoDialog = false">取 消</el-button>
            <el-button type="primary" @click="confirm">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script type="text/babel">
    import formDialog from "../../assets/js/mixins/formDialog"
    import {companyInfoUpdate,companyInfoAdd} from "../../api/get"
    function filtration(obj){
        for(var key in obj){
            if(obj.hasOwnProperty(key) && !obj[key]){
                delete obj[key]
            }
        }
        return obj
    }
    export default {
        mixins:[formDialog],
        methods:{
            confirm(){
                var params = Object.assign({},this.form)
                delete params.updateTime
                delete params.createTime
                if(this.type){
                    companyInfoUpdate({params}).then(res => {
                        this.$message.success("编辑成功")
                        this.infoDialog = false
                    })
                }else {
                    companyInfoAdd({params}).then(res => {
                        this.$message.success("添加成功")
                        this.infoDialog = false
                    })
                }

            }
        },
        data() {
            return {
                formLabelWidth: '80px',
                form:{
                    updateTime:new Date(),
                    companyName: '',
                    companyCode:"",
                    serviceCall: '',
                    serviceFax: '',
                    serviceEmail: '',
                    complaintCall: '',
                    serviceWebsite: '',
                    address: '',
                    zipCode: '',
                    contactPerson: '',
                    taxNo: '',
                    bankName: '',
                    bankAccount: '',
                    allocatedRegion: '',
                    totalAmount: '',
                    regularUseAmount: '',
                },
                infoDialog:false
            }
        }
    }
</script>