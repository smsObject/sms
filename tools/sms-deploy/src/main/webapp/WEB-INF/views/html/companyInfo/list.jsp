<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2016/11/20
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公司信息</title>
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
    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css"/>
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
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script src="../../../resources/plugin/layer/layer.js" type="text/javascript"></script>
    <script src="../../../resources/plugin/laydate/laydate.js" type="text/javascript"></script>
    <script src="js/lrtk.js" type="text/javascript"></script>

</head>
<body>
<div class="page-content clearfix">
    <div id="brand_style">
        <div class="search_style">
            <div class="title_names">搜索查询</div>
            <ul class="search_content clearfix">
                <li><label class="l_f">公司名称</label><input name="" type="text" class="text_add" placeholder="输入公司名称"
                                                          style=" width:250px"/></li>
                <li style="width:90px;">
                    <button type="button" class="btn_search"><i class="icon-search"></i>查询</button>
                </li>
            </ul>
        </div>
        <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void(0)" id = "company_add" title="添加公司" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加公司</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
       </span>
        </div>
    </div>
    <table id = "companyInfo" ></table>
</div>
<!--添加公司图层-->
<div class="add_menber" id="add_companyInfo_style" style="display:none">

    <ul class=" page-content">
        <li><label class="label_name">公司名称：</label><span class="add_name"><input name="公司名称" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">企业性质：</label><span class="add_name"><input name="企业性质" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">企业代码：</label><span class="add_name"><input name="企业性质" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">法人代表：</label><span class="add_name"><input name="固定电话" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">服务电话：</label><span class="add_name"><input name="移动电话" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">服务传真：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">服务邮箱：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">报障电话：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">服务网站：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">联系地址：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">邮编：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">业务联系人：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">企业税号：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">服务范围：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">开户行：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">银行账号：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">所在区域：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">辖区用户总量：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
        <li><label class="label_name">仪表正常使用数量：</label><span class="add_name"><input name="电子邮箱" type="text"  class="text_add"/></span><div class="prompt r_f"></div></li>
    </ul>
</div>
</body>
<script>
    var columns = [
        {
            field: 'state',
            checkbox: true,
            align: 'center'
        },
        {
            field: 'id',
            title: '编号',
            align: 'center'
        },
        {
            field: 'companyName',
            title: '公司名称',
            align: 'center'
        },
        {
            field: 'companyCode',
            title: '企业代码',
            align: 'center'
        },
        {
            field: 'baudRate',
            title: '波特率',
            align: 'center'
        },
        {
            field: 'manufacturer',
            title: '生产商',
            align: 'center'
        },
        {
            field: 'model',
            title: '型号',
            align: 'center'
        },
        {
            field: 'isOnline',
            title: '是否在线',
            align: 'center'
        },
        {
            field: 'createDate',
            title: '创建时间',
            align: 'center'
        },
        {
            field: 'createUser',
            title: '创建人',
            align: 'center'
        }
    ];

    $(function(){
        /*公司-添加*/
        $('#company_add').on('click', function(){
            layer.open({
                type: 1,
                title: '公司信息录入',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area : ['800px' , ''],
                content:$('#add_companyInfo_style'),
                btn:['提交','取消'],
                yes:function(index,layero){
                    var num=0;
                    var str="";
                    $(".add_menber input[type$='text']").each(function(n){
                        if($(this).val()=="")
                        {
                            layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
                                title: '提示框',
                                icon:0,
                            });
                            num++;
                            return false;
                        }
                    });
                    if(num>0){  return false;}
                    else{
                        layer.alert('添加成功！',{
                            title: '提示框',
                            icon:1,
                        });
                        layer.close(index);
                    }
                }
            });
        });

        $('#companyInfo').bootstrapTable({
            columns: columns,
            url: '/companyInfo/page',
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
    });
</script>
</html>