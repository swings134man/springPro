<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
    	String sId = (String)session.getAttribute("sId"); //세션
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글작성</title>
</head>
<body>
<h2>글작성</h2>
<form action="create.bbs">
		제목 : <input type="text" name="title" id="title"> <br>
		작성자 : <input type="text" name="writer" id="writer" value="<%= sId %>"> <br>
		내용 : <input type="text" name="content" id="content"> <br>
	<button>글작성</button>
	</form>



</body>
</html>