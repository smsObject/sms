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
    <!-- widget div-->
    <div>
        <div class="widget-body no-padding">
            <div class="widget-body-toolbar">
            </div>
            <table id="datatable_col_reorder" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>组件端口名</th>
                    <th>波特率</th>
                    <th>生产商</th>
                    <th>型号</th>
                    <th>短信中心号码</th>
                    <th>创建时间</th>
                    <th>创建人</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>91</td>
                    <td>Neil</td>
                    <td>1-550-664-4050</td>
                    <td>Aenean Euismod LLP</td>
                    <td>28842</td>
                    <td>Corby</td>
                    <td>07/27/14</td>
                    <td>Corby</td>
                    <td>Corby</td>
                </tr>
                <tr>
                    <td>100</td>
                    <td>Cathleen</td>
                    <td>1-883-567-6065</td>
                    <td>Eu Corporation</td>
                    <td>4286</td>
                    <td>Rotheux-Rimi?re</td>
                    <td>07/16/13</td>
                    <td>Corby</td>
                    <td>Corby</td>
                </tr>
                </tbody>
            </table>

        </div>
        <!-- end widget content -->

    </div>
    <!-- end widget div -->

</div>

</body>
<script type="text/javascript" >
    pageSetUp();


    loadDataTableScripts();
    function loadDataTableScripts() {

        loadScript("../../resources/js/plugin/datatables/jquery.dataTables-cust.min.js", dt_2);

        function dt_2() {
            loadScript("../../resources/js/plugin/datatables/ColReorder.min.js", dt_3);
        }

        function dt_3() {
            loadScript("../../resources/js/plugin/datatables/FixedColumns.min.js", dt_4);
        }

        function dt_4() {
            loadScript("../../resources/js/plugin/datatables/ColVis.min.js", dt_5);
        }

        function dt_5() {
            loadScript("../../resources/js/plugin/datatables/ZeroClipboard.js", dt_6);
        }

        function dt_6() {
            loadScript("../../resources/js/plugin/datatables/media/js/TableTools.min.js", dt_7);
        }

        function dt_7() {
            loadScript("../../resources/js/plugin/datatables/DT_bootstrap.js", runDataTables);
        }
    }

    function runDataTables() {
        $('#datatable_col_reorder').dataTable({
            "sPaginationType" : "bootstrap"
        });
    }
</script>
</body>
</html>
