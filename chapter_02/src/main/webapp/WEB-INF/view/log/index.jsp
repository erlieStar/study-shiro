<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>用户名</th>
            <th>操作</th>
            <th>方法</th>
            <th>参数</th>
            <th>执行时间（毫秒）</th>
            <th>IP地址</th>
            <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logList}" var="log">
            <tr>
                <td>${log.username}</td>
                <td>${log.operation}</td>
                <td>${log.method}</td>
                <td>${log.params}</td>
                <td>${log.time}</td>
                <td>${log.ip}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${log.createDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/bootstrap/js/bootstrap.min.js"></script>
</html>
