<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String sId = (String)session.getAttribute("sId"); //세션
    	 if(sId == null){
    		sId = "";
    	} 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<%
if (sId != null){
%>
	<h2><%= sId %>환영합니다!</h2>
<%}else { %>
	<h2>환영합니다!</h2>
<%} %>
	<hr>
	<form action="login.member">
		ID : <input type="text" name="id" id="id"> <br>
		PW : <input type="text" name="pw" id="pw"> <br>
	<button>로그인</button> <br>
	</form>
	<a href="logout.member"><button>로그아웃</button> </a>
	<hr>
	<a href="member_join.jsp">회원가입</a>
</body>
</html>