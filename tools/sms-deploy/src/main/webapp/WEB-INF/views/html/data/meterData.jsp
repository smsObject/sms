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
    <title>仪表数据管理</title>
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
            {
                field: 'state',
                checkbox: true,
                align: 'center',
            },
            {
                field: 'meterId',
                title: '仪表id',
                align: 'center',
            },
            {
                field: 'code',
                title: '编号',
                align: 'center',
            },
            {
                field: 'value',
                title: '数据',
                align: 'center',
            },
            {
                field: 'unit',
                title: '单位',
                align: 'center',
            },
            {
                field: 'uploadTime',
                title: '上传时间',
                align: 'center',
            }
        ];

        $(function () {
            $('#table').bootstrapTable({
                columns: columns,
                url: '/meterData/page',
                method: 'get',
                pageNumber: 1,
                pageSize: 15,
                pageList: [10, 15, 20],
                singleSelect: true,
                pagination: true,
                striped: true,
                clickToSelect: true,
                sidePagination: 'server'
            });


            $("#scanner").click(function(){

            });

        });
        //$table.bootstrapTable('getSelections');
        //$table.bootstrapTable('load', data);
        //$table.bootstrapTable('insertRow', {index: 1, row: row});
    </script>
</head>
<body>
<a class="btn btn-primary btn-sm" id="scanner" href="javascript:void(0);">查询</a>
<table id="table"  > </table>
</body>
</html>