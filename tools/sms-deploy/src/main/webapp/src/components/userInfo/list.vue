<template>
    <div class="table-wrap">
        <div class="flex-sb">
            <el-breadcrumb separator=">">
                <el-breadcrumb-item :to="item.view" :key="index" v-for="(item,index) of breadcrumbName">{{item.name}}</el-breadcrumb-item>
            </el-breadcrumb>
            <div class="form-wrap float-right btn-wrap">
                <el-button v-show="userPermission['userInfo:add']" size="small" @click="handleAdd">新增用户</el-button>
                <user-dialog v-model="dialogShow" :type="dialogType"></user-dialog>
            </div>
        </div>
        <div class="search">
            <el-form label-width="100px">
                <el-form-item label="用户姓名" style="width:300px;float:left;margin-right:20px;">
                    <el-input  v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="联系手机" style="width:300px;float:left;margin-right:20px;">
                    <el-input  v-model="form.mobilePhone"></el-input>
                </el-form-item>
                <el-form-item label="计费项" style="width:300px;float:left;margin-right:20px;">
                    <el-select v-model="form.buyType" placeholder="请选择计费项">
                        <el-option label="全部" :value="''"></el-option>
                        <el-option
                                v-for="(value,key) in buyTypeNames"
                                :key="key"
                                :label="value"
                                :value="+key">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-button style="float:left;" icon="search" type="info" @click="handleSearch">搜索</el-button>
            </el-form>
        </div>
        <el-table  :data="tableData" style="width: 100%">
            <el-table-column prop="name" label="用户姓名"></el-table-column>
            <el-table-column prop="useTypeName" label="用水类型"></el-table-column>
            <el-table-column prop="buyTypeName" label="计费项"></el-table-column>
            <el-table-column prop="address" label="用户地址"></el-table-column>
            <el-table-column prop="phone" label="联系电话"></el-table-column>
            <el-table-column prop="mobilePhone" label="联系手机"></el-table-column>
            <el-table-column prop="fixTime" label="安装日期"></el-table-column>
            <el-table-column prop="openingTime" label="开户日期"></el-table-column>
            <el-table-column prop="basicAllowance" label="基本余量"></el-table-column>
            <el-table-column prop="remainingBalance" label="结余金额"></el-table-column>
            <el-table-column prop="status" label="使用状态"></el-table-column>
            <el-table-column prop="valveStatus" label="阀门状态"></el-table-column>
            <el-table-column prop="idNo" label="身份证号"></el-table-column>
            <el-table-column prop="payTypeName" label="支付方式"></el-table-column>
            <el-table-column prop="remarks" label="备注"></el-table-column>
            <el-table-column label="操作" width="180px;">
                <template scope="scope">
                    <el-button
                            v-show="userPermission['userInfo:update']"
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            v-show="userPermission['userInfo:delete']"
                            size="small"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-wrap">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNo"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script type="text/babel">
    import Page from "../../assets/js/mixins/page"
    import FormDialog from "../../assets/js/mixins/listDialog"
    import Emitter from "../../assets/js/mixins/emitter"
    import { mapGetters } from 'vuex'

    import userDialog from "./dialog"
    import {userInfoList,userInfoDelete,queryPriceNames} from "../../api/get"
    export default {
        componentName:"userInfo.list",
        mixins:[Page,FormDialog,Emitter],
        methods:{
            fetchListData() {
                userInfoList({params:Object.assign({},this.page,this.form)}).then(res => {
                    this.tableData = res.data.resultList ? res.data.resultList.map(item => {
                        item.fixTime = item.fixTime.slice(0,10)
                        item.openingTime = item.openingTime.slice(0,10)
                        return item
                    }) : []
                    this.total = res.data.totalSize
                }).catch(res =>{

                })
            },
            handleDelete(index, row) {
                userInfoDelete({params:{id:row.id}}).then(res => {
                    this.$message.success("删除成功")
                })
            },
            handleSearch() {
                this.fetchListData()
            }
        },
        components:{userDialog},
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        created() {
            this.dispatch("app","active-nav",[this.$route.name])
            queryPriceNames({params:{}}).then(res =>　{
                this.buyTypeNames = res.data
            })
        },
        data() {
            return {
                breadcrumbName:[
                    {name:"用户管理"},
                    {name:"用户信息",view:{name:"userInfoList"}}
                ],
                buyTypeNames:{},
                form:{userName:"",mobilPhone:"",buyType:""},
                model:{},
                tableData:[]
            }
        }
    }
</script>
