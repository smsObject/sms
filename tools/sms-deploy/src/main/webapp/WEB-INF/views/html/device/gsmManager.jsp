<%--
  Created by IntelliJ IDEA.
  User: hbh
  Date: 2016/7/17
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GSM设备管理</title>
</head>
<body>
GSM设备管理
<a class="btn btn-primary btn-sm" href="javascript:void(0);">扫描</a>
<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-2" data-widget-editbutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
        <h2>GSM设备列表</h2>
    </header>
</div>
</body>
<script type="text/javascript">
    $(function () {
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
                title:'名称',
                align: 'center',
            },
            {
                field: 'concentratorId',
                title:'组件端口名',
                align: 'center',
            },
            {
                field: 'meterName',
                title:'波特率',
                align: 'center',
            },
            {
                field: 'fixDate',
                title:'生产商',
                align: 'center',
            },
            {
                field: 'useType',
                title:'型号',
                align: 'center',
            },
            {
                field: 'userType',
                title:'短信中心号码',
                align: 'center',
            },
            {
                field: 'basicValue',
                title:'创建时间',
                align: 'center',
            },
            {
                field: 'buyWay',
                title:'创建人',
                align: 'center',
            }
        ];

        $('#table').bootstrapTable({
            columns:columns,
            url:'/device/concentratorPage',
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
    });
    //$table.bootstrapTable('getSelections');
    //$table.bootstrapTable('load', data);
    //$table.bootstrapTable('insertRow', {index: 1, row: row});
</script>
</body>
</html>