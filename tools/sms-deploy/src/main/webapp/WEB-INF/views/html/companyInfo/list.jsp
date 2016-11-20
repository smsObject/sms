<%--
  Created by IntelliJ IDEA.
  User: hubihua
  Date: 2016/11/20
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公司信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="../../../resources/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="../../../resources/css/style.css"/>
    <link rel="stylesheet" href="../../../resources/css/ace.min.css" />
    <link rel="stylesheet" href="../../../resources/css/font-awesome.min.css" />
    <link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="../../../resources/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="../../../resources/css/ace-ie.min.css" />
    <![endif]-->
    <script src="../../../resources/js/jquery-1.9.1.min.js"></script>
    <script src="../../../resources/plugin/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../resources/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/H-ui.js"></script>
    <script type="text/javascript" src="js/H-ui.admin.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="assets/dist/echarts.js"></script>
    <script src="js/lrtk.js" type="text/javascript"></script>

</head>
<body>
<div class="page-content clearfix">
    <div id="brand_style">
        <div class="search_style">
            <div class="title_names">搜索查询</div>
            <ul class="search_content clearfix">
                <li><label class="l_f">品牌名称</label><input name="" type="text"  class="text_add" placeholder="输入品牌名称"  style=" width:250px"/></li>
                <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start" style=" margin-left:10px;"></li>
                <li><select name="" class="text_add"><option  value="1">国内品牌</option><option value="2">国外品牌</option></select></li>
                <li style="width:90px;"><button type="button" class="btn_search"><i class="icon-search"></i>查询</button></li>
            </ul>
        </div>
        <div class="border clearfix">
       <span class="l_f">
        <a href="Add_Brand.html"  title="添加品牌" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加品牌</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
        <a href="javascript:ovid()" class="btn btn-info">国内品牌</a>
        <a href="javascript:ovid()" class="btn btn-success">国外品牌</a>
       </span>
            <span class="r_f">共：<b>234</b>个品牌</span>
        </div>
        <!--品牌展示-->
        <div class="brand_list clearfix" id="category">
            <div class="show_btn" id="rightArrow"><span></span></div>
            <div class="chart_style side_content">
                <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
                <div id="main" style="height:300px;" class="side_list"></div>
            </div>
            <!--品牌列表-->
            <div class="table_menu_list">
                <table class="table table-striped table-bordered table-hover" id="sample-table">
                    <thead>
                    <tr>
                        <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
                        <th width="80px">ID</th>
                        <th width="50px">排序</th>
                        <th width="120px">品牌LOGO</th>
                        <th width="120px">品牌名称</th>
                        <th width="130px">所属地区/国家</th>
                        <th width="350px">描述</th>
                        <th width="180px">加入时间</th>
                        <th width="70px">状态</th>
                        <th width="200px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td width="25px"><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td width="80px">45631</td>
                        <td width="50px"><input type="text" class="input-text text-c" value="1" style="width:60px"></td>
                        <td><img src="products/logo/156.jpg"  width="130"/></td>
                        <td><a href="javascript:ovid()" name="Brand_detailed.html" style="cursor:pointer" class="text-primary brond_name" onclick="generateOrders('561');" title="玉兰油OLAY">玉兰油OLAY</a></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/34.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="generateOrders('5621');" title="玉兰油OLAY">玉兰油OLAY</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/245.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="generateOrders('461');" title="御泥坊">御泥坊</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/199.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="member_show('张三','member-show.html','10001','360','400')">薇姿</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/152.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="member_show('张三','member-show.html','10001','360','400')">丝塔芙</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/42.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="member_show('张三','member-show.html','10001','360','400')">比克度</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
                        <td>2045</td>
                        <td><input type="text" class="input-text text-c" value="2" style="width:60px"></td>
                        <td><img src="products/logo/42.jpg"  width="130"/></td>
                        <td><u style="cursor:pointer" class="text-primary brond_name" onclick="member_show('张三','member-show.html','10001','360','400')">比克度</u></td>
                        <td>法国</td>
                        <td class="text-l">玉兰油OLAY，是宝洁公司全球著名的护肤品牌，OLAY以全球高科技护肤研发技术为后盾......</td>
                        <td>2014-6-11 11:11:42</td>
                        <td class="td-status"><span class="label label-success radius">已启用</span></td>
                        <td class="td-manage">
                            <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a>
                            <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a>
                            <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
</body>
</html>
