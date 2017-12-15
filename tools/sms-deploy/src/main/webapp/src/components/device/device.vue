<template>
    <div class="table-wrap">
        <el-breadcrumb style="background: #eef1f6;padding: 10px;margin-bottom: 20px;" separator="/">
            <el-breadcrumb-item v-for="(item,index) in breadcrumbName" :key="index">{{item}}</el-breadcrumb-item>
        </el-breadcrumb>

        <div id="meterDiv">
            <div style="padding-bottom:20px;">
                <!--button -->
                <el-button v-show="userPermission['device:addMeter']"
                           type="primary" size="small" @click="addMeterVisible = true;update = false">添加</el-button>

                <el-button
                        v-show="userPermission['device:readMeterData']"
                        type="primary" size="small"
                        :loading="loadReadMeter" @click="readMeter"
                        :disabled="disabledReadMeter">抄表
                </el-button>

                <el-button
                        v-show="userPermission['device:setValveStatus']"
                        type="primary" size="small"
                        :loading="loadOpenMeter" @click="openMeter"
                        :disabled="disabledOpenMeter">开阀
                </el-button>

                <el-button
                        v-show="userPermission['device:setValveStatus']"
                        type="primary" size="small"
                       :loading="loadCloseMeter" @click="closeMeter"
                       :disabled="disabledCloseMeter">关阀
                </el-button>

                <el-button
                        v-show="userPermission['device:setManagerCenter']"
                        type="primary" size="small" @click="setManagerCenter">管理中心设置
                </el-button>

                <el-button
                        v-show="userPermission['device:setTiming']"
                        type="primary" size="small"
                        @click="setTiming">定时上传设置
                </el-button>

                <el-button
                        v-show="userPermission['device:setActivateTime']"
                        type="primary" size="small"
                        @click="setActivateTime">激活延时时间
                </el-button>
            </div>

            <div class="search">
                <el-form label-width="100px">
                    <el-form-item label="编号" style="width:300px;float:left;margin-right:20px;">
                        <el-input  v-model="form.meterCode"></el-input>
                    </el-form-item>
                    <el-button style="float:left;" icon="search" type="info" @click="handleSearch">搜索</el-button>
                </el-form>
            </div>
            <!--table-->
            <template>
                <el-table stripe
                          border
                          :data="data"
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
                                    v-show="userPermission['device:updateMeter']"
                                    size="small"
                                    @click="handleEdit(scope.$index, scope.row)">编辑
                            </el-button>
                            <el-button
                                    v-show="userPermission['device:deleteMeter']"
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
                <div class="block flex-center" style="padding-top:20px;">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="page"
                            :page-sizes="[20, 50, 100, 150]"
                            :page-size="20"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </div>
            </template>

        </div>

        <!--设置管理中心号码-->
        <el-dialog title="设置管理中心号码" :close-on-click-modal="false" size="tiny" v-model="managerCenterVisible">
            <el-form :model="form">
                <el-form-item label="管理中心号码1">
                    <el-input v-model="mc1" placeholder="请输入管理中心号码"></el-input>
                </el-form-item>
                <el-form-item label="管理中心号码2">
                    <el-input v-model="mc2" placeholder="请输入管理中心号码"></el-input>
                </el-form-item>
                <el-form-item label="管理中心号码3">
                    <el-input v-model="mc3" placeholder="请输入管理中心号码"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="managerCenterVisible = false" :disabled="disabledManagerCenter">取消</el-button>
                <el-button type="primary" :loading="loadManagerCenter"
                           @click="setManagerCenterData">确 定
                </el-button>
            </div>
        </el-dialog>

        <!--定时上传设置 -->
        <el-dialog title="定时上传设置" :close-on-click-modal="false" size="tiny" v-model="timingVisible">
            <el-form :model="form2">

                <el-select v-model="day1" >
                    <el-option v-for="item in days" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-time-picker
                        v-model="timing1"
                        placeholder="定时点1">
                </el-time-picker>
                <el-switch
                        v-model="on1"
                        on-color="#13ce66"
                        off-color="#ff4949">
                </el-switch>

                <el-select v-model="day2">
                    <el-option v-for="item in days" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-time-picker
                        v-model="timing2"
                        placeholder="定时点2">
                </el-time-picker>
                <el-switch
                        v-model="on2"
                        on-color="#13ce66"
                        off-color="#ff4949">
                </el-switch>

                <el-select v-model="day3">
                    <el-option v-for="item in days" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-time-picker
                        v-model="timing3"
                        placeholder="定时点3">
                </el-time-picker>
                <el-switch
                        v-model="on3"
                        on-color="#13ce66"
                        off-color="#ff4949">
                </el-switch>

                <el-select v-model="day4">
                    <el-option v-for="item in days" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-time-picker
                        v-model="timing4"
                        placeholder="定时点4">
                </el-time-picker>
                <el-switch
                        v-model="on4"
                        on-color="#13ce66"
                        off-color="#ff4949">
                </el-switch>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="timingVisible = false" :disabled="disabledTiming">取 消</el-button>
                <el-button type="primary" :loading="loadTiming"  @click="setTimingData">确 定</el-button>
            </div>
        </el-dialog>

        <!--添加设备 -->
        <el-dialog title="添加仪表设备" size="tiny" :close-on-click-modal="false" v-model="addMeterVisible">
            <el-form :model="form1">

                <el-input v-model="addMeterData.meterCode" placeholder="请输入手机号"></el-input>

                <el-input v-model="addMeterData.meterName" placeholder="请输入仪表名称"></el-input>

                <el-date-picker
                        v-model="addMeterData.createTime"
                        type="date"
                        placeholder="选择安装日期">
                </el-date-picker>

                <el-select v-model="addMeterData.unit">
                    <el-option label="吨" value="吨"></el-option>
                </el-select>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="addMeterVisible = false">取 消</el-button>
                <el-button type="primary" @click="addMeter">确 定</el-button>
            </div>
        </el-dialog>


        <el-dialog title="激活时间设置" size="tiny" :close-on-click-modal="false" v-model="activateTimeVisible">
            <el-input v-model="activateTime" placeholder="请输入激活时间(秒)"></el-input>
            <div slot="footer" class="dialog-footer">
                <el-button @click="activateTimeVisible = false" :disabled="disabledActivate" >取 消</el-button>
                <el-button type="primary" :loading="loadActivateTime"  @click="setActivateTimeData">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :title="title" :close-on-click-modal="false"
                   v-model="dialogVisible"
                   size="tiny"
                   @close="close"
        >
            <span>{{waitDataMsg}}</span>
            </span>
        </el-dialog>

    </div>
