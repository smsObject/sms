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
    <title>仪表管理</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../resources/js/libs/jquery-2.0.2.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../resources/js/bootstrap/bootstrap.min.js"></script>

    <!-- 最新的 Bootstrap table 文件 -->
    <script src="../resources/js/bootstrap/bootstrap-table.js"></script>
    <link rel="stylesheet" href="../resources/css/bootstrap-table.css">

    <script type="text/javascript">
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
            $('#table').bootstrapTable({
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

            });

            $("#add").click(function () {
                
            });

            $("#delete").click(function () {
                
            });

        });
        //$table.bootstrapTable('getSelections');
        //$table.bootstrapTable('load', data);
        //$table.bootstrapTable('insertRow', {index: 1, row: row});





    </script>

</head>
<body>
<a class="btn btn-primary btn-sm" id="search" href="javascript:void(0);">查询</a>
<a class="btn btn-primary btn-sm" id="refresh" href="javascript:void(0);">刷新</a>
<a class="btn btn-primary btn-sm" id="readMeter" href="javascript:void(0);">抄表</a>
<a class="btn btn-primary btn-sm" id="add" href="javascript:void(0);">新增</a>
<a class="btn btn-danger btn-sm" id="delete" href="javascript:void(0);">删除</a>

<table id="table"  > </table>
</body>
</html>