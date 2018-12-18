<%--
  Created by IntelliJ IDEA.
  User: tianye
  Date: 2018/12/4
  Time: 下午2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    request.setAttribute("app", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 、Transitional//EN">
<html>
<head>
    <title>比赛信息</title>
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
        .diff{
            display: none;
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
                                width : 100,
                                align : 'center'
                            },
                            {
                                field : 'homeTotalScore',
                                title : '主队得分',
                                width : 100,
                                align : 'center'
                            },
                            {
                                field : 'guestTeamName',
                                title : '客队',
                                width : 100,
                                align : 'center'
                            },
                            {
                                field : 'guestTotalScore',
                                title : '客队得分',
                                width : 100,
                                align : 'center'
                            },
                            {
                                field : 'gameDate',
                                title : '比赛时间',
                                width : 100,
                                align : 'center'
                            },
                            {
                                field : 'test',
                                title : '操作',
                                width : 100,
                                align : 'center',
                                formatter : function(value, rowData,
                                                     rowIndex) {
                                    var e = "<a href='#' onclick=showDetail('"
                                        + rowIndex
                                        + "\',\'"
                                        + rowData.mid
                                        + "')>详情</a>";
                                    return e;
                                }
                            }
                             ] ]
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

            $('.need_datepicker').datetimepicker({
                zIndex : 9999,
                language : 'cn',
                todayBtn : 1,//标记今天
                clearBtn : 1,
                autoclose : 1,//自动关闭
                todayHighlight : 1, //
                //minView : "hour", //选择日期后
            });

            $('#showDiff').bind("click", function (data) {
                $('.diff').toggle();
            })

            $("#searchBtn").bind("click", function (data) {

                var data = {
                    "team": $("#team").val(),
                    "showDiff": $("#showDiff").is(":checked"),
                    "home": $("input[name=home]:checked").val(),
                    "team2": $("#team").val()
                };
                datagrid.datagrid('load', data);
            });
        });

        function showDetail(index, mid) {
            $.ajax({
                url: "${app}/queryGameDetailForMid",
                method: "POST",
                data: {
                    mid: mid
                },
                success: function (data) {
                    $("#game-detail-window").show();
                    $("#game-detail-window").window("open");
                    $("#homeName").text(data.game.homeTeamName);
                    $("#homeScore1").text(data.game.homeScore1);
                    $("#homeScore2").text(data.game.homeScore2);
                    $("#homeScore3").text(data.game.homeScore3);
                    $("#homeScore4").text(data.game.homeScore4);
                    if (data.game.homeOverScore1>0){
                        $("#homeOt1").text(data.game.homeOverScore1);
                        $("#homeOt1").show();
                    }
                    if (data.game.homeOverScore2>0){
                        $("#homeOt2").text(data.game.homeOverScore2);
                        $("#homeOt2").show();
                    }
                    if (data.game.homeOverScore3>0){
                        $("#homeOt3").text(data.game.homeOverScore3);
                        $("#homeOt3").show();
                    }
                    $("#homeTotalScore").text(data.game.homeTotalScore);

                    $("#guestName").text(data.game.guestTeamName);
                    $("#guestScore1").text(data.game.guestScore1);
                    $("#guestScore2").text(data.game.guestScore2);
                    $("#guestScore3").text(data.game.guestScore3);
                    $("#guestScore4").text(data.game.guestScore4);
                    if (data.game.guestOverScore1>0){
                        $("#guestOt1").text(data.game.guestOverScore1);
                        $("#guestOt1").show();
                    }
                    if (data.game.guestOverScore2>0){
                        $("#guestOt2").text(data.game.guestOverScore2);
                        $("#guestOt2").show();
                    }
                    if (data.game.guestOverScore3>0){
                        $("#guestOt3").text(data.game.guestOverScore3);
                        $("#guestOt3").show();
                    }
                    $("#guestTotalScore").text(data.game.guestTotalScore);
                }
            });
        }

    </script>
