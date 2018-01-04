<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<%--commandName指定form绑定的model--%>
<form:form method="post" commandName="resource">
    <%--通过path属性指定绑定model的值--%>
    <form:hidden path="id"/>
    <form:hidden path="available"/>
    <form:hidden path="parentId"/>

    <c:if test="${not empty parent}">
        <div class="form-group">
            <label>父节点名称：</label>
                ${parent.name}
        </div>
    </c:if>

    <div class="form-group">
        <form:label path="name"><c:if test="${not empty parent}">子</c:if>名称：</form:label>
        <form:input path="name"/>
    </div>
    <div class="form-group">
        <form:label path="type">类型：</form:label>
        <%--items里面的内容相当于<option>选项,itemLabel的值为枚举类的value值--%>
        <form:select path="type" items="${types}" itemLabel="value"/>
    </div>

    <div class="form-group">
        <form:label path="priority">优先级：</form:label>
        <form:input path="priority"/>
    </div>

    <div class="form-group">
        <form:label path="url">URL路径：</form:label>
        <form:input path="url"/>
    </div>


    <div class="form-group">
        <form:label path="permission">权限字符串：</form:label>
        <form:input path="permission"/>
    </div>

    <form:button class="btn btn-default">${op}</form:button>
</form:form>
</body>
<script src="${rootPath}/static/jquery/jquery-1.11.0.min.js"></script>
</html>
