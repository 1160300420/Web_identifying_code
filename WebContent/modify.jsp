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

<script type="text/javascript" src="./CryptoJS/crypto-js.js"></script>
<script type="text/javascript" src="./CryptoJS/aes.js"></script>
<script src="./CryptoJS/pad-pkcs7.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="md5.js"></script>
<script type="text/javascript">
    function aesEncrypt() {
        var password1 = document.getElementById("password").value;
        var key = CryptoJS.enc.Utf8.parse(hex_md5(password1));
        var iv   = CryptoJS.enc.Utf8.parse("face0123456789ai");
        console.log('key:', key);
        var src=document.getElementById("password_t").value;
        src=hex_md5(src);
        console.log('src:', src);
        var enc = CryptoJS.AES.encrypt(src ,key,{
        	iv:iv,
        	padding: CryptoJS.pad.Pkcs7,
            mode: CryptoJS.mode.CBC
        })
        document.getElementById("password_t").value=enc.ciphertext.toString();
        var loginForm = document.getElementById("form_t");
        loginForm.submit();
    }

    function change() {
        //DateTime 格式中包含非法字符导致http header解析失败
        document.getElementById("checkimgcode").src = document
                .getElementById("checkimgcode").src
                + "?nocache=" + Math.random();
    }
    function check(){
    	if(document.getElementById("password_t").value!=document.getElementById("password_s").value){
    		//window.alert('两次输入不一致');
    	}
    }
</script>
</head>
<body>
	<center>
		<form id="form_t" action="Modify" method="post">
			<font face="宋体">Register</font>
			<hr>
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" name="ID"></td>
				</tr>
				<tr>
					<td>旧密码</td>
					<td><input type="text" id="password"></td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input type="text" id="password_t" name="newPW"></td>
				</tr>
				<tr>
                    <td>确认密码</td>
                    <td><input type="text" id="password_s"></td>
                </tr>
			</table>

			<table>
				<tr>
					<td><input type="button" value="提交"  onclick="aesEncrypt()"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>