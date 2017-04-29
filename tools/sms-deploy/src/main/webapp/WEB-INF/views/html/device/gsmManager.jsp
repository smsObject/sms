<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head></head>
<body>
<!-- gsm 模块-->
<div id="gsmDiv" v-if="gsmManager.isShow">
    <!--button -->
    <el-button type="primary" size="small" :loading="loadSearchGsm" icon="search" @click="searchGsmFun">
        扫描
    </el-button>
    <!--table -->
    <template>
        <el-table stripe
                  border
                  :data="gsmManager.data"
                  highlight-current-row
                  @current-change="handleCurrentChange"
                  <%--height="500"--%>
                  style="width: 100%;height:100%">
            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    property="name"
                    label="名称"
                    width="200">
            </el-table-column>
            <el-table-column
                    property="comPort"
                    label="组件名"
                    width="200"
            >
            </el-table-column>
            <el-table-column
                    property="baudRate"
                    label="波特率"
                    width="200"
            >
            </el-table-column>
            <el-table-column
                    width="200"
                    property="manufacturer"
                    label="生产商">
            </el-table-column>
            <el-table-column
                    width="200"
                    property="model"
                    label="型号">
            </el-table-column>
            <el-table-column
                    property="createTime"
                    label="创建时间">
            </el-table-column>
            <el-table-column
                    property="createPerson"
                    label="创建人">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button
                            size="small"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="small"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
    <!--page-->
    <template>
        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="gsmManager.page"
                    :page-sizes="[20, 50, 100, 150]"
                    :page-size="20"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="gsmManager.total">
            </el-pagination>
        </div>
    </template>
</div>
</body>
</html>