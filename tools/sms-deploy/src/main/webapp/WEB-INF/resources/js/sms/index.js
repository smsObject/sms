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
                    page: 1,
                    isShow: false,
                    data: [],
                    total: 0,
                    row: null
                },
                meterDataManager: {
                    isShow: false,
                    page: 1,
                    data: [],
                    total: 20,
                    row: null
                }
            }
        },
        components: {},
        mounted: function () {
            this.gsmData();
        },
        computed: {},
        watch: {},
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
            searchGsmFun: function () {
                var self = this;
                self.gsmManager.loadSearchGsm = true;

                $.ajax({
                    url:"/device/scanner",
                    success:function (e) {
                        if (e.success){
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
                        }else{

                        }
                        self.gsmManager.loadSearchGsm = false;
                        self.gsmData();
                    },
                    error:function (e) {
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
                    url:"/device/readMeterData",
                    data:{id:self.meterRow.id},
                    success:function (e) {
                        console.log(e);
                        if (e.success){
                            self.$message({
                                showClose: true,
                                message: '命令发送成功!',
                                type: 'success'
                            });
                        }else{
                            alert(e.statusText);
                        }
                        self.meterManager.loadReadMeter = false
                        self.meterManager.disabledOpenMeter = false
                        self.meterManager.disabledCloseMeter = false
                    },
                    error:function (e) {
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
                    url:"/device/setValveStatus",
                    data:{id:this.meterRow.id,status:1},
                    success:function (e) {
                        if(e.success){
                            self.$message({
                                showClose: true,
                                message: "命令发送成功",
                                type: 'success'
                            });
                        }else{
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
                    error:function (e) {
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
                    url:"/device/setValveStatus",
                    data:{id:this.meterRow.id,status:0},
                    success:function (e) {
                        console.log(e);
                        if (e.success){
                            self.$message({
                                showClose: true,
                                message: '命令发送成功!',
                                type: 'success'
                            });
                        }else{
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
                    error:function (e) {
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
            addMeter:function () {
                
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
            }
        }
    })
})