/**
 * Created by user on 2017/4/29.
 */
$(function () {
    var vm = new Vue({
        el: "#wap",
        data: function () {
            return {
                item1: "我的设备",
                item2: "GSM",
                title:"",
                waitDataMsg: "",
                intervalId:"",
                timeOutId:"",
                dialogVisible:false,
                gsmManager: {
                    loadSearchGsm: false,
                    isShow: true,
                    page: 1,
                    data: [],
                    total: 0,
                    row: null,
                },
                meterManager: {
                    loadReadMeter: false,
                    loadOpenMeter: false,
                    loadCloseMeter: false,
                    disabledReadMeter: false,
                    disabledOpenMeter: false,
                    disabledCloseMeter: false,
                    managerCenterVisible: false,
                    addMeterVisible: false,
                    mc1: "",
                    mc2: "",
                    mc3: "",
                    form: {},
                    form1: {},
                    formLabelWidth: 100,
                    page: 1,
                    isShow: false,
                    data: [],
                    gsmData: [],
                    addMeterData: {meterCode: "", controllerId: "0", meterName: "", fixDate: "", unit: "吨"},
                    total: 0,
                    row: null
                },
                meterDataManager: {
                    isShow: false,
                    page: 1,
                    data: [],
                    total: 20,
                    row: null
                },
                interval: {}
            }
        },
        components: {},
        mounted: function () {
            this.gsmData();
        },
        computed: {},
        watch: {
            'meterManager.addMeterVisible': {
                immediate: false,
                handler: function (newVal) {
                    if (newVal) {
                        this.gsmData();
                        this.meterManager.gsmData = this.gsmManager.data;
                    }
                }
            },
        },
        methods: {
            gsmData: function () {
                var self = this;
                $.ajax({
                    url: "/device/concentratorPage",
                    success: function (e) {
                        if (e.success) {
                            self.gsmManager.data = e.data.resultList;
                            self.gsmManager.page = e.data.pageNo;
                            self.gsmManager.total = e.data.totalSize;
                        } else {
                            alert(e.statusText);
                        }
                    },
                    error: function (e) {
                        alert(e.statusText);
                    }
                })
            },
            meterData: function () {
                var self = this;
                $.ajax({
                    url: "/device/meterPage",
                    success: function (e) {
                        if (e.success) {
                            self.meterManager.data = e.data.resultList;
                            self.meterManager.page = e.data.pageNo;
                            self.meterManager.total = e.data.totalSize;
                        } else {
                            alert(e.statusText);
                        }
                    },
                    error: function (e) {
                        alert(e.statusText);
                    }
                });
            },
            meterDatas: function () {
                var self = this;
                $.ajax({
                    url: "/meterData/page",
                    success: function (e) {
                        if (e.success) {
                            self.meterDataManager.data = e.data.resultList;
                            self.meterDataManager.page = e.data.pageNo;
                            self.meterDataManager.total = e.data.totalSize;
                        } else {
                            alert(e.statusText);
                        }
                    },
                    error: function (e) {
                        alert(e.statusText);
                    }
                });
            },
            searchGsmFun: function () {
                var self = this;
                self.gsmManager.loadSearchGsm = true;

                $.ajax({
                    url: "/device/scanner",
                    success: function (e) {
                        if (e.success) {
                            console.log(e)
                            self.$message({
                                showClose: true,
                                message: '已成功扫描并返回了数据!',
                                type: 'success'
                            });
                            // this.$notify({
                            //     title: '成功',
                            //     message: '这是一条成功的提示消息',
                            //     type: 'success'
                            // });
                        } else {

                        }
                        self.gsmManager.loadSearchGsm = false;
                        self.gsmData();
                    },
                    error: function (e) {
                        self.gsmManager.loadSearchGsm = false;
                    }
                });
            },
            readMeter: function () {
                var self = this;
                if (self.meterRow == null) {
                    self.$message({
                        showClose: true,
                        message: '请先选择一个仪表再操作',
                        type: 'error'
                    });
                    return;
                }
                self.meterManager.loadReadMeter = true
                self.meterManager.disabledOpenMeter = true
                self.meterManager.disabledCloseMeter = true
                $.ajax({
                    url: "/device/readMeterData",
                    data: {id: self.meterRow.id},
                    success: function (e) {
                        console.log(e);
                        if (e.success) {
                            self.$message({
                                showClose: true,
                                message: '命令发送成功!',
                                type: 'success'
                            });
                            self.waitData(self.meterRow.id);
                        } else {
                            alert(e.statusText);
                        }
                        self.meterManager.loadReadMeter = false
                        self.meterManager.disabledOpenMeter = false
                        self.meterManager.disabledCloseMeter = false
                    },
                    error: function (e) {
                        self.meterManager.loadReadMeter = false
                        self.meterManager.disabledOpenMeter = false
                        self.meterManager.disabledCloseMeter = false
                        alert(e.statusText);
                    }
                })
            },
            openMeter: function () {
                var self = this;
                if (self.meterRow == null) {
                    self.$message({
                        showClose: true,
                        message: '请先选择一个仪表再操作',
                        type: 'error'
                    });
                    return;
                }
                self.meterManager.loadOpenMeter = true
                self.meterManager.disabledReadMeter = true
                self.meterManager.disabledCloseMeter = true

                $.ajax({
                    url: "/device/setValveStatus",
                    data: {id: this.meterRow.id, status: 1},
                    success: function (e) {
                        if (e.success) {
                            self.$message({
                                showClose: true,
                                message: "命令发送成功",
                                type: 'success'
                            });
                        } else {
                            self.$message({
                                showClose: true,
                                message: e.statusText,
                                type: 'error'
                            });
                        }
                        self.meterManager.loadOpenMeter = false
                        self.meterManager.disabledReadMeter = false
                        self.meterManager.disabledCloseMeter = false
                    },
                    error: function (e) {
                        self.meterManager.loadOpenMeter = false
                        self.meterManager.disabledReadMeter = false
                        self.meterManager.disabledCloseMeter = false
                        self.$message({
                            showClose: true,
                            message: e.statusText,
                            type: 'error'
                        });
                    }
                });
            },
            closeMeter: function () {
                var self = this;
                if (self.meterRow == null) {
                    self.$message({
                        showClose: true,
                        message: '请先选择一个仪表再操作',
                        type: 'error'
                    });
                    return;
                }
                self.meterManager.loadCloseMeter = true
                self.meterManager.disabledOpenMeter = true
                self.meterManager.disabledReadMeter = true

                $.ajax({
                    url: "/device/setValveStatus",
                    data: {id: this.meterRow.id, status: 0},
                    success: function (e) {
                        console.log(e);
                        if (e.success) {
                            self.$message({
                                showClose: true,
                                message: '命令发送成功!',
                                type: 'success'
                            });
                        } else {
                            self.$message({
                                showClose: true,
                                message: e.statusText,
                                type: 'error'
                            });
                        }
                        self.meterManager.loadCloseMeter = false
                        self.meterManager.disabledOpenMeter = false
                        self.meterManager.disabledReadMeter = false
                    },
                    error: function (e) {
                        self.$message({
                            showClose: true,
                            message: e.statusText,
                            type: 'error'
                        });
                        self.meterManager.loadCloseMeter = false
                        self.meterManager.disabledOpenMeter = false
                        self.meterManager.disabledReadMeter = false
                    }
                })
            },
            setManagerCenter: function () {
                var self = this;
                if (self.meterRow == null) {
                    self.$message({
                        showClose: true,
                        message: '请先选择一个仪表再操作',
                        type: 'error'
                    });
                    return;
                }
                $.ajax({
                    url: "/device/setManagerCenter",
                    data: {meterId: self.meterRow.id, mc1: "17681860857"},
                    success: function (e) {
                        console.log(e)
                    },
                    error: function (e) {
                    }
                })
                // this.$alert(this.waitDataMsg, '管理中心号码', {
                //     confirmButtonText: '确定'
                // });
            },
            timeUpload: function () {
                this.$alert(this.waitDataMsg, '定时上传', {
                    confirmButtonText: '确定'
                });
            },
            addMeter: function () {
                var self = this;
                $.ajax({
                    url: "/device/addMeter",
                    data: {
                        meterCode: self.meterManager.addMeterData.meterCode,
                        controllerId: self.meterManager.addMeterData.controllerId,
                        meterName: self.meterManager.addMeterData.meterName,
                        fixDate: self.meterManager.addMeterData.fixDate,
                        unit: self.meterManager.addMeterData.unit
                    },
                    success: function (e) {
                        console.log(e);
                        if (e.success) {
                            self.message('success', "添加仪表成功!");
                            self.meterManager.addMeterVisible = false
                            self.meterData();
                        } else {
                            self.message('error', e.statusText);
                        }
                    },
                    error: function (e) {
                        self.message('error', e.statusText);
                    }
                });
            },
            handleSelect: function (index) {
                if ("1" == index) {
                    return
                }
                if ("2-1" == index) {
                    this.item1 = "我的设备"
                    this.item2 = "GSM";
                    this.gsmManager.isShow = true
                    this.meterManager.isShow = false
                    this.meterDataManager.isShow = false
                    this.gsmData();
                    return
                }
                if ("2-2" == index) {
                    this.meterData();
                    this.item1 = "我的设备"
                    this.item2 = "仪表";
                    this.gsmManager.isShow = false
                    this.meterManager.isShow = true
                    this.meterDataManager.isShow = false
                    return
                }
                if ("2-3" == index) {
                    this.meterDatas();
                    this.item1 = "我的设备"
                    this.item2 = "数据";
                    this.gsmManager.isShow = false
                    this.meterManager.isShow = false
                    this.meterDataManager.isShow = true
                    return
                }
                if ("3" == index) {
                    this.message = "用户信息"
                    return
                }
                if ("4" == index) {
                    this.message = "系统设置"
                    return
                }
            },
            loadWaitData: function (date,timeOut1,str,meterId) {
                var self = this;
                self.waitDataMsg = str + " 倒计时："+ timeOut1 +"s";
                $.ajax({
                    url:'/meterData/page',
                    data:{meterId:meterId,startTime:date},
                    success:function (e) {
                        if(e.success){
                            var data = e.data.resultList;
                            if(data.length > 0){
                                self.waitDataMsg = "返回结果:仪表度数: "+data[0].value+" 吨"+" 阀: "+self.getValveStatus(data[0].valveStatus)+" 状态"
                                clearInterval(self.intervalId);
                                clearTimeout(self.timeOutId);
                            }
                        }else{

                        }
                    },
                    error:function (e) {
                        self.waitDataMsg ="服务器端异常";
                        clearInterval(int);
                        clearTimeout(id);
                    }
                })
            },
            waitData: function (meterId) {
                var self = this ;
                self.title = "抄表返回结果";
                self.waitDataMsg = "等待仪表返回。。。。。";
                self.dialogVisible = true;


                var date = new Date();
                var timeOut = 5*60*1000;
                var str = self.waitDataMsg;

                var timeOut1 = 5*60-3;
                self.intervalId =setInterval(function () {
                    self.loadWaitData(date,timeOut1,str,meterId);
                    timeOut1 = timeOut1 -3;
                }, 3000);

                self.timeOutId = setTimeout(function () {
                    clearInterval(self.intervalId);
                    self.waitDataMsg = "获取读表数据超时。。。。。";
                },timeOut);

            },
            handleMeterChange: function (val) {
                this.meterRow = val
            },
            handleCurrentChange: function (val) {
                this.gsmData = val
                console.log(val)
            },
            handleEdit: function (index, row) {
                console.log(index)
            },
            handleDelete: function (index, row) {
                console.log(index)
            },
            handleSizeChange: function (val) {
                console.log('每页 ${val} 条')
            },
            handleCurrentChange: function (val) {
                this.currentPage = val
                console.log('当前页: ${val}')
            },
            getValveStatus:function (val) {
                if(val == 1){
                    return "关闭";
                }else {
                    return "开启";
                }
            },
            message: function (type, message) {
                this.$message({
                    showClose: true,
                    message: message,
                    type: type
                });
            }
        }
    })
})