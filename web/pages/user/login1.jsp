<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/10/23
  Time: 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <style type="text/css">
        .content{
            padding:0 auto;
            margin: 0 auto;
            height: 450px;
            width: 100%;
            background-size:100% 450px ;
            margin-top: 25px;
            background-color: burlywood
        }
        .login-wrap{
            position: absolute;
            width:320px;
            height: 300px;
            border-radius: 10px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            right:200px;
            margin-top: 75px;
            background-size: 100%;
            background-color:aquamarine
        }
        .login-wrap h3{
            color:burlywood;
            font-size: 18px;
            text-align: center;
        }
        .name,.code{
            border:1px solid #fff;
            width:230px;
            height: 40px;
            margin-left: 25px;
            margin-bottom: 20px;
            padding-left: 40px;
        }
        .btn input{
            height: 40px;
            width: 120px;
            float: left;
            margin-right: 25px;
            border:none;
            color:#fff;
            font-size: 16px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            margin-top: 10px;
            cursor: pointer;
        }
        input:active{border-color:#147a62}
        .submit{background: #ea8c37;margin-left: 25px;}
        .reset{background: #bbb;}
        /**错误信息提醒**/
        .msg{
            color: #ea8c37;
            font-size: 14px;
            padding-left: 40px;
            padding-top: 10px;
            clear: both;
            font-weight: bold;
        }
    </style>
<%--    <script type="text/javascript">--%>
<%--        function getValue(id) {--%>
<%--            return document.getElementById(id).value;--%>
<%--        }--%>
<%--        function checkuser() {--%>
<%--            if(getValue('accountName') == "admin" && getValue('password') == "admin") {--%>
<%--                return true;--%>
<%--            }else {--%>
<%--                alert("登录名或密码错误！")--%>
<%--                return false;--%>
<%--            }--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<div class="content">
    <div class="login-wrap">
        <form id="user_login" action="kaptcha.jpg" method="post">
            <h3>登 录</h3>
            <input class="name" name="" id="accountName" type="text" placeholder="请输入用户名"><br>
            <input class="code" name="password" id="password" type="password" placeholder="请输入密码">
            <div class="btn">
                <input type="submit" id="submit" class="submit" value="登录">
                <input type="reset" id="reset"  class="reset" value="重置">

            </div>
            <div id="CheckMsg" class="msg"></div>
        </form>

    </div>
</div>
</body>
</html>
