<template>
    <div>
        <user-dialog v-model="dialogShow" :type="dialogType" :menus="menu"></user-dialog>
        <el-table  :data="tableData" style="width: 100%">
            <el-table-column prop="name" label="岗位姓名"></el-table-column>
            <el-table-column prop="remarks" label="备注"></el-table-column>
            <el-table-column label="权限菜单">
                <template scope="scope">
                    <el-tag v-for="item of scope.row.systemMenus" :key="item.id" type="primary">{{item.name}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="180px;">
                <template scope="scope">
                    <el-button
                            v-show="userPermission['system:updateRole']"
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            v-show="userPermission['system:deleteRole']"
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
    import {authorityJob,queryMenu,authorityJobDelete} from "../../../api/get"
    import listDialog from "../../../assets/js/mixins/listDialog"
    import { mapGetters } from 'vuex'
    export default {
        componentName:"authority.job",
        mixins:[Page,listDialog],
        components:{userDialog},
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        methods:{
            fetchListData() {
                authorityJob({params:Object.assign({},this.page)}).then(res => {
                    this.tableData = res.data.resultList
                    this.total = res.data.totalSize
                }).catch(res => {

                })
            },
            queryMenu() {
                queryMenu({params:{}}).then(res => {
                    this.menu = res.data
                }).catch(res => {

                })
            },
            handleDelete(index, row) {
                authorityJobDelete({params:{id:row.id}}).then(res => {
                    this.$message.success("删除成功")
                    this.tableData.splice(index,1)
                })
            }
        },
        created() {
            this.queryMenu()
        },
        data() {
            return {
                menu:[],
                tableData:[
                ]
            }
        }
    }
</script>