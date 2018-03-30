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
    <el-row>
        <el-col :span="6">
            <div class="grid-content bg-purple-dark">
                <button></button>
            </div>
        </el-col>
        <el-col :span="18">
            <div class="grid-content bg-purple-dark">
                <template>
                    <!--查询头-->
                    <el-row>
                        <el-col :span="8">
                            <div class="grid-content bg-purple-dark">
                                <el-input placeholder="请输入文件名或公告内容" v-model="criteria" style="padding-bottom:10px;">
                                    <el-button slot="append" v-on:click="search">搜索</el-button>
                                </el-input>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content bg-purple-dark">
                                <el-input id="timing" placeholder="根据时间搜索" v-model="date"
                                          style="padding-bottom:10px;">
                                    <el-button slot="append" v-on:click="search_time">搜索</el-button>
                                </el-input>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <div class="grid-content bg-purple-dark">
                                <el-button
                                        type="primary"
                                        style="padding-bottom:10px;margin-left: 50px"
                                        @click="window.location.href='skipUploadPage'">点击跳转上传文件<i
                                        class="el-icon-upload el-icon--right"></i>
                                </el-button>
                            </div>
                        </el-col>
                    </el-row>
                    <!--table-->
                    <el-row>
                        <el-col :span="24">
                            <div class="grid-content bg-purple-dark">
                                <template>
                                    <el-table
                                            :data="partner"
                                            border
                                            stripe
                                            style="width: 100%">
                                        <el-table-column
                                                type="index"
                                                :index="indexMethod"
                                        >
                                        </el-table-column>
                                        <el-table-column
                                                prop="fileName"
                                                label="文件名"
                                        >
                                        </el-table-column>
                                        <el-table-column
                                                prop="content"
                                                label="标题">
                                        </el-table-column>
                                        <el-table-column
                                                prop="uploadTime"
                                                label="上传时间">
                                        </el-table-column>
                                        <el-table-column
                                                prop="neeqTime"
                                                label="新三板发布时间">
                                        </el-table-column>
                                        <el-table-column
                                                prop="fileSize"
                                                label="文件大小">
                                        </el-table-column>
                                        <el-table-column label="操作">
                                            <template slot-scope="scope">
                                                <el-button
                                                        size="mini"
                                                        type="success"
                                                        @click="window.location.href=${cxt}scope.row.fileName"
                                                        icon="el-icon-search">预览附件
                                                </el-button>
                                                <el-button
                                                        size="mini"
                                                        type="danger"
                                                        @click="delAnnouncement(scope.row.id)"
                                                        icon="el-icon-delete">
                                                    删除
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </template>
                            </div>
                        </el-col>
                    </el-row>
                    <!--分页-->
                    <el-row>
                        <el-col :span="24">
                            <div class="grid-content bg-purple-dark" style="margin-top: 30px;">
                                <div align="center">
                                    <el-pagination
                                            @size-change="handleSizeChange"
                                            @current-change="handleCurrentChange"
                                            :current-page="currentPage"
                                            :page-sizes="[10, 20, 30, 40]"
                                            :page-size="pagesize"
                                            layout="total, sizes, prev, pager, next, jumper"
                                            :total="totalCount">
                                    </el-pagination>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </template>
            </div>
        </el-col>
    </el-row>
</div>
</body>
<script>
    new Vue({
        el: '#app',
        data: {
            partner: [],
            //请求的URL
            url: '${cxt}/announcement/list',
            //搜索条件
            criteria: '',
            //根据时间搜索条件
            date: '',
            //下拉菜单选项
            select: '',
            //默认每页数据量
            pagesize: 10,
            //默认高亮行数据id
            highlightId: -1,
            //当前页码
            currentPage: 1,
            //查询的页码
            start: 1,
            //默认数据总数
            totalCount: 100,
        },
        methods: {
            //从服务器读取数据
            loadData: function (criteria, pageNum, pageSize) {
                var _this = this;
                axios.get(this.url, {
                    params: {
                        parameter: criteria,
                        pageNum: pageNum,
                        pageSize: pageSize
                    }
                }).then(function (res) {
                    if (!res.data) {
                        alert('数据加载失败，请刷新页面重试！');
                        return;
                    }
                    var data = res.data;
                    for (var i = 0; i < res.data.pageData.length; i++) {
                        data.pageData[i].uploadTime = moment(data.pageData[i].uploadTime).format('YYYY-MM-DD');//moment.js 格式化时间戳
                        data.pageData[i].fileSize = bytesToSize(data.pageData[i].fileSize);//moment.js 格式化时间戳
                        data.pageData[i].neeqTime = moment(data.pageData[i].neeqTime).format('YYYY-MM-DD');//moment.js 格式化时间戳bytesToSize
                    }
                    _this.partner = data.pageData;
                    _this.totalCount = data.number;
                }).catch(function (error) {
                    alert('数据加载失败，请刷新页面重试！');
                });
            },
            //每页显示数据量变更
            handleSizeChange: function (val) {
                this.pagesize = val;
                this.loadData(this.criteria, this.currentPage, this.pagesize);
            },
            //页码变更
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.loadData(this.criteria, this.currentPage, this.pagesize);
            },
            //搜索
            search: function () {
                this.loadData(this.criteria, this.currentPage, this.pagesize);
                this.criteria = ''
            },
            //根据时间搜索
            search_time: function () {
                this.loadData(this.date, this.currentPage, this.pagesize);
                this.date = ''
            },
            //序号
            indexMethod(index) {
                return index + 1;
            },
            delAnnouncement(row) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.del_announcement(row);
                    this.loadData(this.criteria, this.currentPage, this.pagesize);
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            del_announcement(row) {
                axios.get('${cxt}/announcement/delAnnouncement', {
                    params: {
                        id: row,
                        del: 1//将数据库中del字段改为1 （删除状态）
                    }
                });
            }
        },
        //载入数据
        created: function () {
            this.loadData(this.criteria, this.currentPage, this.pagesize);
        },
        mounted: function () {
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

    function bytesToSize(bytes) {
        if (bytes === 0) return '0 B';

        var k = 1024;

        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

        i = Math.floor(Math.log(bytes) / Math.log(k));

        return (bytes / Math.pow(k, i)).toPrecision(4) + ' ' + sizes[i];
        //toPrecision(3) 后面保留一位小数，如1.0GB
        //return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
    }
</script>
<script>

</script>
</html>
