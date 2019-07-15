<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>绑定信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no"/>

    <link rel="stylesheet" href="css/jquery-weui.min.css">
    <link rel="stylesheet" href="css/jquery-weui.css">
    <link rel="stylesheet" href="css/demos/css/demos.css">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-weui.js"></script>
    <script src="js/city-picker.js"></script>
    <script src="js/city-picker.min.js"></script>

    <link type="text/css" rel="stylesheet" href="css/wexin/weui.css"/>
    <link type="text/css" rel="stylesheet" href="css/wexin/example.css"/>

    <script type="text/javascript" src="js/jqueryEasyui/jquery.min.js"></script>
    <script type="text/javascript" src="js/jqueryEasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jqueryEasyui/jquery.easyui.mobile.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="https://res.wx.qq.com/open/libs/weuijs/1.0.0/weui.min.js"></script>
    <script src="js/wxmanager/bind.js"></script>

</head>

<body>
<div class="weui-cells__title">请输入用户名密码以绑定微信号</div>
<div class="weui-cells">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" id="loginname" placeholder="请输入用户名"/>
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__bd">
            <input class="weui-input" type="password" id="password" placeholder="请输入密码"/>
        </div>
    </div>

    <div id="toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content" id="mytoast">已完成</p>
        </div>
    </div>

    <!-- 用于校验链接合法 -->
    <input type="hidden" name="timeStamp" id="timeStamp" value="<%=request.getParameter("timeStamp")%>">
    <input type="hidden" name="openid" id="openid" value="<%=request.getParameter("openid")%>">
</div>

<div class="button-sp-area" style="margin-top: 2em;">
    <a href="javascript:;" class="weui-btn weui-btn_plain-primary" id="bindwx" onclick="bindwx();" style="width: 60%">绑&nbsp;&nbsp;定</a>
</div>


</body>
</html>