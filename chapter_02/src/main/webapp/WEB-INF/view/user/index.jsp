<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<html>
<head>
    <title>Title</title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <shiro:hasPermission name="user:create">
        <button class="btn btn-default" style="margin-bottom: 20px">新增用户</button>
    </shiro:hasPermission>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>用户名</th>
            <th>角色列表</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>
                    <c:forEach items="${roleMap.get(user.id)}" var="name">
                        ${name},
                    </c:forEach>
                </td>
                <td>
                    <shiro:hasPermission name="user:update">
                        <a href="${rootPath}/user/${user.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:delete">
                        <a href="${rootPath}/user/${user.id}/delete">删除</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:update">
                        <a href="${rootPath}/user/${user.id}/change">改密</a>
                    </shiro:hasPermission>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $('.btn').click(function () {
            location.href = "${rootPath}/user/create";
        });
    });
</script>
</html>
