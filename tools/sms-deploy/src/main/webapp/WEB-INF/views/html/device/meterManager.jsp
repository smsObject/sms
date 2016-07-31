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
</head>
<body>
<a class="btn btn-primary btn-sm" href="javascript:void(0);">查询</a>
<a class="btn btn-primary btn-sm" href="javascript:void(0);">刷新</a>
<a class="btn btn-primary btn-sm" href="javascript:void(0);">抄表</a>
<a class="btn btn-primary btn-sm" href="javascript:void(0);">新增</a>
<a class="btn btn-danger btn-sm" href="javascript:void(0);">删除</a>

<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-2" data-widget-editbutton="false">
    <!-- widget options:
    usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

    data-widget-colorbutton="false"
    data-widget-editbutton="false"
    data-widget-togglebutton="false"
    data-widget-deletebutton="false"
    data-widget-fullscreenbutton="false"
    data-widget-custombutton="false"
    data-widget-collapsed="true"
    data-widget-sortable="false"

    -->
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
        <h2>仪表信息列表</h2>

    </header>

    <!-- widget div-->
    <div>

        <div class="widget-body no-padding">
            <div class="widget-body-toolbar">

            </div>
            <table id="datatable_col_reorder" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Company</th>
                    <th>Zip</th>
                    <th>City</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>

        </div>
        <!-- end widget content -->

    </div>
    <!-- end widget div -->

</div>
<script src="../../resources/js/plugin/datatables/jquery.dataTables-cust.min.js" type="text/javascript" ></script>
</body>
<script src="../../resources/js/plugin/datatables/FixedColumns.min.js" type="text/javascript" ></script>
</body>
<script src="../../resources/js/plugin/datatables/ColVis.min.js" type="text/javascript" ></script>
</body>
<script src="../../resources/js/plugin/datatables/ZeroClipboard.js" type="text/javascript" ></script>
</body>
<script src="../../resources/js/plugin/datatables/media/js/TableTools.min.js" type="text/javascript" ></script>
</body>
<script src="../../resources/js/plugin/datatables/DT_bootstrap.js" type="text/javascript" ></script>
</body>

<script type="text/javascript" >

    $(document).ready(function() {
        $('#datatable_col_reorder').dataTable({
            "bProcessing": false, // 是否显示取数据时的那个等待提示
            "bServerSide": true,//这个用来指明是通过服务端来取数据
            "sAjaxSource": "../device/meterPage",//这个是请求的地址
            "fnServerData": retrieveData // 获取数据的处理函数
        });
    });

    // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
    function retrieveData( sSource111,aoData111, fnCallback111) {
        $.ajax({
            url : sSource111,//这个就是请求地址对应sAjaxSource
            data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
            type : 'post',
            dataType : 'json',
            async : false,
            success : function(result) {
                console.log(result);
                //fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
            },
            error : function(msg) {
            }
        });
    }

    function runDataTables() {
        /*
         * COL ORDER
         */
//    ,
//        "sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
//                "fnInitComplete" : function(oSettings, json) {
//            $('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
//        }
//
//        $('#datatable_col_reorder').dataTable({
//            "sPaginationType" : "bootstrap"
//        });


    }
</script>
</html>