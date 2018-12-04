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

            $('.need_datepicker').datetimepicker({
                zIndex : 9999,
                language : 'cn',
                todayBtn : 1,//标记今天
                clearBtn : 1,
                autoclose : 1,//自动关闭
                todayHighlight : 1, //
                //minView : "hour", //选择日期后
            });

            $("#searchBtn").bind("click", function (data) {
                var platcust = $("#platcust").val();
                var pagesize = $("#pagesize").val();
                var pagenum = $("#pagenum").val();
                var transName = $("#transName").val();
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();

                if(pagesize == "" || pagesize == null || pagesize == undefined) {
                    alert("分页大小不能为空");
                    return;
                }
                if(pagenum == "" || pagenum == null || pagenum == undefined) {
                    alert("页码不能为空");
                    return;
                }

                var data = {
                    "platcust": platcust,
                    "pagesize": pagesize,
                    "pagenum": pagenum,
                    "transName": transName,
                    "startDate": startDate,
                    "endDate": endDate
                };
                queryResult(data);
            });
        });

        function queryResult(data){
            var url = "${app}/queryController/queryCapitalUpdateDetail";
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

    <title>资金变动查询</title>
<body>
<div id="page-wrapper" style="min-height: 819px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">资金变动查询</h1>
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
                        <label>平台客户号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                     placeholder="平台客户号" id="platcust">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>分页大小 <label style="color: red">*</label>：</label> <input type="text" class="form-control" value="1000"
                                                                                         placeholder="分页大小" id="pagesize">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>页码 <label style="color: red">*</label>：</label> <input type="text" class="form-control" value="1"
                                                                                         placeholder="页码" id="pagenum">
                    </div>
                </div>
                <div class="col-xs-8">
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>交易名称 ：</label> <input type="text" class="form-control"
                                                                                         placeholder="交易名称" id="transName">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>开始时间：</label> <input
                            class="form-control need_datepicker" type="text"
                            data-date-format="yyyy-mm-dd hh:ii:00" id="startDate" readonly="readonly">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>结束时间：</label> <input
                            class="form-control need_datepicker" type="text"
                            data-date-format="yyyy-m-d h:i:00" id="endDate" readonly="readonly">
                    </div>
                </div>
                <div class="form-group col-xs-4 bor-l">
                    <button class="btn btn-warning  btn-sm mr20 w120" id="searchBtn">
                        <span class="glyphicon glyphicon-search mr5"></span>查询
                    </button>
                </div>
            </div>
            <hr>
        </div>
    </div>
    <div>
        <textarea style="width: 100%;height: 500px;" id="result"></textarea>

    </div>
</div>

</body>