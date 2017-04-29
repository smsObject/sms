<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head></head>
<body>
<!-- 仪表模块-->
<div id="meterDiv" v-if="meterManager.isShow">
    <!--button -->
    <el-button type="primary" size="small"
               :loading="meterManager.loadReadMeter" @click="readMeter"
               :disabled="meterManager.disabledReadMeter">抄表
    </el-button>

    <el-button type="primary" size="small"
               :loading="meterManager.loadOpenMeter" @click="openMeter"
               :disabled="meterManager.disabledOpenMeter">开阀
    </el-button>

    <el-button type="primary" size="small"
               :loading="meterManager.loadCloseMeter" @click="closeMeter"
               :disabled="meterManager.disabledCloseMeter">关阀
    </el-button>

    <!--table-->
    <template>
        <el-table stripe
                  border
                  :data="meterManager.data"
                  highlight-current-row
                  @current-change="handleMeterChange"
                  <%--height="500"--%>
                  style="width: 100%;height:100%">
            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    property="meterCode"
                    label="手机号"
                    width="200">
            </el-table-column>
            <el-table-column
                    property="controllerName"
                    label="集中器"
                    width="200">
            </el-table-column>
            <el-table-column
                    property="meterName"
                    label="仪表名称"
                    width="200">
            </el-table-column>
            <el-table-column
                    width="200"
                    property="unit"
                    label="单位">
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
                    :current-page="meterManager.page"
                    :page-sizes="[20, 50, 100, 150]"
                    :page-size="20"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="meterManager.total">
            </el-pagination>
        </div>
    </template>
</div>
</body>
</html>