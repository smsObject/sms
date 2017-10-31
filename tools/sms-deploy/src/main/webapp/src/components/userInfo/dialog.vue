<template>
    <el-dialog title="用户信息登记" :visible.sync="infoDialog" @open="handleOpen">
        <el-form :model="form" ref="form" label-width="100px">
            <!--<div class="meter" v-if="type">-->
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="用户表号" :label-width="formLabelWidth" prop="meterId">
                            <el-select v-model="form.meterId" placeholder="请选择用户表号">
                                <el-option
                                        v-for="item in meterCodes"
                                        :key="item.id"
                                        :value="item.id"
                                        :label="item.meterCode">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="安装日期" :label-width="formLabelWidth" prop="fixTime">
                            <el-date-picker
                                    v-model="form.fixTime"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="仪表基数" :label-width="formLabelWidth" prop="basicValue">
                            <el-input  v-model="form.basicValue"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用水类型" :label-width="formLabelWidth" prop="useType">
                            <el-select v-model="form.useType" placeholder="请选择用水类型">
                                <el-option
                                        v-for="item in usageTypeNames"
                                        :key="item.id"
                                        :value="item.id"
                                        :label="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="计费项" :label-width="formLabelWidth" prop="buyType">
                            <el-select v-model="form.buyType" placeholder="请选择计费项">
                                <el-option
                                        v-for="(value,key) in buyTypeNames"
                                        :key="key"
                                        :label="value"
                                        :value="+key">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用户类型" :label-width="formLabelWidth" prop="userType">
                            <el-select v-model="form.userType" placeholder="请选择用户类型">
                                <el-option label="普通用户" :value="1"></el-option>
                                <el-option label="预付费用户" :value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>


            <!--</div>-->

            <el-row>
                <el-col :span="12">
                    <el-form-item label="用户姓名" :label-width="formLabelWidth" prop="name">
                        <el-input  v-model="form.name"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="开户日期" :label-width="formLabelWidth" prop="openingTime">
                        <el-date-picker
                                v-model="form.openingTime"
                                type="date"
                                placeholder="开户日期">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label="身份证号" :label-width="formLabelWidth" prop="idNo">
                        <el-input v-model="form.idNo"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
                        <el-input v-model="form.phone"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="24">
                    <el-form-item label="详细地址" :label-width="formLabelWidth" prop="address">
                        <el-input placeholder="请输入街道小区信息" v-model="form.address"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="支付方式" :label-width="formLabelWidth">
                        <el-checkbox label="现金支付" :disabled="true" v-model="payedByCash"></el-checkbox>
                        <el-checkbox label="银行卡支付" v-model="payedByBank"></el-checkbox>
                        <el-checkbox label="电话支付" v-model="payByPhone"></el-checkbox>
                        <el-checkbox label="手机支付" v-model="payByMobilPhone"></el-checkbox>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="代缴电话" :label-width="formLabelWidth">
                        <el-input :disabled="true" placeholder="缴费电话同步联系电话" v-model="form.phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="代缴手机" prop="mobilePhone">
                        <el-input v-model="form.mobilePhone"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="代缴银行" :label-width="formLabelWidth" prop="paymentBank">
                        <el-input v-model="form.paymentBank"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="银行账号" prop="bankAccount">
                        <el-input v-model="form.bankAccount"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="24">
                    <el-form-item label="备注" :label-width="formLabelWidth" prop="remarks">
                        <el-input type="textarea" :autosize="{minRows: 4}" v-model="form.remarks" ></el-input>
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
    import Emitter from "../../assets/js/mixins/emitter"
    import {userInfoAdd,userInfoUpdate,queryPriceNames,listUseType,queryNoCorrelationMeters} from "../../api/get"
    function hasZero(o) {
        return o < 10 ? "0"+o : o
    }
    let format = (now) => {
        if(typeof now === "string") return now
        var   year=now.getYear()+1900;
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        return   hasZero(year)+"-"+hasZero(month)+"-"+hasZero(date)
    }
    export default {
        mixins:[formDialog,Emitter],
        created() {
            queryPriceNames({params:{}}).then(res =>　{
                this.buyTypeNames = res.data
            })
            listUseType({params:{}}).then(res => {
                this.usageTypeNames = res.data
            })
        },

        methods:{
            handleOpen() {
                if(!this.type){
                    queryNoCorrelationMeters({params:{}}).then(res => {
                        this.meterCodes = res.data
                    })
                    this.$refs.form && this.$refs.form.resetFields()
                    this.payedByBank = false
                    this.payByPhone = false
                    this.payByMobilPhone = false
                }else {
                    if(!this.$refs.form){
                        this.$nextTick(()=>{
                            this.form = Object.assign({},this.$parent.dialogModel)
                            queryNoCorrelationMeters({params:{userId:this.form.id}}).then(res => {
                                this.meterCodes = res.data
                            })
                            this.form.payType && this.form.payType.split(",").forEach(item => {
                                if(item == 2){
                                    this.payedByBank = true
                                }else if(item == 3){
                                    this.payByPhone = true
                                }
                                else if(item == 4){
                                    this.payByMobilPhone = true
                                }
                            })
                        })
                    }else {
                        this.form = Object.assign({},this.$parent.dialogModel)
                        queryNoCorrelationMeters({params:{userId:this.form.id}}).then(res => {
                            this.meterCodes = res.data
                        })
                        this.form.payType && this.form.payType.split(",").forEach(item => {
                            if(item == 2){
                                this.payedByBank = true
                            }else if(item == 3){
                                this.payByPhone = true
                            }
                            else if(item == 4){
                                this.payByMobilPhone = true
                            }
                        })
                    }
                }
            },
            confirm(){
                let arr = [1]
                if(this.payedByBank) arr.push(2)
                if(this.payByPhone) arr.push(3)
                if(this.payByMobilPhone) arr.push(4)
                var params = Object.assign({},this.form,{payType:arr+"",fixTime:format(this.form.fixTime),openingTime:format(this.form.openingTime)})
                delete params.updateTime
                delete params.createTime
                if(this.type){
                    userInfoUpdate({params}).then(res => {
                        this.$message.success("编辑成功")
                        this.infoDialog = false
                        this.dispatch("userInfo.list","update-list")
                    })
                }else {
                    userInfoAdd({params}).then(res => {
                        this.$message.success("添加成功")
                        this.infoDialog = false
                        this.dispatch("userInfo.list","update-list")
                    })
                }

            },

        },
        data() {
            return {
                province:"",
                city:"",
                region:"",
                detailAddress:"",
                buyTypeNames:{},
                usageTypeNames:[],
                formLabelWidth: '80px',
                //支付方式
                payedByCash:true,
                payedByBank:false,
                payByPhone:false,
                payByMobilPhone:false,
                meterCodes:[],
                form:{
                    meterId:"",
                    usageType:"",
                    basicValue:"",
                    name: '',
                    userType:"",
                    buyType: '',
                    address: '',
                    phone: '',
                    mobilePhone: '',
                    fixTime: '',
                    openingTime: '',
                    basicAllowance: '',
                    remainingBalance: '',
                    status: '',
                    valveStatus: '',
                    idNo: '',
                    payType: '',
                    remarks: '',
                },
                infoDialog:false
            }
        }
    }
</script>