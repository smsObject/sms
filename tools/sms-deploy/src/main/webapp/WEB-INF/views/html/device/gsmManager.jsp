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
                field: 'id',
                title: '编号',
                align: 'center',
            },
            {
                field: 'name',
                title: '名称',
                align: 'center',
            },
            {
                field: 'comPort',
                title: '组件名',
                align: 'center',
            },
            {
                field: 'baudRate',
                title: '波特率',
                align: 'center',
            },
            {
                field: 'manufacturer',
                title: '生产商',
                align: 'center',
            },
            {
                field: 'model',
                title: '型号',
                align: 'center',
            },
            {
                field: 'isOnline',
                title: '是否在线',
                align: 'center',
            },
            {
                field: 'createDate',
                title: '创建时间',
                align: 'center',
            },
            {
                field: 'createUser',
                title: '创建人',
                align: 'center',
            }
        ];

        $(function () {
            $('#table').bootstrapTable({
                columns: columns,
                url: '/device/concentratorPage',
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
                $.ajax({
                    url:"../device/scanner",
                    success:function (data) {
                        if (data.stateCode == "ERROR"){
                            alert("服务器端错误！");
                        }else{
                            var result= data.data;
                            var length= result.length;
                            var str="";
                            for(var i = 0; i <length; i++){
                                str+=" 组件名称:"+result[i].comPort+"  ";
                            }
                            if (length != 0){
                                alert("检测到设备: "+str + "已更新入库");
                            }else{
                                alert("没有检测到任何设备");
                            }
                        }
                    },
                    error:function () {
                        alert("请求失败！");
                    }
                });
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
                <li><label class="l_f">设备名称</label><input name="" type="text" class="text_add" placeholder="输入设备名称"
                                                          style=" width:250px"/></li>
                <li style="width:90px;">
                    <button type="button" class="btn_search"><i class="icon-search"></i>查询</button>
                </li>
            </ul>
        </div>
        <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void(0)" title="扫描设备" id="scanner" class="btn btn-warning Order_form"><i class="icon-plus"></i>扫描设备</a>
       </span>
        </div>
    </div>
    <table id = "gsmTable" ></table>
</div>
</body>
</html>