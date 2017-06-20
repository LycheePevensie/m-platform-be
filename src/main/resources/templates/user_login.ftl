<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
<title>登录页面</title>
</head>
<body>
	<h1>用户登录</h1>
	<form action="/user/loginJudge" method="post">
		*用户名：<input type="text" id="username" name="username" value=""/>
		*密码：<input type="password" id="password" name="password" value=""/><br/>
		<input type="submit" value="登录" id="login"/>
		<input type="button" value="注册" id="register"/><br/>
	</form>
	<#if messageforl??>
		${messageforl}
	</#if>
	<script type="text/javascript">
		$().ready(function(){
			<!--$("#login").click(function(){location.href="/user_login/user_login_login?time="+new Date().getTime()+"&username="+$("#username").val()+"&password="+$("#password").val();});-->
			$("#register").click(function(){
				location.href="/user_login/user_login_register?time="+new Date().getTime();
				});
		});
	</script>
</body>
</html>