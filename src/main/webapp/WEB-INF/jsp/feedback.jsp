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
    </style>
</head>
<body>
<div id="app">
    <el-row>
        <el-col :span="4">
            <div class="grid-content bg-purple-dark">
                <button></button>
            </div>
        </el-col>
        <el-col :span="20">
            <div class="grid-content bg-purple-dark">
                <template>
                    <!--查询头-->
                    <el-row>

                        <el-col :span="11">
                            <el-select v-model="value" placeholder="请选择反馈类型"  @change="search_type(value)">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="2">
                            <div class="grid-content bg-purple-dark">&nbsp;</div>
                        </el-col>
                        <el-col :span="11">
                            <div class="grid-content bg-purple-dark">
                                <el-input id="test1" placeholder="根据时间搜索" v-model="date"
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
                                        :data="cooperation"
                                        border
                                        style="width: 100%">
                                    <el-table-column type="expand">
                                        <template slot-scope="props">
                                            <el-form label-position="left" inline class="demo-table-expand">
                                                <el-form-item label="反馈内容">
                                                    <span>{{ props.row.content }}</span>
                                                </el-form-item>
                                            </el-form>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            type="index"
                                            :index="indexMethod">
                                    </el-table-column>
                                    <el-table-column
                                            label="反馈人"
                                            prop="name">
                                    </el-table-column>
                                    <el-table-column
                                            label="手机号"
                                            prop="phone">
                                    </el-table-column>
                                    <el-table-column
                                            label="QQ"
                                            prop="qq">
                                    </el-table-column>
                                    <el-table-column
                                            label="反馈类型"
                                            prop="type">
                                    </el-table-column>
                                    <el-table-column
                                            width="180"
                                            label="反馈时间"
                                            prop="creationTime">
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
            options: [{
                value: 71,
                label: '对产品的意见和建议'
            }, {
                value: 72,
                label: '对服务的意见和建议'
            }, {
                value:73,
                label: '对管网的意见和建议'
            }, {
                value: 74,
                label: '其他'
            }],
            value: '',
            cooperation: [],
            //请求的URL
            url: '${cxt}/customerFeedback/list',
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
                    var project = ['对产品的意见和建议', '对服务的意见和建议', '对管网的意见和建议', '其他']
                    for (var i = 0; i < res.data.pageData.length; i++) {
                        data.pageData[i].creationTime = moment(data.pageData[i].creationTime).format('YYYY-MM-DD HH:mm:ss');//moment.js 格式化时间戳
                        data.pageData[i].type = project[data.pageData[i].type - 71];
                    }
                    _this.cooperation = data.pageData;
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
            //根据反馈类型搜索
            search_type: function () {
                console.log(this.value + "11111")
                this.loadData(this.value, this.currentPage, this.pagesize);
                this.value = ''
            },
            //序号
            indexMethod(index) {
                return index + 1;
            },
            //点击备注弹框
            remark(row, index) {
                this.loadData(this.criteria, this.currentPage, this.pagesize);
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
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });

            },
            save_remark(row, value) {
                axios.get('${cxt}/customerFeedback/saveRemark', {
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
                elem: '#test1',
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