</head>
<body>
<div id="page-wrapper" style="min-height: 819px;">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">比赛信息查询</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="form-inline row  biaoge" role="form"
                 id="top_form">
                <div class="col-xs-10">
                    <div class="form-group col-sm-5 col-xs-6 mb20">
                        <label>队伍&nbsp; <label style="color: red">*</label>：</label>
                        <select class="form-control" id="team">
                            <option value="2:1">老鹰</option>
                            <option value="2:10">火箭</option>
                            <option value="2:11">步行者</option>
                            <option value="2:12">快船</option>
                            <option value="2:13">湖人</option>
                            <option value="2:14">热火</option>
                            <option value="2:15">雄鹿</option>
                            <option value="2:16">森林狼</option>
                            <option value="2:17">篮网</option>
                            <option value="2:18">尼克斯</option>
                            <option value="2:19">魔术</option>
                            <option value="2:2">凯尔特人</option>
                            <option value="2:20">76人</option>
                            <option value="2:21">太阳</option>
                            <option value="2:22">开拓者</option>
                            <option value="2:23">国王</option>
                            <option value="2:24">马刺</option>
                            <option value="2:25">雷霆</option>
                            <option value="2:26">爵士</option>
                            <option value="2:27">奇才</option>
                            <option value="2:28">猛龙</option>
                            <option value="2:29">灰熊</option>
                            <option value="2:3">鹈鹕</option>
                            <option value="2:30">黄蜂</option>
                            <option value="2:4">公牛</option>
                            <option value="2:5">骑士</option>
                            <option value="2:6">独行侠</option>
                            <option value="2:7">掘金</option>
                            <option value="2:8">活塞</option>
                            <option value="2:9">勇士</option>
                        </select>
                    </div>
                    <div class="form-group col-sm-1 col-xs-1 mb20">
                        <label><input id="showDiff" type="checkbox" class="form-checkbox" checked/>主/客</label>
                    </div>
                    <div class="diff form-group col-sm-1 col-xs-1 mb20 ">
                        <label><input type="radio" class="radio home-radio" name="home" value="1" checked/>主</label>
                        <label><input type="radio" class="radio home-radio" name="home" value="0"/>客</label>
                    </div>
                </div>
                <div class="form-group col-xs-10">
                    <div class="form-group col-sm-4 col-xs-6 mb20">
                        <label>队伍2 ：</label>
                        <select class="form-control" id="team2">
                            <option value="">全部</option>
                            <option value="2:1">老鹰</option>
                            <option value="2:10">火箭</option>
                            <option value="2:11">步行者</option>
                            <option value="2:12">快船</option>
                            <option value="2:13">湖人</option>
                            <option value="2:14">热火</option>
                            <option value="2:15">雄鹿</option>
                            <option value="2:16">森林狼</option>
                            <option value="2:17">篮网</option>
                            <option value="2:18">尼克斯</option>
                            <option value="2:19">魔术</option>
                            <option value="2:2">凯尔特人</option>
                            <option value="2:20">76人</option>
                            <option value="2:21">太阳</option>
                            <option value="2:22">开拓者</option>
                            <option value="2:23">国王</option>
                            <option value="2:24">马刺</option>
                            <option value="2:25">雷霆</option>
                            <option value="2:26">爵士</option>
                            <option value="2:27">奇才</option>
                            <option value="2:28">猛龙</option>
                            <option value="2:29">灰熊</option>
                            <option value="2:3">鹈鹕</option>
                            <option value="2:30">黄蜂</option>
                            <option value="2:4">公牛</option>
                            <option value="2:5">骑士</option>
                            <option value="2:6">独行侠</option>
                            <option value="2:7">掘金</option>
                            <option value="2:8">活塞</option>
                            <option value="2:9">勇士</option>
                        </select>
                    </div>
                    <button class="btn btn-warning  btn-sm mr20 w120" id="searchBtn" style="float: right;">
                        <span class="glyphicon glyphicon-search mr5"></span>查询
                    </button>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <div>
        <div class="row" style="border:1px dashed #000;">
            <div class="col-lg-12">
                <h1 class="page-header">胜率分析</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <hr>
    <div class="row">
        <div id="datagrid"></div>
    </div>

    <div id="game-detail-window" class="easyui-window" style="height: 80%; width: 80%;top: 5%;left: 10%;"
         data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false
										,iconCls:'icon-save',collapsible:false,inline:false"
         title="比赛详情" style="display: none">
        <div class="easyui-layout"
             style="width: 100%; height: 100%; border: 1px rgb(202, 196, 196) solid; border-radius: 5px;">
            <div style="height: 100%;" class="row">
                <table style="margin-left: 70px;">
                    <tr>
                        <th><label style="margin: 10px;">球队</label></th>
                        <th><label style="margin: 10px;">第一节</label></th>
                        <th><label style="margin: 10px;">第二节</label></th>
                        <th><label style="margin: 10px;">第三节</label></th>
                        <th><label style="margin: 10px;">第四节</label></th>
                        <th><label class="ot1" style="margin: 10px;display: none;">加时1</label></th>
                        <th><label class="ot2" style="margin: 10px;display: none;">加时2</label></th>
                        <th><label class="ot3" style="margin: 10px;display: none;">加时3</label></th>
                        <th><label class="ot4" style="margin: 10px;display: none;">加时4</label></th>
                        <th><label style="margin: 10px;">总分</label></th>
                    </tr>
                    <tr>
                        <td><label style="margin: 10px;" id="homeName"></label></td>
                        <td><label style="margin: 10px;" id="homeScore1"></label></td>
                        <td><label style="margin: 10px;" id="homeScore2"></label></td>
                        <td><label style="margin: 10px;" id="homeScore3"></label></td>
                        <td><label style="margin: 10px;" id="homeScore4"></label></td>
                        <td><label class="ot1" style="margin: 10px;display: none;" id="homeOt1"></label></td>
                        <td><label class="ot2" style="margin: 10px;display: none;" id="homeOt2"></label></td>
                        <td><label class="ot3" style="margin: 10px;display: none;" id="homeOt3"></label></td>
                        <td><label class="ot4" style="margin: 10px;display: none;" id="homeOt4"></label></td>
                        <td><label style="margin: 10px;" id="homeTotalScore"></label></td>
                    </tr>
                    <tr>
                        <td><label style="margin: 10px;" id="guestName"></label></td>
                        <td><label style="margin: 10px;" id="guestScore1"></label></td>
                        <td><label style="margin: 10px;" id="guestScore2"></label></td>
                        <td><label style="margin: 10px;" id="guestScore3"></label></td>
                        <td><label style="margin: 10px;" id="guestScore4"></label></td>
                        <td><label class="ot1" style="margin: 10px;" id="guestOt1"></label></td>
                        <td><label class="ot2" style="margin: 10px;" id="guestOt2"></label></td>
                        <td><label class="ot3" style="margin: 10px;" id="guestOt3"></label></td>
                        <td><label class="ot4" style="margin: 10px;" id="guestOt4"></label></td>
                        <td><label style="margin: 10px;" id="guestTotalScore"></label></td>
                    </tr>
                </table>
                <hr>
                <table style="margin-left: 70px;">
                    <tr>
                        <th><label style="margin: 10px;" id="homeTeam"></label></th>
                        <th><label style="margin: 10px;" id="guestTeam"></label></th>
                    </tr>
                    <tr>
                        <th><label style="margin: 10px;" id="homeTeam"></label></th>
                        <th><label style="margin: 10px;" id="guestTeam"></label></th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
