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
                    label="最后数据">
            </el-table-column>

            <el-table-column
                    width="200"
                    property="lastValveStatus"
                    label="最后阀状态">
            </el-table-column>

            <el-table-column
                    width="200"
                    property="lastUpLoadTime"
                    label="最后上传时间">
            </el-table-column>

            <el-table-column
                    width="200"
                    property="unit"
                    label="单位">
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

<el-dialog title="设置管理中心号码" size="tiny" v-model="meterManager.managerCenterVisible">
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
<el-dialog title="添加仪表设备" size="tiny" v-model="meterManager.addMeterVisible">
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
<el-dialog title="定时上传设置" size="tiny" v-model="meterManager.timingVisible">
    <el-form :model="meterManager.form2">

        <el-select v-model="meterManager.day1" >
            <el-option label="请选择日期" value="0"></el-option>
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
            <el-option label="5" value="5"></el-option>
            <el-option label="6" value="6"></el-option>
            <el-option label="7" value="7"></el-option>
            <el-option label="8" value="8"></el-option>
            <el-option label="9" value="9"></el-option>
            <el-option label="10" value="10"></el-option>
            <el-option label="11" value="11"></el-option>
            <el-option label="12" value="12"></el-option>
            <el-option label="13" value="13"></el-option>
            <el-option label="14" value="14"></el-option>
            <el-option label="15" value="15"></el-option>
            <el-option label="16" value="16"></el-option>
            <el-option label="17" value="17"></el-option>
            <el-option label="18" value="18"></el-option>
            <el-option label="19" value="19"></el-option>
            <el-option label="20" value="20"></el-option>
            <el-option label="21" value="21"></el-option>
            <el-option label="22" value="22"></el-option>
            <el-option label="23" value="23"></el-option>
            <el-option label="24" value="24"></el-option>
            <el-option label="25" value="25"></el-option>
            <el-option label="26" value="26"></el-option>
            <el-option label="27" value="27"></el-option>
            <el-option label="28" value="28"></el-option>
            <el-option label="29" value="29"></el-option>
            <el-option label="30" value="30"></el-option>
            <el-option label="31" value="31"></el-option>
        </el-select>

        <el-time-picker
                v-model="meterManager.timing1"
                placeholder="定时点1">
        </el-time-picker>

        <el-select v-model="meterManager.day2">
            <el-option label="请选择日期" value="0"></el-option>
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
            <el-option label="5" value="5"></el-option>
            <el-option label="6" value="6"></el-option>
            <el-option label="7" value="7"></el-option>
            <el-option label="8" value="8"></el-option>
            <el-option label="9" value="9"></el-option>
            <el-option label="10" value="10"></el-option>
            <el-option label="11" value="11"></el-option>
            <el-option label="12" value="12"></el-option>
            <el-option label="13" value="13"></el-option>
            <el-option label="14" value="14"></el-option>
            <el-option label="15" value="15"></el-option>
            <el-option label="16" value="16"></el-option>
            <el-option label="17" value="17"></el-option>
            <el-option label="18" value="18"></el-option>
            <el-option label="19" value="19"></el-option>
            <el-option label="20" value="20"></el-option>
            <el-option label="21" value="21"></el-option>
            <el-option label="22" value="22"></el-option>
            <el-option label="23" value="23"></el-option>
            <el-option label="24" value="24"></el-option>
            <el-option label="25" value="25"></el-option>
            <el-option label="26" value="26"></el-option>
            <el-option label="27" value="27"></el-option>
            <el-option label="28" value="28"></el-option>
            <el-option label="29" value="29"></el-option>
            <el-option label="30" value="30"></el-option>
            <el-option label="31" value="31"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing2"
                placeholder="定时点2">
        </el-time-picker>

        <el-select v-model="meterManager.day3">
            <el-option label="请选择日期" value="0"></el-option>
            <el-option label="1" value="1"></el-option>
            <el-option label="2" value="2"></el-option>
            <el-option label="3" value="3"></el-option>
            <el-option label="4" value="4"></el-option>
            <el-option label="5" value="5"></el-option>
            <el-option label="6" value="6"></el-option>
            <el-option label="7" value="7"></el-option>
            <el-option label="8" value="8"></el-option>
            <el-option label="9" value="9"></el-option>
            <el-option label="10" value="10"></el-option>
            <el-option label="11" value="11"></el-option>
            <el-option label="12" value="12"></el-option>
            <el-option label="13" value="13"></el-option>
            <el-option label="14" value="14"></el-option>
            <el-option label="15" value="15"></el-option>
            <el-option label="16" value="16"></el-option>
            <el-option label="17" value="17"></el-option>
            <el-option label="18" value="18"></el-option>
            <el-option label="19" value="19"></el-option>
            <el-option label="20" value="20"></el-option>
            <el-option label="21" value="21"></el-option>
            <el-option label="22" value="22"></el-option>
            <el-option label="23" value="23"></el-option>
            <el-option label="24" value="24"></el-option>
            <el-option label="25" value="25"></el-option>
            <el-option label="26" value="26"></el-option>
            <el-option label="27" value="27"></el-option>
            <el-option label="28" value="28"></el-option>
            <el-option label="29" value="29"></el-option>
            <el-option label="30" value="30"></el-option>
            <el-option label="31" value="31"></el-option>
        </el-select>
        <el-time-picker
                v-model="meterManager.timing3"
                placeholder="定时点3">
        </el-time-picker>

    </el-form>

    <div slot="footer" class="dialog-footer">
        <el-button @click="meterManager.timingVisible = false" :disabled="meterManager.disabledTiming">取 消</el-button>
        <el-button type="primary" :loading="meterManager.loadTiming"  @click="setTimingData">确 定</el-button>
    </div>
</el-dialog>















<el-dialog :title="title"
           v-model="dialogVisible"
           size="tiny">
    <span>{{waitDataMsg}}</span>
    </span>
</el-dialog>

</body>
</html>