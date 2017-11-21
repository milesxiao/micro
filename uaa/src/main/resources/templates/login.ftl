<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>提灯临床护理信息系统</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login-top">
    <img src="images/logo.png"/>
</div>
<div class="login-box">
    <div class="login-box-body">
        <p class="login-box-msg">用户登录</p>
        <form role="form" action="login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名"/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码"/>
            </div>
        <#--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <button type="submit" class="btn btn-primary btn-custom">登录</button>
        </form>
    </div>
</div>
<div class="login-footer">
    ©2016-2017 湖南提灯医疗科技有限公司
</div>
</body>
</html>
