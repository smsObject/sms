<template>
    <div class="table-wrap">
        <el-breadcrumb style="padding-bottom:20px;" separator="/">
            <el-breadcrumb-item v-for="(item,index) in breadcrumbName" :key="index">{{item}}</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 仪表数据模块-->
        <div id="meterDataDiv">
            <!--table-->
            <template>
                <el-table stripe
                          border
                          :data="data"
                          highlight-current-row
                          height="500"
                          style="width: 100%">
                    <el-table-column
                            type="index"
                            width="50">
                    </el-table-column>
                    <el-table-column
                            property="code"
                            label="手机号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            property="value"
                            label="值"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            width="90"
                            property="unit"
                            label="单位">
                    </el-table-column>
                    <el-table-column
                            width="90"
                            property="valveStatusName"
                            label="阀状态">
                    </el-table-column>
                    <el-table-column
                            property="createTime"
                            label="创建时间">
                    </el-table-column>
                    <el-table-column
                            property="createPerson"
                            label="创建人">
                    </el-table-column>
                </el-table>
            </template>
            <!--page-->
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
    </div>
</template>

<script type="text/babel">
    import Page from "../../assets/js/mixins/page"
    import {formatDate} from 'util'
    import {deviceData} from "../../api/get"
    export default {
        mixins:[Page],
        data() {
            return {
                breadcrumbName:["我的设备","数据"],
                data: [],
                total: 20,
                row: null
            }
        },
        methods:{
            fetchListData() {
                deviceData({params:{}}).then(e => {
                    let data = e.data
                    if (e.success) {
                        this.data  = data.resultList ? data.resultList.map(item => {
                            item.createTime     = formatDate(item.createTime)
                            return item
                        }) : []
                        this.total = data.totalSize;
                    } else {
                        this.$message(e.statusText);
                    }
                })
            },
        }
    }
</script>