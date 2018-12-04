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
                var account = $("#account").val();
                var acctType = $("#acctType").val();
                var fundType = $("#fundType").val();

                var data = {
                    "account": account,
                    "acctType": acctType,
                    "fundType": fundType
                };
                queryResult(data);
            });
        });

        function queryResult(data){
            var url = "${app}/queryController/queryAccountBalanceDetail";
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

    <title>账户余额明细查询</title>
<body>
<div id="page-wrapper" style="min-height: 819px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">账户余额明细查询</h1>
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
                        <label>账户编号 <label style="color: red">*</label>：</label> <input type="text" class="form-control"
                                                     placeholder="账户编号" id="account">
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>账户类型 <label style="color: red">*</label>：</label>
                        <select class="form-control"
                                id="acctType">
                            <option value="">全部</option>
                                <option value="1">平台-自有子账户</option>
                                <option value="3">平台-手续费子账户</option>
                                <option value="4">平台-体验金子账户</option>
                                <option value="5">平台-抵用金子账户</option>
                                <option value="6">平台-加息金子账户</option>
                                <option value="7">平台-保证金子账户</option>
                                <option value="11">平台-在途垫付</option>
                                <option value="12">个人-投资账户</option>
                                <option value="13">个人-融资账户</option>
                        </select>
                    </div>
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>个人账户类型 <label style="color: red">*</label>：</label>
                        <select class="form-control"
                                id="fundType">
                            <option value="">全部</option>
                                <option value="01">现金</option>
                                <option value="02">在途</option>
                        </select>
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