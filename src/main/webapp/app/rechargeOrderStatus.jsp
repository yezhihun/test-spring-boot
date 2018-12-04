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

            $("#searchBtn").bind("click", function (data) {
                var originalSerialNo = $("#originalSerialNo").val();
                var occurBalance = $("#occurBalance").val();

                var data = {
                    "originalSerialNo": originalSerialNo,
                    "occurBalance": occurBalance
                };
                queryResult(data);
            });
        });

        function queryResult(data){
            var url = "${app}/queryController/queryRechargeOrderStatus";
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

    <title>充值订单状态查询</title>
<body>
<div id="page-wrapper" style="min-height: 819px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">充值订单状态查询</h1>
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
                        <label>原充值订单号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                     placeholder="原充值订单号" id="originalSerialNo">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>发生金额 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                      placeholder="发生金额" id="occurBalance">
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