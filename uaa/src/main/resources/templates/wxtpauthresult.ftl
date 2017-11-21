<html>
<head>
</head>
<body>
<div class="container">
<#if result == "success">
    <#if wxAppInfo?? && wxAppInfo.id??>
      <h1>${wxAppInfo.id}，您已成功授权提灯医院微信第三方平台.</h1>
      <br/>
      <h2>您需要继续完善微信app对应的医院信息：</h2>
      <form role="form" action="/uaa/wxapphospital/edit" method="post">
        <input type="hidden" id="wxappId" name="wxappId" value="${wxAppInfo.id}" />
        <div class="form-group">
          <input type="text" class="form-control" id="hospitalId" name="hospitalId" value="<#if wxAppInfo.hospitalId??>${wxAppInfo.hospitalId}<#else></#if>" placeholder="医院ID"/>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" id="hospitalName" name="hospitalName" value="<#if wxAppInfo.hospitalName??>${wxAppInfo.hospitalName}<#else></#if>" placeholder="医院名称"/>
        </div>
        <button type="submit" class="btn btn-primary btn-custom">提交</button>
      </form>
      <br/>
    <#else>
      <h2>授权第三方平台异常，请重新操作！</h2>
    </#if>
<#else>
  <h1>授权第三方平台失败，请重新操作！</h1>
</#if>
</div>
</body>
</html>
