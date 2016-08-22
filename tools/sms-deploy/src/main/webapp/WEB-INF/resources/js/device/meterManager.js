var columns = [
    {	field: 'state',
        checkbox: true,
        align: 'center',
    },
    {	field: 'meterNo',
        title:'编号',
        align: 'center',
    },
    {
        field: 'meterCode',
        title:'编码',
        align: 'center',
    },
    {
        field: 'concentratorId',
        title:'所属集中器',
        align: 'center',
    },
    {
        field: 'meterName',
        title:'仪表名称',
        align: 'center',
    },
    {
        field: 'fixDate',
        title:'安装时间',
        align: 'center',
    },
    {
        field: 'useType',
        title:'使用类型',
        align: 'center',
    },
    {
        field: 'userType',
        title:'用户类型',
        align: 'center',
    },
    {
        field: 'basicValue',
        title:'基础值',
        align: 'center',
    },
    {
        field: 'buyWay',
        title:'付费方式',
        align: 'center',
    },
    {
        field: 'meterModel',
        title:'仪表型号',
        align: 'center',
    }
];

$(function () {
   var $table= $('#table').bootstrapTable({
        columns:columns,
        url:'/device/meterPage',
        method:'get',
        pageNumber:1,
        pageSize:15,
        pageList:[10, 15, 20],
        singleSelect:true,
        pagination:true,
        striped:true,
        clickToSelect:true,
        sidePagination:'server'
    });

    $("#search").click(function(){

    });

    $("#refresh").click(function(){

    });

    $("#readMeter").click(function(){
        var data= $table.bootstrapTable('getSelections');
        console.log(data);
        //弹窗ajax请求
        if (data.length ==0){
            alert("请选择仪表再操作");
            return;
        }

        $.ajax({
            url:"../device/readMeterData",
            data:{id :data[0].id},
            success:function (data) {
                debugger;
                if (data.stateCode == "ERROR"){
                    alert("服务器端发生异常");
                }else{
                    alert("");
                }
            },
            error:function () {
                alert("请求失败!");
            }
        });
    });

    $("#add").click(function () {
        //弹窗新增
    });

    $("#delete").click(function () {
        选中Ajax删除
        var data= $table.bootstrapTable('getSelections');
        console(data);
    });

});
//$table.bootstrapTable('getSelections');
//$table.bootstrapTable('load', data);
//$table.bootstrapTable('insertRow', {index: 1, row: row});