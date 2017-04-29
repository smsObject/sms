<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head></head>
<body>
<!-- 仪表数据模块-->
<div id="meterDataDiv" v-if="meterDataManager.isShow">
    <!--table-->
    <template>
        <el-table stripe
                  border
                  :data="meterDataManager.data"
                  highlight-current-row
                  @current-change="handleMeterDataChange"
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
                    property="createDate"
                    label="创建时间">
            </el-table-column>
            <el-table-column
                    property="createPerson"
                    label="创建人">
            </el-table-column>
        </el-table>
    </template>
    <!--page-->
    <template>
        <div class="block">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="meterDataManager.page"
                    :page-sizes="[20, 50, 100, 150]"
                    :page-size="20"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="meterDataManager.total">
            </el-pagination>
        </div>
    </template>
</div>
</body>
</html>