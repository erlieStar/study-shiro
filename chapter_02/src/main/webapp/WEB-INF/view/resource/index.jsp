<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/static/treegrid/jquery.treegrid.css">
</head>
<body>
    <table class="tree table table-bordered">
        <thead>
        <tr>
            <th>名称</th>
            <th>类型</th>
            <th>URL路径</th>
            <th>权限字符串</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resourceList}" var="resource">
            <%--如果是根结点则显示在最上面--%>
            <tr <c:if test="${not resource.rootNode}">class="treegrid-${resource.id} treegrid-parent-${resource.parentId}</c:if>
            <c:if test="${resource.rootNode}">class="treegrid-${resource.id}</c:if>">
                <td>${resource.name}</td>
                <td>${resource.type.value}</td>
                <td>${resource.url}</td>
                <td>${resource.permission}</td>
                <td>
                    <shiro:hasPermission name="resource:create">
                        <c:if test="${resource.type ne 'button'}">
                            <a href="${pageContext.request.contextPath}/resource/${resource.id}/create">添加子节点</a>
                        </c:if>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="resource:update">
                        <a href="${pageContext.request.contextPath}/resource/${resource.id}/update">修改</a>
                    </shiro:hasPermission>

                    <c:if test="${not resource.rootNode}">
                        <shiro:hasPermission name="resource:delete">
                            <a class="deleteBtn" href="#" data-id="${resource.id}">删除</a>
                        </shiro:hasPermission>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${rootPath}/static/treegrid/jquery.treegrid.min.js"></script>
<script src="${rootPath}/static/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script src="${rootPath}/static/treegrid/jquery.treegrid.extension.js"></script>
<script src="${rootPath}/static/treegrid/tree.table.js"></script>
<script>
    $(function () {
        $(".tree").treegrid();
        $(function () {
            $(".deleteBtn").click(function () {
                if (confirm("确认删除?")) {
                    location.href = "${rootPath}/resource/" + $(this).data('id') + "/delete";
                }
            });
        });
    })
</script>
</html>
