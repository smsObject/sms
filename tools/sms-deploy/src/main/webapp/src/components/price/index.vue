<template>
    <div class="table-wrap">
        <div class="flex-sb" style="padding-bottom:20px;">
            <el-breadcrumb separator=">">
                <el-breadcrumb-item :to="item.view" :key="index" v-for="(item,index) of breadcrumbName">{{item.name}}</el-breadcrumb-item>
            </el-breadcrumb>
            <div class="float-right">
                <step-btn v-show="userPermission['setting:addUseType']"></step-btn>
                <water-btn v-show="userPermission['setting:listUseType']"></water-btn>
                <price-btn v-show="userPermission['setting:listPriceTemplates']" ></price-btn>
            </div>
        </div>
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column label="用户类型" width="150" prop="name"></el-table-column>
            <el-table-column label="类型描述" prop="remarks"></el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button
                            v-show="userPermission['setting:updateUseType']"
                            size="small"
                            type="info"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            v-show="userPermission['setting:deleteUseType']"
                            size="small"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script type="text/babel">
    import Emitter from "../../assets/js/mixins/emitter"
    import stepBtn from "./step/stepBtn"
    import waterBtn from "./water/waterBtn"
    import {listUseType,deleteUseType} from "../../api/get"
    import priceBtn from "./price/priceBtn"
    import { mapGetters } from 'vuex'
    export default {
        mixins:[Emitter],
        componentName:"price.list",
        data() {
            return {
                breadcrumbName:[
                    {name:"用户管理"},
                    {name:"价格设置",view:{name:"priceList"}}
                ],
                tableData:[]
            }
        },
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        methods:{
            fetchListData() {
                listUseType({params:{}}).then(res => {
                    this.tableData = res.data
                })
            },
            handleDelete(index, row) {
                this.$confirm('此操作将永久删除该用水类型, 是否继续?', '提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deleteUseType({params:{id:row.id}}).then(res => {
                        this.$message.success("删除成功")
                        this.tableData.splice(index,1)
                    })
                }).catch(() => {})

            },
            handleEdit(index,row){
                this.broadcast("price.water","show-dialog",[index,row])
            }
        },
        created() {
            this.dispatch("app","active-nav",[this.$route.name])
            this.$on("fetch",this.fetchListData)
            this.fetchListData()
        },
        components:{
            stepBtn:stepBtn,
            waterBtn:waterBtn,
            priceBtn:priceBtn
        }
    }
</script>