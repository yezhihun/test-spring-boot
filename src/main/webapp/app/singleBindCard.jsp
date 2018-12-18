<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    request.setAttribute("app", request.getContextPath());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${app}/js/jquery-easyui-1.4.1/themes/default/easyui.css"
          rel="stylesheet" type="text/css">
    <link href="${app}/css/easyui-yg.css" rel="stylesheet" type="text/css">
    <link href="${app}/vendor/bootstrap/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
    <style>
        /*.window-header,.window-body{min-width:430px}  */
        .window {
            min-width: 442px !important;
        }

        .datetimepicker {
            z-index: 999999
        }
    </style>

    <script src="${app}/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${app}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script src="${app}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"
            charset="UTF-8" type="text/javascript"></script>
    <script src="${app}/vendor/bootstrap/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript">
        var datagrid;
        $(function () {

            datagrid = $('#datagrid')
                .datagrid(
                    {
                        url : "${app}/queryGameList",
                        title : '',
                        pagination : true,
                        toolbar : "#toolbar",
                        pageSize : 10,
                        pageList : [ 10, 20, 30, 40, 50 ],
                        border : true,
                        idField : 'id',
                        singleSelect : true,
                        fit : false,
                        queryParams : {
                            "name": $("#name").val(),
                            "idCode":$("#idCode").val(),
                            "preMobile":$("#preMobile").val(),
                            "bankNum":$("#bankNum").val()
                        },
                        columns : [ [
                            {
                                field : 'homeTeamName',
                                title : '主队',
                                width : 180,
                                align : 'center'
                            },
                            {
                                field : 'homeTeamName',
                                title : '主队得分',
                                width : 180,
                                align : 'center'
                            },
                            {
                                field : 'guestTeamName',
                                title : '客队',
                                width : 180,
                                align : 'center'
                            },
                            {
                                field : 'gameDate',
                                title : '比赛时间',
                                width : 180,
                                align : 'center',
                                formatter : function(value) {
                                    if (value == -1) {
                                        return "";
                                    } else {
                                        return dateFormat(new Date(
                                            value),
                                            "yyyy-MM-dd hh:mm:ss");
                                    }
                                }
                            },
                            {
                                field : 'TRIGGER_STATE',
                                title : '状态',
                                width : 100,
                                align : 'center',
                                formatter : function(value, rowData,
                                                     rowIndex) {
                                    if (value == "WAITING") {
                                        return "等待";
                                    } else if (value == "PAUSED") {
                                        return "暂停";
                                    } else if (value == "ACQUIRED") {
                                        return "正常执行";
                                    } else if (value == "BLOCKED") {
                                        return "阻塞";
                                    } else if (value == "ERROR") {
                                        return "错误";
                                    } else if (value == "DONE") {
                                        return "完成";
                                    } else {
                                        return value;
                                    }

                                }
                            },
                            {
                                field : 'TRIGGER_NAME',
                                title : '操作',
                                width : 180,
                                align : 'center',
                                formatter : function(value, rowData,
                                                     rowIndex) {
                                    var e = "<a href='#' onclick=editRow('"
                                        + rowIndex
                                        + "\',\'"
                                        + value
                                        + "')>修改</a>";
                                    var s = "<a href='#' onclick=saveRow('"
                                        + rowIndex + "')>保存</a>";
                                    var c = "<a href='#' onclick=cancelRow('"
                                        + rowIndex + "')>取消</a>";

                                    var d = "<a href='#' onclick=deleteJob('"
                                        + rowData.JOB_NAME
                                        + "')>删除</a>";
                                    var start = '<a href="#" onclick="startJob(\''
                                        + value
                                        + '\',\''
                                        + rowData.JOB_NAME
                                        + '\')">开始</a>';
                                    var pause = '<a href="#" onclick="pauseJob(\''
                                        + value
                                        + '\',\''
                                        + rowData.JOB_NAME
                                        + '\')">暂停</a>';
                                    if (rowData.editing){
                                        return s + " | " + c;
                                    }else{
                                        if(rowData.TRIGGER_STATE=="PAUSED"){
                                            return start +" | " + e + " | " + d;
                                        }else if(rowData.TRIGGER_STATE=="DONE"){
                                            d = '<a href="#" class="a_dis">删除</a>';
                                            return d;
                                        }
                                        else {
                                            return pause +" | " + e + " | " + d;
                                        }
                                    }
                                }
                            } ] ]
                    });


            /*展开代码*/
            var ul_show = 1;
            $("#show_all").click(function (e) {
                e.preventDefault();
                if (ul_show == 1) {
                    $("#top_form").removeClass("biaoge_hide");
                    $(this).html("- 收起");
                    ul_show = 0
                } else {
                    $("#top_form").addClass("biaoge_hide");
                    $(this).html("+显示全部");
                    ul_show = 1
                }
            });

            $("#searchBtn").bind("click", function (data) {
                var name = $("#name").val();
                var idCode = $("#idCode").val();
                var preMobile = $("#preMobile").val();
                var bankNum = $("#bankNum").val();

                var data = {
                    "name": $("#name").val(),
                    "idCode":$("#idCode").val(),
                    "preMobile":$("#preMobile").val(),
                    "bankNum":$("#bankNum").val()
                };
//                queryResult(data);
                datagrid.datagrid('load', data);
            });
        });

        function queryResult(data){
            var url = "${app}/updateController/singleBindCard";
            $.ajax({
                url: url,
                type : "POST",
                data : data,
                dataType : "json",
                success : function(data){
                    if(data && data.data){
                        $("#result").val(JSON.stringify(JSON.parse(data.data), null, "\t"));
                    }
                }
            });
        }

    </script>

    <title>单独绑卡</title>
<body>
<div id="page-wrapper" style="min-height: 819px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">单独绑卡</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="form-inline row  biaoge" role="form"
                 id="top_form">
                <div class="col-xs-8">
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>姓名 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                     placeholder="真实姓名" id="name">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>身份证号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                                                        placeholder="身份证号" id="idCode">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>银行卡号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                                                        placeholder="银行卡号" id="bankNum">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>预留手机号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                                                         placeholder="预留手机号" id="preMobile">
                    </div>
                </div>
                <div class="form-group col-xs-4 bor-l">
                    <button class="btn btn-warning  btn-sm mr20 w120" id="searchBtn">
                        <span class="glyphicon glyphicon-search mr5"></span>绑定
                    </button>
                </div>
            </div>
            <hr>
        </div>
    </div>
    <div class="row">
        <div id="datagrid"></div>
    </div>
</div>

</body>