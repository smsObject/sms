<#import "../spring.ftl" as s />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SMS Login</title>
    <script src="<@s.url '/js/jquery.min.js'/>"></script>
    <style type="text/css" media="all">
        * {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial;
        }

        .login {
            padding-top: 6%;
            width: 500px;
            margin: 0 auto;
        }

        .login h1 {
            margin: 0;
        }

        .top {
            position: relative;
            width: 100%;
            text-align: center;
        }

        .logo-bg {
            background: url("<@s.url '/css/metro-wms'/>/images/app/logo_bg.jpg") repeat;
        }

        .login-cnt {
            margin: 0 auto;
            width: 500px;
        }

        .login-panel {
            width: 500px;
            margin-top: 50px;
            border: 1px solid #c5c8cc;
            background: #FFFFFF;
        }

        .input-title {
            font-size: 14px;
        }

        .login-input {
            width: 280px;
            border: 1px solid #c5c8cc;
            height: 48px;
            background: #e8e8e9;
            font-size: 15px;
        }

        .login-input:focus {
            outline: none;
        }

        .login-tab {
            width: 290px;
            margin: 0 auto;
            padding: 20px 0;
        }

        .login-tab td {
            padding: 5px 0;
        }

        .error-msg {
            display: block;
            color: #ff0000;
            font-size: 12px;
            margin-top: 4px;
        }

        .error-focus {
            border: 1px solid #ff0000;
        }

        .fn-hide {
            display: none;
        }

        .wms-linkbutton {
            display: block;
            width: 280px;
            height: 48px;
            line-height: 48px;
            background: #c41230;
            border-bottom: 2px solid #97011a;
            text-align: center;
            font-size: 18px;
            text-decoration: none;
            color: #FFF;
        }

        .wms-linkbutton:hover {
            background: #dd2443;
        }

        .copyright {
            margin-top: 10px;
            text-align: center;
            font-size: 12px;
        }

        .help-links {
            margin-top: 10px;
            text-align: center;
            font-size: 12px;
            color: #000;
        }
    </style>

</head>
<body class="logo-bg">
<div class="login">
    <div class="top">
        <img src="<@s.url '/css/metro-wms'/>/images/app/logo.png" alt="">

        <h1>远程抄表管理系统</h1>
    </div>
</div>
<div class="login-cnt">
    <div class="login-panel">
        <div class="login-tab">
            <form name="login_form" id="login_form" method="post" action="<@s.url '/auth/signIn'/>">
                <table>
                    <tbody>
                    <tr>
                        <td class="input-title">Username</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" class="login-input J-login-user" name="userName"
                                   value="<#if userName??>${userName}</#if>"/>
                            <span class="error-msg fn-hide">Please enter your username.</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="input-title">Password</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" class="login-input J-login-pwd" autocomplete="off" name="password"
                                   value=""/>
                            <span class="error-msg fn-hide">Your password must between 6-20 characters.</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <#if flash_message??>
                            <span class="error-msg J-error-msg">${flash_message}</span>
                        </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="javascript:void(0)" class="wms-linkbutton" onclick="submitForm()">Log in</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="copyright">&copy; 2005-2014 Jollychic Copyright, All Rights Reserved.</div>
</div>
<script type="text/javascript">
    function submitForm() {
        var $user = $(".J-login-user");
        var $pwd = $(".J-login-pwd");
        var user = $.trim($user.val());
        var pwd = $.trim($pwd.val());
        var $msg = $('.J-error-msg');
        if (!user) {
            $user.addClass('error-focus').siblings('.error-msg').removeClass('fn-hide');
            $msg.addClass('fn-hide');
            return false;
        }
        if (pwd.length < 6 || pwd.length > 20) {
            $pwd.addClass('error-focus').siblings('.error-msg').removeClass('fn-hide');
            $msg.addClass('fn-hide');
            return false;
        }
        $user.removeClass('error-focus').siblings('.error-msg').addClass('fn-hide');
        $pwd.removeClass('error-focus').siblings('.error-msg').addClass('fn-hide');
        $msg.removeClass('fn-hide');
        $('#login_form').submit();
    }

    document.onkeydown = function (event) {
        e = event ? event : (window.event ? window.event : null);
        if (e.keyCode == 13) {
            submitForm();
        }
    }
</script>
</body>
</html>
