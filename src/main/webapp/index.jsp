<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="app" value="<%=basePath%>" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>信息查询</title>

    <!-- Bootstrap Core CSS -->
    <link href="${app}/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${app}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="${app}/vendor/datatables-plugins/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link
            href="${app}/vendor/datatables-responsive/dataTables.responsive.css"
            rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${app}/vendor/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${app}/vendor/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <link href="${app}/vendor/home/home.css" rel="stylesheet"
          type="text/css">
    <link href="${app}/js/jquery-easyui-1.4.1/themes/default/easyui.css"
          rel="stylesheet" type="text/css">
    <link href="${app}/css/easyui-yg.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        .form-control[readonly] {
            background-color: #fff;
            opacity: 1;
        }
    </style>
    <!-- jQuery -->
    <script src="${app}/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${app}/vendor/bootstrap/js/bootstrap.min.js"></script>




    <!-- Metis Menu Plugin JavaScript -->
    <script src="${app}/vendor/metisMenu/metisMenu.min.js"></script>
    <%--<script src="${app}/js/common/common.js"></script>--%>

    <!-- DataTables JavaScript -->
    <script src="${app}/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script
            src="${app}/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script
            src="${app}/vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${app}/vendor/dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->


    <script type="text/javascript"
            src="${app}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script src="${app}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"
            charset="UTF-8" type="text/javascript"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        $(function() {

            $.get("${app}/queryController/showCapitalUpdateDetail",function(data){
                $("#main").html(data);
            });
            $(".yg-menu").bind("click",function(){
                var url = $(this).attr("id");
                $.get(url,function(data){
                    $("#main").empty().html(data);
                });
            });
        });

    </script>
</head>

<body>
<div id="wrapper" class="yy_home">


        <div class="navbar-default sidebar yy_home_left" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search" style="display: none">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
							</span>
                        </div> <!-- /input-group -->
                    </li>
                    <li><a href="#"><i class="fa fa-list-alt fa-fw"></i>  信息查询<span
                            class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showCapitalUpdateDetail" class="yg-menu active">资金变动明细查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showCapitalBalance" class="yg-menu active">资金余额查询(废弃)</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showRepayDetail" class="yg-menu active">还款明细查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showSignInvestDetail" class="yg-menu active">标的投资明细查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showSignInvestInfo" class="yg-menu active">标的信息查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showAccountBalanceDetail" class="yg-menu active">账户余额明细查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showAccountSignBalance" class="yg-menu active">账户标的余额查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showOrderStatus" class="yg-menu active">订单状态查询</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/queryController/showRechargeOrderStatus" class="yg-menu active">充值订单状态查询</a></li>
                        </ul>
                        <%--<ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/updateController/showSingleBindCard" class="yg-menu active">单独绑卡</a></li>
                        </ul>
                        <ul class="nav nav-second-level collapse in">
                            <li><a id="${app}/updateController/showBind" class="yg-menu active">批量绑卡</a></li>
                        </ul>--%>
                        <%--<ul class="nav nav-second-level collapse in">--%>
                            <%--<li><a id="${app}/queryController/showAccountConverse" class="yg-menu active">投资账户转融资账户</a></li>--%>
                        <%--</ul>--%>
                        <%--<ul class="nav nav-second-level collapse in">--%>
                            <%--<li><a id="${app}/queryController/showCustTrans" class="yg-menu active">个人缴费</a></li>--%>
                        <%--</ul>--%>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>
    <div id="main">
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
</body>
</html>