</template>
<script type="text/babel">
const debug = process.env.NODE_ENV == 'production' ? "" : "sms/"
    import {formatDate} from "util"
    import { mapGetters } from 'vuex'
    export default {
        data() {
            return {
                form:{meterCode:""},
                update:null,
                breadcrumbName:["我的设备","仪表"],
                title: "",
                waitDataMsg: "",
                intervalId: "",
                timeOutId: "",
                dialogVisible: false,
                days: [],
                loadReadMeter: false,
                loadOpenMeter: false,
                loadCloseMeter: false,
                loadManagerCenter: false,
                loadTiming: false,
                timingVisible: false,
                disabledTiming: false,
                disabledReadMeter: false,
                disabledManagerCenter: false,
                disabledOpenMeter: false,
                disabledCloseMeter: false,
                managerCenterVisible: false,
                addMeterVisible: false,
                activateTimeVisible: false,
                disabledActivate: false,
                loadActivateTime: false,
                mc1: "",
                mc2: "",
                mc3: "",
                form: {},
                form1: {},
                form2: {},
                formLabelWidth: 100,
                page: 1,
                data: [],
                gsmData: [],
                on1: false,
                on2: false,
                on3: false,
                on4: false,
                day1: "0",
                day2: "0",
                day3: "0",
                day4: "0",
                timing1: "",
                timing2: "",
                timing3: "",
                timing4: "",
                activateTime: "",
                meterManager:"",
                addMeterData: {meterCode: "", meterName: "", createTime: "", unit: "吨"},
                total: 0,
                row: null
            }
        },
        created() {
            this.meterData()
            this.initData()
        },
        computed:{
            ...mapGetters({
                userPermission:'userPermission'
            })
        },
        methods:{
            handleSearch() {
                this.meterData()
            },
            initData() {
                for (var i = 0; i < 32; i++) {
                    if (i == 0) {
                        this.days[i] = {label: "请选择日期", value: "0"};
                    } else {
                        var value = i;
                        if (i < 10) {
                            value = "0" + i;
                        }
                        this.days[i] = {label: i + "号", value: value};
                    }
                }
            },
            readMeter() {
                if (this.meterRow == null) {
                    this.$message({
                        showClose: true,
                        message: '请先选择一个仪表再操作',
                        type: 'error'
                    });
                    return;
                }
                this.loadReadMeter = true
                this.disabledOpenMeter = true
                this.disabledCloseMeter = true
                $.ajax({
                    url:'device/readMeterData',
                    data: {id: this.meterRow.id},
                    success: e => {
                        console.log(e);
                        if (e.success) {
                            this.$message.success('命令发送成功!');
                            this.waitData(this.meterRow.id);
                        } else {
                            this.$message.error(e.statusText);
                        }
                        this.loadReadMeter = false
                        this.disabledOpenMeter = false
                        this.disabledCloseMeter = false
                    },
                    error: e => {
                        this.loadReadMeter = false
                        this.disabledOpenMeter = false
                        this.disabledCloseMeter = false
                        this.$message.error(e.statusText);
                    }
                })
            },
            waitData: function (meterId) {
                this.title = "抄表返回结果";
                this.waitDataMsg = "等待仪表返回。。。。。";
                this.dialogVisible = true;


                var date = new Date();
                var timeOut = 5 * 60 * 1000;
                var str = this.waitDataMsg;

                var timeOut1 = 5 * 60 - 3;
                if(this.intervalId) clearInterval(this.intervalId)
                this.intervalId = setInterval(() => {
                    this.loadWaitData(date, timeOut1, str, meterId);
                    timeOut1 = timeOut1 - 3;
                }, 3000);

                this.timeOutId = setTimeout(() => {
                    clearInterval(this.intervalId);
                    this.waitDataMsg = "获取读表数据超时。。。。。";
                }, timeOut);
            },
            loadWaitData: function (date, timeOut1, str, meterId) {
                var self = this;
                self.waitDataMsg = str + " 倒计时：" + timeOut1 + "s";
                $.ajax({
                    url: debug+'meterData/page',
                    data: {meterId: meterId, startTime: date},
                    success: function (e) {
                        if (e.success) {
                            var data = e.data.resultList;
                            if (data.length > 0) {
                                self.waitDataMsg = "返回结果:仪表度数: " + data[0].value + " 吨" + " 阀: " + self.getValveStatus(data[0].valveStatus) + " 状态"
                                clearInterval(self.intervalId);
                                clearTimeout(self.timeOutId);
                                self.meterData();
                            }
                        }
                    },
                    error: function (e) {
                        self.waitDataMsg = "服务器端异常";
                        clearInterval(int);
                        clearTimeout(id);
                    }
                })
            },
            waitSetting(meterId) {
                var self = this;
                self.title = "抄表返回结果";
                self.waitDataMsg = "等待仪表返回。。。。。";
                self.dialogVisible = true;

                var date = new Date();
                var timeOut = 5 * 60 * 1000;
                var str = self.waitDataMsg;

                var timeOut1 = 5 * 60 - 3;
                if(this.intervalId) clearInterval(this.intervalId)
                self.intervalId = setInterval(function () {
                    self.loadWaitSetting(date, timeOut1, str, meterId);
                    timeOut1 = timeOut1 - 3;
                }, 3000);

                self.timeOutId = setTimeout(function () {
                    clearInterval(self.intervalId);
                    self.waitDataMsg = "获取读表数据超时。。。。。";
                }, timeOut);
            },
            loadWaitSetting: function (date, timeOut1, str, meterId) {
                var self = this;
                self.waitDataMsg = str + " 倒计时：" + timeOut1 + "s";
                $.ajax({
                    url: debug+'device/meterPage',
                    data: {meterId: meterId, startUpdateTime: date},
                    success: function (data) {
                        if (data.success) {
                            console.log(data);
                            var data = data.data.resultList;
                            if (data.length > 0) {
                                self.waitDataMsg = "返回结果:管理中心号码1: " + data[0].mc1 + ";号码2:"+data[0].mc2+";号码3:"+data[0].mc3+";"
                                        +" 定时点"+data[0].day1+"/"+data[0].timing1+"; 定时点"+data[0].day2+"/"+data[0].timing2+"; 定时点"+data[0].day3+"/"+data[0].timing3
                                        +"; 定时点"+data[0].day4+"/"+data[0].timing4;
                                clearInterval(self.intervalId);
                                clearTimeout(self.timeOutId);
                                self.meterData();
                            }
                        } else {

                        }
                    },
                    error: function (e) {
                        self.waitDataMsg = "服务器端异常";
                        clearInterval(int);
                        clearTimeout(id);
                    }
                })
            },

            openMeter() {
                if (this.meterRow == null) return this.$message.error('请先选择一个仪表再操作')
                this.loadOpenMeter = true
                this.disabledReadMeter = true
                this.disabledCloseMeter = true


                $.ajax({
                    url: debug+'device/setValveStatus',
                    data: {id: this.meterRow.id, status: 1},
                    success: e => {
                        if (e.success) {
                            this.$message.success('命令发送成功!');
                            this.waitData(this.meterRow.id);
                        } else {
                            this.$message.error(e.statusText);
                        }
                        this.loadOpenMeter = false
                        this.disabledReadMeter = false
                        this.disabledCloseMeter = false
                    },
                    error: e => {
                        this.loadOpenMeter = false
                        this.disabledReadMeter = false
                        this.disabledCloseMeter = false
                        this.$message.error(e.statusText);
                    }
                });
            },
            closeMeter() {
                var self = this;
                if (this.meterRow == null) {
                    this.$message.error('请先选择一个仪表再操作');
                    return;
                }
                this.loadCloseMeter = true
                this.disabledOpenMeter = true
                this.disabledReadMeter = true

                $.ajax({
                    url: debug+'device/setValveStatus',
                    data: {id: this.meterRow.id, status: 0},
                    success: function (e) {
                        console.log(e);
                        if (e.success) {
                            self.$message.success('命令发送成功!');
                            self.waitData(self.meterRow.id);
                        } else {
                            self.$message.error(e.statusText);
                        }
                        self.loadCloseMeter = false
                        self.disabledOpenMeter = false
                        self.disabledReadMeter = false
                    },
                    error: function (e) {
                        self.$message.error(e.statusText);
                        self.loadCloseMeter = false
                        self.disabledOpenMeter = false
                        self.disabledReadMeter = false
                    }
                })
            },
            close() {
                clearInterval(this.intervalId);
                clearTimeout(this.timeOutId);
            },
            setActivateTime() {
                var self = this;
                if (self.meterRow == null) {
                    self.$message.error('请先选择一个仪表再操作');
                    return;
                }
                self.activateTimeVisible = true;
            },
            setActivateTimeData() {
                this.loadActivateTime = true;
                this.disabledActivate = true;
                $.ajax({
                    url: debug+'device/setActivateTime',
                    data: {
                        meterId: this.meterRow.id,
                        time: this.activateTime
                    },
                    success: e => {
                        if (e.success) {
                            this.activateTimeVisible = false;
                            this.$message.success("命令发送成功!");
                            this.waitSetting(this.meterRow.id);
                        } else {
                            this.disabledActivate = false;
                            this.loadActivateTime = false;
                            this.$message.error(e.statusText);
                        }
                    },
                    error: e => {
                        this.disabledActivate = false;
                        this.loadActivateTime = false;
                        this.$message.error(e.statusText);
                    }
                })
            },
            setTimingData() {
                var self = this;
                self.loadTiming = true;
                self.disabledTiming = true;
                $.ajax({
                    url: debug+'device/setTiming',
                    data: {
                        meterId: self.meterRow.id,
                        day1: self.day1,
                        timing1: self.timing1,
                        on1: self.on1,
                        day2: self.day2,
                        timing2: self.timing2,
                        on2: self.on2,
                        day3: self.day3,
                        timing3: self.timing3,
                        on3: self.on3,
                        day4: self.day4,
                        timing4: self.timing4,
                        on4: self.on4
                    },
                    success: function (e) {
                        if (e.success) {
                            self.timingVisible = false;
                            self.$message.success("命令发送成功!");
                            self.waitSetting(self.meterRow.id);
                        } else {
                            self.disabledTiming = false;
                            self.loadTiming = false;
                            self.$message.error(e.statusText);
                        }
                    },
                    error: function (e) {
                        self.disabledTiming = false;
                        self.loadTiming = false;
                        self.$message.error(e.statusText);
                    }
                })
            },
            setTiming() {
                var self = this;
                if (self.meterRow == null) {
                    self.$message.error('请先选择一个仪表再操作');
                    return;
                }
                self.timingVisible = true;
            },

            setManagerCenterData() {
                var self = this;
                self.loadManagerCenter = true;
                self.disabledManagerCenter = true;
                console.log({
                    meterId: self.meterRow.id,
                    mc1: self.mc1,
                    mc2: self.mc2,
                    mc3: self.mc3
                })
                $.ajax({
                    url: debug+'device/setManagerCenter',
                    data: {
                        meterId: self.meterRow.id,
                        mc1: self.mc1,
                        mc2: self.mc2,
                        mc3: self.mc3
                    },
                    success: function (e) {
                        console.log(e)
                        if (e.success) {
                            self.managerCenterVisible = false;
                            self.$message.success("命令发送成功！");
                            self.waitSetting(self.meterRow.id);
                        } else {
                            self.$message.error(e.statusText);
                        }
                        self.loadManagerCenter = false;
                        self.disabledManagerCenter = false;
                    },
                    error: function (e) {
                        self.loadManagerCenter = false;
                        self.disabledManagerCenter = false;
                        self.$message.error(e.statusText);
                    }
                });
            },

            meterData: function () {
                $.ajax({
                    url: debug+"device/meterPage",
                    data:this.form,
                    success: e => {
                        console.log(e)
                        if (e.success) {
                            let data = e.data
                            this.data  = data.resultList ? data.resultList.map(item => {
                                item.createTime     = formatDate(item.createTime)
                                item.lastUpLoadTime = formatDate(item.lastUpLoadTime)
                                return item
                            }) : []
                            this.page  = data.pageNo
                            this.total = data.totalSize
                        } else {
                            this.$message.error(e.statusText)
                        }
                    },
                    error: e => { this.$message.error(e.statusText) }
                });


            },
            addMeter() {
                let url = this.update ? debug+'device/updateMeter' : debug+'device/addMeter'
                let data = {
                    meterCode: this.addMeterData.meterCode,
                    meterName: this.addMeterData.meterName,
                    fixDate: this.addMeterData.createTime,
                    unit: this.addMeterData.unit,
                }
                if(this.update) Object.assign(data,{id:this.addMeterData.id})
                $.ajax({
                    url,
                    data,
                    success: (e) => {
                        if (e.success) {
                            this.$message.success("添加仪表成功!");
                            this.addMeterVisible = false
                            this.meterData();
                        } else {
                            this.$message.error(e.statusText);
                        }
                    },
                    error: (e) => {
                        this.$message.error(e.statusText);
                    }
                });
            },
            setManagerCenter() {
                var self = this;
                if (self.meterRow == null) return self.$message.error('请先选择一个仪表再操作');
                self.managerCenterVisible = true;
            },

            handleMeterChange(val) {
                this.meterRow = val
            },
            handleCurrentChange(val) {
                this.gsmData = val
                console.log(val)
            },
            handleEdit(index, row) {
                this.addMeterData = row
                this.addMeterVisible = true
                this.update = true
            },
            handleDetail(index, row) {
                console.log(index)
            },
            handleDelete(index, row) {
                $.ajax({
                    url:debug+'device/deleteMeter',
                    data:{id:row.id},
                    success: (e) => {
                        if (e.success) {
                            this.meterData();
                        } else {
                            this.$message.error(e.statusText);
                        }
                    },
                    error: (e) => {
                        this.$message.error(e.statusText);
                    }
                });
            },
            handleSizeChange(val) {
                console.log('每页 ${val} 条')
            },
            getValveStatus(val) {
                if (val == 1) {
                    return "关闭";
                } else {
                    return "开启";
                }
            },
        }
    }
</script>
