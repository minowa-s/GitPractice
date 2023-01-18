<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dto.Kadaidto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>下記の内容で登録します。宜しければ確認ボタンをおしてください</p>
	<%
		Kadaidto customer = (Kadaidto)session.getAttribute("input_data");
	%>
	名前:<%=customer.getName() %><br>
	年齢:<%=customer.getOld() %><br>
		<%
		String genderStr = request.getParameter("gender");
		int ge = Integer.parseInt(genderStr);
		String gender = ge == 1 ?"男":"女";
		%>
		性別:
		<%=customer.getGender() %><br>
		<br>
		電話番号:<%=customer.getTel() %><br>
		メールアドレス:<%=customer.getMail() %>
		パスワード:********<br>
		
		<a href="KadaiRegisterExecuteServlet">確認</a>
		<a href="KadaiRegisterFormServlet">戻る</a>

</body>
</html>