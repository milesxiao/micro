<html>
<head>
</head>
<body>
<div class="container">
<#if result == "success">
    <h1>${nickName}，您的微信app授权流程已完成。</h1>
<#else>
    <h1>微信app医院信息修改失败！${errorMsg}</h1>
</#if>
</div>
</body>
</html>
