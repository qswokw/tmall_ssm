<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<script>
    $(function(){

        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.loginErrorMessageDiv").show();
        </c:if>
    });
</script>

<title>登录</title>
<form class="loginForm" action="adminlogin" method="post">

    <div id="loginSmallDiv" class="loginSmallDiv">
        <div class="loginErrorMessageDiv">
            <div class="alert alert-danger" >
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>
        <div class="login_acount_text">账户登录</div>
        <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
            <input id="name" name="name" placeholder="用户名" type="text">
        </div>

        <div class="loginInput " >
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
            <input id="password" name="password" type="password" placeholder="密码" type="text">
        </div>


        <div style="margin-top:20px">
            <button class="btn btn-block redButton" type="submit">登录</button>
        </div>
    </div>
</form>
