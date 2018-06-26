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

        body{
            margin: 0;
        }
        
        .box{
            margin-top:70px;
            padding:40px;
            background-color: #f7f7f7;
            box-shadow: 5px 10px 15px #d8dce5;
            /*border: 2px solid #d8dce5;*/
        }
    </style>
</head>
<body>
<div id="app" style="padding-bottom:70px;">
    <el-col :span="20" :offset="2" class="box">
        <el-upload
                style="margin-top:40px"
                multiple="false"
                class="upload-demo"
                ref="upload"
                action="${cxt}/announcement/upload"
                :auto-upload="false"
                :on-change="handleChange"
                show-file-list="false">
            <el-button slot="trigger" size="small" type="primary" v-model="file">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        </el-upload>
        <div>
            <el-input placeholder="请输入内容" v-model="content" style="margin-top:40px">
                <template slot="prepend">标题</template>
            </el-input>
            <el-input id="timing" placeholder="请输入内容" v-model="neeqTime" style="margin-top:40px">
                <template slot="prepend">新三板时间</template>
            </el-input>
            <el-button type="success" slot="append" v-on:click="submitParticulars" style="margin-top:40px">提交</el-button>
        </div>
    </el-col>
    <p style="clear:both;"></p>
</div>
</body>
<script>
    new Vue({
        el: '#app',
        data: {
            file: '',
            fileName: '',
            fileSize: '',
            content: '',
            neeqTime: ''
        },
        methods: {
            handleChange(file) {
                this.fileName = file.name;
                this.fileSize = file.size;
            },
            submitUpload(file) {
                console.log(this.input2 + "12121212");
                this.$refs.upload.submit();
            },
            submitParticulars() {
                axios.post('${cxt}/announcement/addAnnouncement', {
                    fileName: this.fileName,
                    fileSize: this.fileSize,
                    content: this.content,
                    neeqTime: this.neeqTime,
                    del: 0
                }).then(function (res) {
                window.location.href='${cxt}/announcement/skipAnnouncementPage';
                }).catch(function (error) {
                    alert('提交失败，请刷新页面重试！');
                });
            }
        }, mounted: function () {
            // laydate时间控件
            var _this = this;
            laydate.render({
                elem: '#timing',
                theme: 'grid',
                done: function (value) {
                    console.log(value); //得到日期生成的值，如：2017-08-18
                    _this.neeqTime = value;
                }
            })
        }
    });
</script>
</html>
