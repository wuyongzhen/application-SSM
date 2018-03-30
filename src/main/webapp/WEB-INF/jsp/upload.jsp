<%--
  Created by IntelliJ IDEA.
  User: Wu Yongzhen
  Date: 2018/3/16
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath }" var="cxt"/>
<c:set value="${request.getServerName()}" var="xxx"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>合作商信息管理</title>
    <!-- import Vue before Element -->
    <script src="${cxt}/js/vue.min.js"></script>
    <!-- 引入组件库 -->
    <script src="${cxt}/element/element-ui.js"></script>
    <!-- import CSS -->
    <link href="${cxt}/element/element-ui.css" rel="stylesheet">
    <script src="${cxt}/js/axios.min.js"></script>
    <script src="${cxt}/js/moment.min.js"></script>
    <script src="${cxt}/laydate/laydate.js"></script> <!-- 时间控件 -->
    <style>
        .demo-table-expand label {
            width: 120px;
            color: #99a9bf;
        }
    </style>
</head>
<body>
<div id="app">
    <div>
        <el-input placeholder="请输入内容" v-model="input3">
            <template slot="prepend">标题</template>
        </el-input>
        <el-input placeholder="请输入内容" v-model="input4" id="timing">
            <template slot="prepend">新三板时间</template>
        </el-input>
    </div>
    <el-upload
            multiple="false"
            class="upload-demo"
            ref="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
    </el-upload>
</div>
</body>
<script>
    new Vue({
        el: '#app',
        data: {
            input3: ''
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            }
        }, mounted: function () {
            // laydate时间控件
            var _this = this;
            laydate.render({
                elem: '#timing',
                theme: 'grid',
                done: function (value) {
                    console.log(value); //得到日期生成的值，如：2017-08-18
                    _this.date = value;
                }
            })
        }
    });
</script>
<script>

</script>
</html>
