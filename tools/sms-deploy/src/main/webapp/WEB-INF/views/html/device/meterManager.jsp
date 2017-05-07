<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head></head>
<body>
<!-- 仪表模块-->
<div id="meterDiv" v-if="meterManager.isShow">
    <!--button -->
    <el-button type="primary" size="small" @click="meterManager.addMeterVisible = true">添加</el-button>

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

    <el-button type="primary" size="small" @click="setManagerCenter">管理中心设置
    </el-button>

    <el-button type="primary" size="small"
               @click="setTiming">定时上传设置
    </el-button>

    <el-button type="primary" size="small"
               @click="timeUpload">激活延时时间
    </el-button>

    <!--table-->
    <template>
        <el-table stripe
                  border
                  :data="meterManager.data"
                  highlight-current-row
                  @current-change="handleMeterChange"
                  style="width: 100%;height:100%">

            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>

            <el-table-column
                    property="meterCode"
                    label="编号"
                    width="200">
            </el-table-column>

            <el-table-column
                    property="meterName"
                    label="仪表名称"
                    width="200">
            </el-table-column>

            <el-table-column
                    width="200"
                    property="lastValue"
                    label="当前数据">
            </el-table-column>
            <el-table-column
                    width="200"
                    property="unit"
                    label="单位">
            </el-table-column>
            <el-table-column
                    width="200"
                    property="lastValveStatus"
                    label="当前阀状态">
            </el-table-column>

            <el-table-column
                    width="200"
                    property="lastUpLoadTime"
                    label="当前上传时间">
            </el-table-column>

            <%--<el-table-column--%>
                    <%--width="200"--%>
                    <%--property="mc1"--%>
                    <%--label="管理中心号码1">--%>
            <%--</el-table-column>--%>
            <%--<el-table-column--%>
                    <%--width="200"--%>
                    <%--property="mc2"--%>
                    <%--label="管理中心号码2">--%>
            <%--</el-table-column>--%>
            <%--<el-table-column--%>
                    <%--width="200"--%>
                    <%--property="mc3"--%>
                    <%--label="管理中心号码3">--%>
            <%--</el-table-column>--%>
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
                    <%--<el-button--%>
                    <%--size="small"--%>
                    <%--type="danger"--%>
                    <%--@click="handleDelete(scope.$index, scope.row)">删除--%>
                    <%--</el-button>--%>
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

<el-dialog title="设置管理中心号码" :close-on-click-modal="false" size="tiny" v-model="meterManager.managerCenterVisible">
    <el-form :model="meterManager.form">
        <el-form-item label="管理中心号码1">
            <el-input v-model="meterManager.mc1" placeholder="请输入管理中心号码"></el-input>
        </el-form-item>
        <el-form-item label="管理中心号码2">
            <el-input v-model="meterManager.mc2" placeholder="请输入管理中心号码"></el-input>
        </el-form-item>
        <el-form-item label="管理中心号码3">
            <el-input v-model="meterManager.mc3" placeholder="请输入管理中心号码"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="meterManager.managerCenterVisible = false" :disabled="meterManager.disabledManagerCenter">取
            消
        </el-button>
        <el-button type="primary" :loading="meterManager.loadManagerCenter"
                   @click="setManagerCenterData">确 定
        </el-button>
    </div>
</el-dialog>

<!--添加设备 -->
<el-dialog title="添加仪表设备" size="tiny" :close-on-click-modal="false" v-model="meterManager.addMeterVisible">
    <el-form :model="meterManager.form1">

        <el-input v-model="meterManager.addMeterData.meterCode" placeholder="请输入手机号"></el-input>

        <el-input v-model="meterManager.addMeterData.meterName" placeholder="请输入仪表名称"></el-input>

        <el-select v-model="meterManager.addMeterData.controllerId">
            <el-option label="请选择gsm" value="0"></el-option>
            <el-option v-for="gsm in this.meterManager.gsmData" :label="gsm.name" :value="gsm.id"></el-option>
        </el-select>

        <el-date-picker
                v-model="meterManager.addMeterData.fixDate"
                type="date"
                placeholder="选择安装日期">
        </el-date-picker>

        <el-select v-model="meterManager.addMeterData.unit">
            <el-option label="吨" value="吨"></el-option>
        </el-select>

    </el-form>

    <div slot="footer" class="dialog-footer">
        <el-button @click="meterManager.addMeterVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMeter">确 定</el-button>
    </div>
</el-dialog>

<!--添加设备 -->
<el-dialog title="定时上传设置" :close-on-click-modal="false" size="tiny" v-model="meterManager.timingVisible">
    <el-form :model="meterManager.form2">

        <el-select v-model="meterManager.day1" >
            <el-option v-for="item in days" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing1"
                placeholder="定时点1">
        </el-time-picker>
        <el-switch
                v-model="meterManager.on1"
                on-color="#13ce66"
                off-color="#ff4949">
        </el-switch>

        <el-select v-model="meterManager.day2">
            <el-option v-for="item in days" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing2"
                placeholder="定时点2">
        </el-time-picker>
        <el-switch
                v-model="meterManager.on2"
                on-color="#13ce66"
                off-color="#ff4949">
        </el-switch>

        <el-select v-model="meterManager.day3">
            <el-option v-for="item in days" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing3"
                placeholder="定时点3">
        </el-time-picker>
        <el-switch
                v-model="meterManager.on3"
                on-color="#13ce66"
                off-color="#ff4949">
        </el-switch>

        <el-select v-model="meterManager.day4">
            <el-option v-for="item in days" :label="item.label" :value="item.value"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing4"
                placeholder="定时点4">
        </el-time-picker>
        <el-switch
                v-model="meterManager.on4"
                on-color="#13ce66"
                off-color="#ff4949">
        </el-switch>

    </el-form>

    <div slot="footer" class="dialog-footer">
        <el-button @click="meterManager.timingVisible = false" :disabled="meterManager.disabledTiming">取 消</el-button>
        <el-button type="primary" :loading="meterManager.loadTiming"  @click="setTimingData">确 定</el-button>
    </div>
</el-dialog>


<el-dialog :title="title" :close-on-click-modal="false"
           v-model="dialogVisible"
           size="tiny">
    <span>{{waitDataMsg}}</span>
    </span>
</el-dialog>

</body>
</html>