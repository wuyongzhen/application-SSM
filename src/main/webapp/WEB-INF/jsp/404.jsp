<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath }" var="cxt"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>404错误页面</title>
<link href="${cxt}/error/css/pintuer.css" rel="stylesheet"/>
</head>

<body>
<div class="container" style=" margin-top:8%;"> 
   <div class="panel margin-big-top">
      <div class="text-center">
         <br>
         <h2 class="padding-top"> <stong>404错误！抱歉您要找的页面不存在</stong> </h2>
         <div class=""> 
            <div class="float-left">
                <img src="${cxt}/error/img/ds-1.gif">
                <div class="alert"> 卧槽！页面不见了！ </div>
            </div>
            <div class="float-right">
               <img src="${cxt}/error/img/ds-2.png" width="260">
            </div>
          </div>
          <div class="padding-big">
               <a href="${cxt}/login/skipLoginPage" class="button bg-yellow">返回登录</a>
               <a href="" class="button">保证不打死管理员</a>
          </div> 
      </div> 
   </div> 
</div>
</body>
</html>
