<template>
    <el-dialog title="费用项设置" :visible.sync="infoDialog" @open="handleOpen" size="tiny">
        <el-form :model="form" ref="form">
            <el-collapse v-model="activeNames" accordion>
                <el-collapse-item v-for="(item,index) in form.cost" :key="index" :title="item.name" :name="index">
                    一级名称：<tag :value="item.name" @input="handleAddUpdate" :index="index" :closable="false"></tag>
                    <el-button @click="handleDelete(index)" size="mini" type="danger" icon="delete">删除</el-button>
                    <br>
                    <tags :tags="item.childs" :parent-id="item.id || 0"></tags>
                </el-collapse-item>
            </el-collapse>
        </el-form>
        <div>
            <el-button type="primary" icon="edit" @click="handleAdd">添加</el-button>
        </div>
        <div slot="footer" class="dialog-footer">

            <el-button @click="infoDialog = false">取 消</el-button>
        </div>
    </el-dialog>
</template>
<script type="text/babel">
    import formDialog from "../../../assets/js/mixins/formDialog"
    import {numberToChinese} from "../../../assets/js/utils/util"
    import {listPriceTemplates,addAndUpdatePriceTemplate,deletePriceTemplate} from "../../../api/get"
    import tags from "./tags"
    import tag from "./tag"
    export default {
        mixins:[formDialog],
        data() {
            return {
                activeNames:-1,
                infoDialog:false,
                formLabelWidth:'100px',
                types:[
                    {label:"月度",value:"monthly"},
                    {label:"季度",value:"quarter"},
                    {label:"半年",value:"halfYear"},
                    {label:"年度",value:"annually"},
                ],
                form:{
                    year:new Date(),
                    type:"monthly",
                    cost:[]
                },
            }
        },
        components:{tags,tag},
        methods:{
            confirm() {
                // 成功回调
            },
            handleOpen() {
                listPriceTemplates({params:{}}).then(res => {
                    this.form.cost = res.data.map(item =>{
                        return {
                            id:item.id,
                            name:item.name,
                            childs:item.childs
                        }
                    })
                })
            },

            handleAddUpdate(value,index) {
                var item = this.form.cost[index]
                addAndUpdatePriceTemplate({params:{id:item.id || "",name:value}}).then(res => {
                    this.$set(item,"id",res.data)
                    this.$set(item,"name",value)
                    this.$message.success("操作成功")
                }).catch(res => {
                    this.$message.error(res.statusText)
                })
            },

            handleDelete(index) {
                this.$confirm('此操作将永久删除该费用项, 是否继续?', '提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    return deletePriceTemplate({params:{id:this.form.cost[index].id}}).then(() => {
                        this.form.cost.splice(index,1)
                        this.$message({
                            type: 'success',
                            message: '删除成功'
                        });
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            handleAdd() {
                let cost = this.form.cost
                cost.push({name:"新增",childs:[]})
            }
        },
        filters:{
            numberToChinese:numberToChinese
        }
    }
</script>