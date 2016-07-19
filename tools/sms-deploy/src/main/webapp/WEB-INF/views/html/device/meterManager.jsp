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
                <tr>
                    <td>91</td>
                    <td>Neil</td>
                    <td>1-550-664-4050</td>
                    <td>Aenean Euismod LLP</td>
                    <td>28842</td>
                    <td>Corby</td>
                    <td>07/27/14</td>
                </tr>
                <tr>
                    <td>92</td>
                    <td>Hunter</td>
                    <td>1-637-483-4408</td>
                    <td>In Nec Orci LLC</td>
                    <td>49338</td>
                    <td>Cleveland</td>
                    <td>01/15/13</td>
                </tr>
                <tr>
                    <td>93</td>
                    <td>Marcia</td>
                    <td>1-512-896-6301</td>
                    <td>Et Risus Industries</td>
                    <td>74123</td>
                    <td>Quinte West</td>
                    <td>09/30/13</td>
                </tr>
                <tr>
                    <td>94</td>
                    <td>Lavinia</td>
                    <td>1-222-745-5312</td>
                    <td>Nulla Interdum Curabitur LLC</td>
                    <td>3531</td>
                    <td>Assiniboia</td>
                    <td>01/12/13</td>
                </tr>
                <tr>
                    <td>95</td>
                    <td>Cynthia</td>
                    <td>1-392-134-2788</td>
                    <td>Nunc Ut Erat Company</td>
                    <td>I27 5OS</td>
                    <td>Pagazzano</td>
                    <td>05/20/13</td>
                </tr>
                <tr>
                    <td>96</td>
                    <td>Lee</td>
                    <td>1-128-816-7274</td>
                    <td>Litora Torquent Per PC</td>
                    <td>11386</td>
                    <td>Mazzano Romano</td>
                    <td>04/18/14</td>
                </tr>
                <tr>
                    <td>97</td>
                    <td>Linda</td>
                    <td>1-546-735-8920</td>
                    <td>Dis Parturient Montes Associates</td>
                    <td>64629</td>
                    <td>Ferlach</td>
                    <td>03/29/14</td>
                </tr>
                <tr>
                    <td>98</td>
                    <td>Wayne</td>
                    <td>1-744-647-6144</td>
                    <td>In Industries</td>
                    <td>Xxxx</td>
                    <td>Memphis</td>
                    <td>06/11/14</td>
                </tr>
                <tr>
                    <td>99</td>
                    <td>Liberty</td>
                    <td>1-841-489-1665</td>
                    <td>Sed Sem Limited</td>
                    <td>27504-649</td>
                    <td>Olivola</td>
                    <td>05/24/14</td>
                </tr>
                <tr>
                    <td>100</td>
                    <td>Cathleen</td>
                    <td>1-883-567-6065</td>
                    <td>Eu Corporation</td>
                    <td>4286</td>
                    <td>Rotheux-Rimi?re</td>
                    <td>07/16/13</td>
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
        /*
         * COL ORDER
         */
//    ,
//        "sDom" : "R<'dt-top-row'Clf>r<'dt-wrapper't><'dt-row dt-bottom-row'<'row'<'col-sm-6'i><'col-sm-6 text-right'p>>",
//                "fnInitComplete" : function(oSettings, json) {
//            $('.ColVis_Button').addClass('btn btn-default btn-sm').html('Columns <i class="icon-arrow-down"></i>');
//        }

        $('#datatable_col_reorder').dataTable({
            "sPaginationType" : "bootstrap"
        });
    }
</script>
</html>