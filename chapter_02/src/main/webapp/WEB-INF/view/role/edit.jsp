<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<html>
<head>
    <title></title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/static/ztree/css/zTreeStyle/zTreeStyle.css">
</head>
<body>
<%--commandName指定form绑定的model--%>
<form:form method="post" commandName="role">
    <%--通过path属性指定绑定model的值--%>
    <form:hidden path="id"/>
    <form:hidden path="available"/>

    <div class="form-group">
        <form:label path="roleName">角色名</form:label>
        <form:input path="roleName"/>
    </div>

    <div class="form-group">
        <form:label path="description">角色描述</form:label>
        <form:input path="description"/>
    </div>

    <div class="form-group">
        <form:label path="resourceIdList">资源列表</form:label>
        <form:hidden path="resourceIdList"/>
    </div>
    <ul id="tree" class="ztree"></ul>
    <form:button class="btn btn-default">${op}</form:button>
</form:form>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/ztree/jquery.ztree.all.min.js"></script>
<script>
    $(function () {
        var setting = {
            check: {
                //显示单选框或者复选框
                enable: true ,
                //checkbox被勾选或者被取消后，父子结点不影响
                chkboxType: { "Y": "", "N": "" }
            },
            data: {
                //使用简单数据模式
                simpleData: {
                    enable: true
                }
            },
            callback: {
                //捕获结点被点击事件的回调函数
                onCheck: onCheck
            }
        };

        var zNodes =[
            <c:forEach items="${resourceList}" var="r">
            <c:if test="${not r.rootNode}">
            { id:${r.id}, pId:${r.parentId}, name:"${r.name}", checked:${zhangfn:in(role.resourceIdList, r.id)}},
            </c:if>
            </c:forEach>
        ];

        function onCheck(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
                nodes = zTree.getCheckedNodes(true),
                id = "",
                name = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                id += nodes[i].id + ",";
            }
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            if (name.length > 0 ) name = name.substring(0, name.length-1);
            $("#resourceIdList").val(id);
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting, zNodes);
        });
    })

</script>
</html>
