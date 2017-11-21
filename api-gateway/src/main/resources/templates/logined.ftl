<html>
<head>
</head>
<body>
<div class="container">
    <h2>欢迎您${username}</h2>
    <#list routes as route >

    <a href="apiview.html?baseUrl=https://${zuulUrl}/${route.id}/v2/api-docs">${route.id} API调试</a>
    </#list>
    <form action="/logout" method="post">
        <button type="submit" >API-Gateway退出</button>
    </form>
    <form action="/uaa/logout" method="post">
        <button type="submit">UAA退出</button>
    </form>

</div>
</body>
</html>
