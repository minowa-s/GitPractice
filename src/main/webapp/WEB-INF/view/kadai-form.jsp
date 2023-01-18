<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="dto.Kadaidto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String errorCode = request.getParameter("error");
	if(errorCode !=null && errorCode.equals("1")){
		Kadaidto ac = (Kadaidto)session.getAttribute("input_data");
%>
<p style="color:red">登録に失敗しました。</p>
	<h3>新規会員登録</h3>
	<form action="KadaiRegisterConfirmServlet" method="post">
		名前:<input type="text" name="name" value="<%=ac.getName() %>"><br>
		年齢:<input type="text" name="old" value="<%=ac.getOld() %>"><br>
		性別:
		男:<input type="radio" name="gender" value="1"><br>
		女:<input type="radio" name="gender" value="2"><br>
		電話番号:<input type="text" name="tel" value="<%=ac.getTel() %>"><br>
		メールアドレス:<input type="text" name="mail" value="<%=ac.getMail() %>"><br>
		パスワード:<input type="password" name="password" value="<%=ac.getPassword() %>"><br>
		<input type="submit" value="登録">
			</form>
			<%
			}else{
			%>
			<h3>新規会員登録</h3>
			<form action="KadaiRegisterConfirmServlet" method="post">
			名前:<input type="text" name="name" ><br>
		年齢:<input type="text" name="old"><br>
		性別:<br>
		男:<input type="radio" name="gender"value="1">
		女:<input type="radio" name="gender" value="2"><br>
		電話番号:<input type="text" name="tel" ><br>
		メールアドレス:<input type="text" name="mail"><br>
		パスワード:<input type="password" name="password"><br>
		<input type="submit" value="登録">
			</form>
			<%
			}			
			%>
	

</body>
</html>