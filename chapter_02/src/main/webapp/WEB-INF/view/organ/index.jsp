<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${rootPath}/static/ztree/css/zTreeStyle/zTreeStyle.css">
</head>
<body>

<div class="row">
    <div class="col-md-4">
        <ul id="tree" class="ztree"></ul>
    </div>
    <div class="col-md-8">
        <label for="name">名称</label>
        <input type="text"id="name">
        <input type="hidden" id="orgid">
        <button class="btn btn-default" id="updateBtn">修改</button>
        <button class="btn btn-default" id="deleteBtn">删除</button>
        <button class="btn btn-default" id="appendBtn">添加子节点</button>
        <button class="btn btn-default" id="moveBtn">移动节点</button>
    </div>
</div>

</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/ztree/jquery.ztree.all.min.js"></script>
<script>
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    $('#name').val(treeNode.name);
                    $('#orgid').val(treeNode.id);
                }
            }
        };

        var zNodes =[
            <c:forEach items="${organList}" var="organ">
            { id:${organ.id}, pId:${organ.parentId}, name:"${organ.name}", open:${organ.rootNode}},
            </c:forEach>
        ];

        $(document).ready(function(){
            $.fn.zTree.init($("#tree"), setting, zNodes);
        });

        $('#updateBtn').click(function () {
            var value = $("#orgid").val();
            if (value == "") {
                alert("请选中节点");
            } else {
                window.location.href="${rootPath}/organization/" + value + "/update";
            }
        });

        $('#deleteBtn').click(function () {
            var value = $("#orgid").val();
            if (value == "") {
                alert("请选中节点");
            } else {
                window.location.href="${rootPath}/organization/" + value + "/delete";
            }
        });

        $('#appendBtn').click(function () {
            var value = $("#orgid").val();
            if (value == "") {
                alert("请选中节点");
            } else {
                window.location.href="${rootPath}/organization/" + value + "/create";
            }
        });

        $('#moveBtn').click(function () {
            var value = $("#orgid").val();
            if (value == "") {
                alert("请选中节点")
            } else {
                window.location.href="${rootPath}/organization/" + value + "/move";
            }
        });
    });
</script>
</html>
