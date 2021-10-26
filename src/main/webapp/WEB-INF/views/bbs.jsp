<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="bbsList" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<!-- 반복문 통해서 list 출력 -->
	<table border="1">
		<th width="80">게시물 번호</th>
		<th width="300">제목</th>
		<th width="80">작성자</th>
		<th width="50">조회수</th>
			<bbsList:forEach items="${list}" var="board">
					<tr>
						<td align="center">${board.postid} </td>
						<td align="center"><a href="#">${board.title}</a></td>
						<td align="center">${board.writer} </td>
						<td align="center">${board.view} </td>
					</tr>
					
					
					
		
			</bbsList:forEach>
	</table>

















<hr>
<button><a href="write_bbs.jsp">글쓰기</a></button> <br>
<a href="index.jsp">홈으로</a>
</body>
</html>