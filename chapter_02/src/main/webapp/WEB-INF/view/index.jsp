<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <% String rootPath = request.getContextPath();request.setAttribute("rootPath", rootPath); %>
    <link rel="stylesheet" href="${rootPath}/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${rootPath}/static/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${rootPath}/static/adminlte/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${rootPath}/static/adminlte/css/font-awesome.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
        <header class="main-header">
            <a href="javascript:void(0);" class="logo">
                <!-- mini logo for sidebar mini 50x50 pixels -->
                <span class="logo-mini"><b>权限</b></span>
                <!-- logo for regular state and mobile devices -->
                <span class="logo-lg"><b>权限管理系统</b></span>
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <div style="float:left;color:#fff;padding:15px 10px;">欢迎<shiro:principal property="username"/></div>
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li><a href="javascript:;" @click="updatePassword"><i class="fa fa-lock"></i> &nbsp;修改密码</a></li>
                        <li><a href="${rootPath}/logout"><i class="fa fa-sign-out"></i> &nbsp;退出系统</a></li>
                    </ul>
                </div>
            </nav>
        </header>
        <!-- Left side column. contains the sidebar -->
        <aside class="main-sidebar">
            <!-- sidebar: style can be found in sidebar.less -->
            <section class="sidebar">
                <!-- /.search form -->
                <!-- sidebar menu: : style can be found in sidebar.less -->
                <ul class="sidebar-menu">
                    <li class="header">导航菜单</li>
                    <c:forEach items="${menu}" var="m">
                        <li>
                            <a href="javascript:;" id="${rootPath}${m.url}">${m.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <!-- =============================================== -->
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                    <li class="active"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 首页</li>
                    <li class="active"></li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content" style="background:#fff;">
                <iframe scrolling="yes" frameborder="0" style="width:100%;min-height:200px;overflow:visible;background:#fff;" src="${rootPath}/welcome"></iframe>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                Version 3.0.0
            </div>
            Copyright &copy; 2017 <a href="http://www.erlie.cc" target="_blank">www.erlie.cc</a> All Rights Reserved
        </footer>

        <!-- Add the sidebar's background. This div must be placed
             immediately after the control sidebar -->
        <div class="control-sidebar-bg"></div>

        <!-- 修改密码 -->
        <div id="passwordLayer" style="display: none;">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="form-group">
                        <div class="col-sm-2 control-label">账号</div>
                        <span class="label label-success" style="vertical-align: bottom;">{{user.username}}</span>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 control-label">原密码</div>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" v-model="password" placeholder="原密码"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 control-label">新密码</div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" v-model="newPassword" placeholder="新密码"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
<script src="${rootPath}/static/jquery/jquery-3.2.1.js"></script>
<script src="${rootPath}/static/bootstrap/js/bootstrap.js"></script>
<script src="${rootPath}/static/layer/layer.js"></script>
<script src="${rootPath}/static/adminlte/js/adminlte.min.js"></script>
<script>
    //iframe自适应
    $(window).on('resize', function() {
        var $content = $('.content');
        $content.height($(this).height() - 120);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }).resize();
    $('a').click(function () {
        var url = $(this).attr('id');
        $('iframe').attr('src',url);
    })
</script>
</html>
