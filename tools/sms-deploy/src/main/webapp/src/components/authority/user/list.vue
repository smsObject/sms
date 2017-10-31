<template>
    <div>
        <user-dialog v-model="dialogShow" :type="dialogType"></user-dialog>
        <el-table  :data="tableData" style="width: 100%">
            <el-table-column prop="name" label="账户姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="roleName" label="所属岗位"></el-table-column>
            <el-table-column label="操作" width="180px;">
                <template scope="scope">
                    <el-button
                            v-show="userPermission['system:updateUser']"
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            v-show="userPermission['system:deleteUser']"
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
    import userDialog from "./dialog"
    import Page from "../../../assets/js/mixins/page"
    import {authorityUser,authorityUserDelete} from "../../../api/get"
    import listDialog from "../../../assets/js/mixins/listDialog"
    import { mapGetters } from 'vuex'
    export default {
        componentName:"authority.user",
        mixins:[Page,listDialog],
        components:{userDialog},
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        methods:{
            fetchListData() {
                authorityUser({params:Object.assign({},this.page)}).then(res => {
                    this.tableData = res.data.resultList
                    this.total = res.data.totalSize
                }).catch(res => {

                })
            },
            handleDelete(index,row) {
                authorityUserDelete({params:{id:row.id}}).then(res => {
                    this.$message.success("删除成功")
                    this.tableData.splice(index,1)
                })
            }
        },
        data() {
            return {
                tableData:[]
            }
        }
    }
</script>