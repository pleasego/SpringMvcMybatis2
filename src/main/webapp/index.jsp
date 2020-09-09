<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/bootstrap-table.min.css">
</head>
<body>

    <table id="tab"></table>
    <form>
        请选择删除的用户:<input type="text" name="userId" id="userId">
        <input type="button" onclick="delUser()" value="确认删除">
        <p id="del" style="color: red"></p>
    </form>
    <form id="addUsers">
        用户名：<input type="text" name="userName"/>
        账 号：<input type="text" name="loginId"/>
        密 码：<input type="password" name="loginPwd"/>
        <input type="button" onclick="addUser()" value="增加用户"/>
        <p id="add" style="color: red"></p>
    </form>
    <form id="updUser">
        用户id：<input type="text" name="userId"/>
        用户名：<input type="text" name="userName"/>
        账 号：<input type="text" name="loginId"/>
        密 码：<input type="password" name="loginPwd"/>
        <input type="button" onclick="updUser()" value="确认修改">
        <p id="upd" style="color: red"></p>
    </form>
<script src="static/jquery-3.5.1.min.js"></script>
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
    <script src="static/bootstrap-table.min.js"></script>
    <script src="static/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function (){
        init();
    })
    function init() {
        $('#tab').bootstrapTable({
            url: 'getUsers',         //请求后台的URL（*）
            contentType : "application/x-www-form-urlencoded",
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams:queryParam,
            responseHandler:responseHandler,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 0,                       //初始化加载第一页，默认第一页
            pageSize: 2,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'userId',
                title: '编号'
            },{
                field: 'userName',
                title: '用户名'
            }, {
                field: 'loginId',
                title: '账号'
            }, {
                field: 'loginPwd',
                title: '密码'
            }]
        })
        function queryParam(params) {
            var param = {
                limit: this.limit, // 页面大小
                offset: this.offset, // 页码
                pageNumber: this.pageNumber,
                pageSize: this.pageSize
            };
            return param;
        }
        function responseHandler(res) {
            console.log(res)
            if (res) {
                return {
                    "rows" : res.obj.list,
                    "total" : res.obj.total
                };
            } else {
                return {
                    "rows" : [],
                    "total" : 0
                };
            }
        }
    }
    $("#tab").bootstrapTable("refresh",{
        silent:true //静态刷新表格
    })
    function delUser(){
        $.ajax({
            url:"delUser",
            type:"post",
            data:{"userId":$("#userId").val()},
            dataType:"json",
            success:function (data){
                if(data.stateCode=200){
                    $("#del").html(data.msg)
                }else{
                    $("#del").html(data.msg)
                }
                // 删除成功后刷新页面
                window.location.reload();
            },
            error:function (data){
                alert("执行失败")
            }
        })
    }

    function addUser() {
        $.ajax({
            url:"addUser",
            type:"post",
            data: $("#addUsers").serialize(),
            dataType: "json",
            success:function (data){
                if(data.stateCode=200){
                    $("#add").html(data.msg);
                }else{
                    $("#add").html(data.msg);
                }
            },
            error:function (data){
                alert("执行失败")
            }
        })
    }

    function updUser() {
        $.ajax({
            url:"updUser",
            type:"post",
            data: $("#updUser").serialize(),
            dataType: "json",
            success:function (data){
                if(data.stateCode=200){
                    $("#upd").html(data.msg);
                }else{
                    $("#upd").html(data.msg);
                }
            },
            error:function (data){
                alert("执行失败")
            }
        })
    }
</script>
</body>
</html>