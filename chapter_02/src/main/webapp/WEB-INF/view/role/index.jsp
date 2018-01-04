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
    <button class="btn btn-default" style="margin-bottom: 20px">新增角色</button>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>用户名</th>
                <th>描述</th>
                <th>权限字符串</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${roleList}" var="role">
                <tr>
                    <td>${role.roleName}</td>
                    <td>${role.description}</td>
                    <td>
                        <c:forEach items="${permissionMap.get(role.id)}" var="permission">
                            ${permission}
                        </c:forEach>
                    </td>

                    <td>
                        <shiro:hasPermission name="role:update">
                            <a href="${rootPath}/role/${role.id}/update">修改</a>
                        </shiro:hasPermission>

                        <shiro:hasPermission name="role:delete">
                            <a href="${rootPath}/role/${role.id}/delete">删除</a>
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
            location.href = "${rootPath}/role/create";
        });
    });
</script>
</html>
