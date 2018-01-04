<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<html>
<head>
    <title></title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<%--commandName指定form绑定的model--%>
<form:form method="post" commandName="user">
    <%--通过path属性指定绑定model的值--%>
    <form:hidden path="id"/>
    <form:hidden path="password"/>
    <form:hidden path="salt"/>

    <div class="form-group">
        <form:label path="username">用户名</form:label>
        <form:input path="username"/>
    </div>

    <div class="form-group">
        <form:label path="roleIdList">角色列表</form:label>
        <form:select path="roleIdList" items="${roleList}" itemLabel="description" itemValue="id" multiple="true"/>
    </div>

    <form:button class="btn btn-default">${op}</form:button>
</form:form>
</body>
<script src="${rootPath}/static/jquery/jquery-1.11.0.min.js"></script>
</html>
