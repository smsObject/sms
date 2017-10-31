<template>
    <div class="table-wrap">
        <div class="form-wrap float-right btn-wrap">
            <el-button size="small" @click="handleAdd">新增公司</el-button>
            <company-dialog v-model="dialogShow" :type="dialogType"></company-dialog>
        </div>

        <el-table  :data="tableData" style="width: 100%">
            <el-table-column prop="companyName" label="公司名称"></el-table-column>
            <el-table-column prop="companyCode" label="公司代码"></el-table-column>
            <el-table-column prop="serviceCall" label="服务电话"></el-table-column>
            <el-table-column prop="serviceFax" label="服务传真"></el-table-column>
            <el-table-column prop="serviceEmail" label="服务邮箱"></el-table-column>
            <el-table-column prop="complaintCall" label="报障电话"></el-table-column>
            <el-table-column prop="serviceWebsite" label="服务网站"></el-table-column>
            <el-table-column prop="address" label="联系地址" ></el-table-column>
            <el-table-column prop="zipCode" label="邮编"></el-table-column>
            <el-table-column prop="contactPerson" label="业务联系人"></el-table-column>
            <el-table-column prop="taxNo" label="企业税号"></el-table-column>
            <!--<el-table-column prop="bankName" label="开户行"></el-table-column>-->
            <!--<el-table-column prop="bankAccount" label="银行账号"></el-table-column>-->
            <!--<el-table-column prop="allocatedRegion" label="所在区域"></el-table-column>-->
            <el-table-column prop="totalAmount" label="辖区用户总量"></el-table-column>
            <el-table-column prop="regularUseAmount" label="仪表正常使用数量"></el-table-column>
            <el-table-column label="操作" width="180px;">
                <template scope="scope">
                    <el-button
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
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
    import ListDialog from "../../assets/js/mixins/listDialog"
    import Emitter from "../../assets/js/mixins/emitter"
    import {companyInfoList,companyInfoDelete} from "../../api/get"
    import companyDialog from "./dialog"
    export default {
        mixins:[Page,ListDialog,Emitter],
        methods:{
            fetchListData() {
                companyInfoList({params:Object.assign({},this.page)}).then(res => {
                    this.tableData = res.data.resultList
                    this.total = res.data.totalSize
                }).catch(res =>{
                    console.log(res)
                })
            },
            handleDelete(index, row) {
                companyInfoDelete({params:{id:row.id}}).then(res => {
                    this.$message.success("删除成功")
                })
            }
        },
        components:{companyDialog},
        created() {
            this.dispatch("app","active-nav",[this.$route.name])
        },
        data() {
            return {
                tableData:[]
            }
        },
    }
</script>