function uploadBatchFile(){
    $("#cod_import_batch_button").linkbutton('disable');
    var formData = new FormData($("#uploadBatch")[0]);
    $.ajax({
        url: cxt +'/codOrder/batchConfirmImport',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            var json = eval('(' + data + ')');
            if(json.success) {
                $("#cod_order_batch_upload").dialog("close");
                $("#codOrderManagerInfoList_datagrid").datagrid("reload");
                return;
            }
            $("#message").html(json.message);
            if(json.rows){
                $("#import_excel_result_datagrid").datagrid("loadData",json.rows);
                $("#importExcelResult").dialog({
                    title: "导入结果",
                    width: 500,
                    height: 400
                }).dialog('open');
            }else{
                $.messager.alert('信息', '导入成功', 'info');
            }
        },
        error: function () {
            alert("操作失败");
        },
        complete:function(){
            $("#cod_import_batch_button").linkbutton('enable');
        }
    });
}