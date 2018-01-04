<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <form:form id="form" method="post" commandName="child">
        <form:hidden path="id"/>
        <form:hidden path="available"/>
        <form:hidden path="parentId"/>

        <div class="form-group">
            <label>父节点名称：</label>
                ${parent.name}
        </div>

        <div class="form-group">
            <form:label path="name">子节点名称：</form:label>
            <form:input path="name"/>
        </div>

        <div class="form-group">
            <form:label path="priority">子节点优先级：</form:label>
            <form:input path="priority"/>
        </div>

        <form:button class="btn btn-default">新增子节点</form:button>
    </form:form>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script>
</script>
</html>
