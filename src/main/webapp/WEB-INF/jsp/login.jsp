<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="cxt"/>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="${cxt}/login/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="${cxt}/login/css/demo.css"/>
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="${cxt}/login/css/component.css"/>
    <!--[if IE]>
    <script src="${cxt}/login/js/html5.js"></script>
    <![endif]-->
    <script src="${cxt}/js/vue.min.js"></script>
</head>
<body>
<div class="container demo-1" id="app">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>盛世光明管理后台</h3>
                <form action="#" name="f" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="logname" class="text" style="color: #FFFFFF !important" type="text" v-model="user"
                               placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="logpass" class="text" style="display:none"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                        <input name="logpass" class="text" autocomplete="off" v-model="password"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2">
                        <a class="act-but submit" href="javascript:;" style="color: #FFFFFF"
                           v-on:click="login_rsa">登录</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="${cxt}/login/js/TweenLite.min.js"></script>
<script src="${cxt}/login/js/EasePack.min.js"></script>
<script src="${cxt}/login/js/rAF.js"></script>
<script src="${cxt}/login/js/demo-1.js"></script>
<script src="${cxt}/js/axios.min.js"></script>
<%--MD5加密js--%>
<script src="${cxt}js/md5.js" type="text/javascript"></script>
<div style="text-align:center;">
</div>
</body>
<script>

</script>
<script>
    new Vue({
        el: '#app',
        data: {
            user: '',
            password: ''
        },
        methods: {
            login: function () {
                console.log(this.user + this.password)
            },
            login_rsa: function () {
                console.log(this.user + this.password)
                var user = this.user;
                var password = this.password;
                if (null == user || user.length < 4) {
                    alert("用户名格式输入有误！")
                    return
                }
                if (!password || password.length < 4) {
                    alert("密码格式输入有误！")
                    return
                }
                axios.get('${cxt}/login/login', {
                    params: {
                        user: user,
                        password: hex_md5(password)
                    }
                }).then(function (res) {
                    alert(res);
                }).catch(function (error) {
                    alert('数据加载失败，请刷新页面重试！');
                });
            }
        }, created: function () {
        }

    });
</script>
</html>