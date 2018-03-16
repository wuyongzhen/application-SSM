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
        .demo-table-expand {
            font-size: 0;
        }

        .demo-table-expand label {
            width: 120px;
            color: #99a9bf;
        }

        .demo-table-expand .el-form-item {
            margin-right: 0;
            margin-bottom: 0;
            width: 50%;
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
                        <el-col :span="11">
                            <div class="grid-content bg-purple-dark">
                                <el-input placeholder="请输入手机号或负责人搜索" v-model="criteria" style="padding-bottom:10px;">
                                    <el-button slot="append" v-on:click="search">搜索</el-button>
                                </el-input>
                            </div>
                        </el-col>
                        <el-col :span="2">
                            <div class="grid-content bg-purple-dark">&nbsp;</div>
                        </el-col>
                        <el-col :span="11">
                            <div class="grid-content bg-purple-dark">
                                <el-input id="timing" placeholder="根据时间搜索" v-model="date"
                                          style="padding-bottom:10px;">
                                    <el-button slot="append" v-on:click="search_time">搜索</el-button>
                                </el-input>
                            </div>
                        </el-col>
                    </el-row>
                    <!--table-->
                    <el-row>
                        <el-col :span="24">
                            <div class="grid-content bg-purple-dark">
                                <el-table
                                        :data="partner"
                                        border
                                        style="width: 100%">
                                    <el-table-column type="expand">
                                        <template slot-scope="props">
                                            <el-form label-position="left" inline class="demo-table-expand">
                                                <el-form-item label="合伙人年龄">
                                                    <span>{{ props.row.age }}</span>
                                                </el-form-item>
                                                <el-form-item label="手机号">
                                                    <span>{{ props.row.phone }}</span>
                                                </el-form-item>
                                                <el-form-item label="籍贯">
                                                    <span>{{ props.row.nativePlace }}</span>
                                                </el-form-item>
                                                <el-form-item label="电子邮箱">
                                                    <span>{{ props.row.mailbox }}</span>
                                                </el-form-item>
                                            </el-form>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            type="index"
                                            :index="indexMethod">
                                    </el-table-column>
                                    <el-table-column
                                            label="单位名称"
                                            prop="companyName">
                                    </el-table-column>
                                    <el-table-column
                                            label="合伙人"
                                            prop="name">
                                    </el-table-column>
                                    <el-table-column
                                            label="职务"
                                            prop="duty">
                                    </el-table-column>
                                    <el-table-column
                                            label="申请时间"
                                            prop="createTime">
                                    </el-table-column>
                                    <el-table-column
                                            label="单位地址"
                                            prop="site">
                                    </el-table-column>
                                    <el-table-column label="操作">
                                        <template slot-scope="scope">
                                            <el-button
                                                    size="mini"
                                                    type="success"
                                                    @click="remark(scope.row.id,scope.row.remark)">备注
                                            </el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
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
            url: '${cxt}/partner/list',
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
                        data.pageData[i].createTime = moment(data.pageData[i].createTime).format('YYYY-MM-DD HH:mm:ss');//moment.js 格式化时间戳
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
            //点击备注弹框
            remark(row, index) {
                this.$prompt('请输入备注信息', '提示', {
                    inputValue: index,
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then(({value}) => {
                    this.save_remark(row, value);
                    this.$message({
                        type: 'success',
                        message: '备注信息保存成功！'
                    });
                    this.loadData(this.criteria, this.currentPage, this.pagesize);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });

            },
            save_remark(row, value) {
                axios.get('${cxt}/partner/saveRemark', {
                    params: {
                        id: row,
                        remark: value
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
</script>
<script>

</script>
</html>
