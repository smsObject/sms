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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="../../../resources/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../../resources/css/style.css"/>
    <link rel="stylesheet" href="../../../resources/css/ace.min.css"/>
    <link rel="stylesheet" href="../../../resources/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="../../../resources/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="../../../resources/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="../../../resources/js/jquery-1.9.1.min.js"></script>
    <script src="../../../resources/plugin/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->
    <script src="../../../resources/plugin/bootstrap/js/jquery.dataTables.min.js"></script>
    <script src="../../../resources/plugin/bootstrap/js/jquery.dataTables.bootstrap.js"></script>
    <script src="../../../resources/plugin/layer/layer.js" type="text/javascript"></script>
    <script src="../../../resources/plugin/laydate/laydate.js" type="text/javascript"></script>
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
<div class="page-content clearfix">
    <div id="brand_style">
        <div class="search_style">
            <div class="title_names">搜索查询</div>
            <ul class="search_content clearfix">
                <li><label class="l_f">设备编号</label><input name="" type="text" class="text_add" placeholder="输入设备编号"
                                                          style=" width:250px"/></li>
                <li style="width:90px;">
                    <button type="button" class="btn_search"><i class="icon-search"></i>查询</button>
                </li>
            </ul>
        </div>
        <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void(0)" title="今天" id="scanner" class="btn btn-warning Order_form"><i class="icon-plus"></i>今天</a>
            <a href="javascript:void(0)" title="本周" id="scanner1" class="btn btn-warning Order_form"><i class="icon-plus"></i>本周</a>
            <a href="javascript:void(0)" title="本月" id="scanner2" class="btn btn-warning Order_form"><i class="icon-plus"></i>本月</a>
       </span>
        </div>
    </div>
    <table id = "meterDataTable" ></table>
</div>
</body>
</html>