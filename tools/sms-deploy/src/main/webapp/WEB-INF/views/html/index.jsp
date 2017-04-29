<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <title>远程抄表管理系统</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="../../resources/js/element/index.css">
    <!-- 引入组件库 -->
    <script src="../../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../../resources/js/element/vue.js"></script>
    <script src="../../resources/js/element/index.js"></script>
    <script src="../../resources/js/sms/index.js"></script>
    <script>

    </script>
</head>

<body>
<div id="wap">
    <el-menu theme="dark" :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">公司信息</el-menu-item>
        <el-submenu index="2">
            <template slot="title">我的设备</template>
            <el-menu-item index="2-1">GSM</el-menu-item>
            <el-menu-item index="2-2">仪表</el-menu-item>
            <el-menu-item index="2-3">数据</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
            <template slot="title">用户管理</template>
            <el-menu-item index="3-1">用户信息</el-menu-item>
            <el-menu-item index="3-2">价格设置</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
            <template slot="title">系统设置</template>
            <el-menu-item index="4-1">账号管理</el-menu-item>
            <el-menu-item index="4-2">角色管理</el-menu-item>
        </el-submenu>
    </el-menu>
    <div class="line"></div>
    <el-breadcrumb separator="/">
        <el-breadcrumb-item>{{item1}}</el-breadcrumb-item>
        <el-breadcrumb-item>{{item2}}</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- gsm 模块-->
    <jsp:include page="device/gsmManager.jsp"></jsp:include>

    <!-- 仪表模块-->
    <jsp:include page="device/meterManager.jsp"></jsp:include>

    <!-- 仪表数据模块-->
    <jsp:include page="data/meterData.jsp"></jsp:include>

</div>
</body>
</html>