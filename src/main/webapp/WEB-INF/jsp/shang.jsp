<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/3/27
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传下载</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/file/upload.chao"
      method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file" width="120px"> <input
        type="submit" value="上传">
</form>
<hr>
<form action="${pageContext.request.contextPath }/file/down.chao"
      method="get">
    <input type="submit" value="下载">
</form>
</body>
</html>