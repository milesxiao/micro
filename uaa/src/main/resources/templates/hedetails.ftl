<html>
<head>
</head>
<body>
<div class="container">
<#if entity??>
  <h2>宣教类型：${entity.missionType}</h2>
  <h2>宣教标题：${entity.missionName}</h2>
  <h2>宣教内容：${entity.patientNotesContent}</h2>
<#else>
  <h2>健康宣教模板不存在</h2>
</#if>
</div>
</body>
</html>
