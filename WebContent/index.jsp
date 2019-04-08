<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="md5.js"></script>
<script type="text/javascript">
	function cmdEncrypt() {
		var password1 = document.getElementById("password").value;
		document.getElementById("password").value = hex_md5(encodeURIComponent(hex_md5(encodeURIComponent(password1))+ document.getElementById("code").value));
		var loginForm = document.getElementById("form");
		loginForm.submit();
	}

	function change() {
		//DateTime 格式中包含非法字符导致http header解析失败
		document.getElementById("checkimgcode").src = document
				.getElementById("checkimgcode").src
				+ "?nocache=" + Math.random();
	}
</script>
</head>
<body>
	<center>
		<form id="form" action="Login" method="post">
			<font face="宋体">welcome</font>
			<hr>
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="ID"> <!-- <input type="hidden" id="pwd" name="PW">-->
					</td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="text" id="password" name="PW"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>验证码:<input type="text" id="code" name="Code"></td>
				</tr>
				<tr>
					<td><img src="ImageServlet" alt="验证码" id="checkimgcode"></td>
				</tr>
				<tr>
					<td><a href="javaScript:change()">看不清楚</a></td>
				</tr>
			</table>

			<table>
				<tr>
					<td><input type="button" value="登陆"
					onclick="cmdEncrypt()"></td>
				<!-- 	<td><input type="button" value="注册"
						onclick="window.location.href('register.jsp')"></td> -->
					<td><input type="button" value="修改密码"
						onclick="window.open('modify.jsp')"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>