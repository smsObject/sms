<template>
    <el-form label-width="100px" >
        <div class="business">
            <el-form-item label="用户名称">
                <el-input  v-model="form.userName"></el-input>
            </el-form-item>
            <el-form-item label="用户表号">
                <el-input  v-model="form.meterCode"></el-input>
            </el-form-item>
            <el-form-item label="安装地址" class="address">
                <el-input  v-model="form.address" disabled></el-input>
            </el-form-item>
            <div class="search">
                <el-button icon="search" type="info" @click="handleSearch">搜索</el-button>
                <el-button icon="submit" type="danger" @click="handleSubmit">提交</el-button>
            </div>
        </div>
        <div class="business">
            <el-form-item label="用户类型">
                <el-select v-model="form.userType" disabled placeholder="请选择用户类型">
                    <el-option label="普通用户" :value="1"></el-option>
                    <el-option label="预付费用户" :value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="购买数量">
                <el-input  v-model="form.buyWaterValue" :disabled="form.userType == 1" @blur="handleBlur"></el-input>
            </el-form-item>
            <el-form-item label="应付金额">
                <el-input  v-model="form.payableAmount" disabled></el-input>
            </el-form-item>
            <el-form-item label="实收金额">
                <el-input  v-model="form.realAmount"></el-input>
            </el-form-item>
            <el-form-item label="找赎">
                <el-input  v-model="getbackAmount" disabled></el-input>
            </el-form-item>
        </div>
        <div class="business">
            <el-form-item label="上次购买量">
                <el-input  v-model="form.lastBuyWaterValue" disabled></el-input>
            </el-form-item>
            <el-form-item label="上次购买日期">
                <el-input  v-model="form.lastBuyWaterTime" disabled></el-input>
            </el-form-item>
        </div>
    </el-form>
</template>

<script type="text/babel">

    import {formatDate} from "util"

    import {listUseType,getUserWaterValue,buyWaterPrice,calculateWaterPrice} from "../../api/get"
    export default {
        data() {
            return {
                usageTypeNames:[],
                form:{
                    userName:"",
                    meterCode:"",
                    address:"",
                    userType:"",
                    buyWaterValue:"",
                    realAmount:"",
                    payableAmount:"",
                    lastBuyWaterValue:"",
                    lastBuyWaterTime:"",
                }
            }
        },
        computed:{
            getbackAmount:function() {
                return (this.form.realAmount || 0) - (this.form.payableAmount || 0)
            }
        },
        methods:{
            handleSearch() {
                getUserWaterValue({params:{userName:this.form.userName,meterCode:this.form.meterCode}}).then(res => {
                    if(res.success){
                        res.data.lastBuyWaterTime = formatDate(res.data.lastBuyWaterTime)
                        this.form = res.data
                    }
                })
            },
            handleBlur() {
                calculateWaterPrice({params:{userId:this.form.userId,waterValue:this.form.buyWaterValue}}).then(res => {
                    if(res.success){
                        this.form.payableAmount = res.data
                    }
                })
            },
            handleSubmit() {
                this.$confirm('是否保存修改', '提示',{
                    confirmButtonText: '保存',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({},this.form,{getbackAmount:this.getbackAmount})
                    delete params.updateTime
                    delete params.createTime
                    delete params.lastBuyWaterTime
                    delete params.lastBuyWaterValue
                    delete params.id
                    buyWaterPrice({params}).then(res => {
                        if(res.success){
                            this.$message({
                                type: 'success',
                                message: '保存成功'
                            });
                        }
                    })

                }).catch(() => {});
            }
        }
    }
</script>

<style lang="scss" rel="stylesheet/scss">
    .business{
        display: flex;
        justify-content: center;
        .el-form-item{
            display: inline-block;
        }
        .el-input{
            width:160px;
        }
        .search{
            display: inline-block;
            padding-left:20px;
        }
        .address{
            .el-input{
                width:300px;
            }
        }
    }
</style>