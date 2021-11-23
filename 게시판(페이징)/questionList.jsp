<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="questionList" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String fId = (String) session.getAttribute("fId");
if (fId == null) {
	fId = "stest";

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역 확인하기</title>
<style>
</style>
<script type="text/javascript" src="resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	function NotSession() {
		alert('접근권한이 없습니다')
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h1>문의 내역</h1>
		<hr>
		<table class="type09">
			<thead>
				<tr>
					<th width="100">문의 번호</th>
					<th width="400">문의 제목</th>
					<th width="95">글쓴이</th>
					<th width="95">문의 날짜</th>
					<th width="95">조회</th>
				</tr>
			</thead>
			<questionList:forEach items="${questionList}" var="board">
				<!-- jstl forEach를 사용해서 views에 뿌려준다 -->
				<tbody>
					<tr>
						<th scope="row">${board.qno}</th>
						<!-- db처리한 sequence값 -->
						<questionList:choose>
							<questionList:when test="${board.fk_seq == 0}">
								<!-- fk_seq값이 0인 경우는 문의글이다. -->
								<td height="30px;" id="title"><a
									href="questionInfo?qno=${board.qno}">${board.qtitle}</a></td>
								<td>${board.qid}</td>
							</questionList:when>

							<questionList:otherwise>
								<!-- when으로 조건을 준게 false인 경우, fk_seq컬럼에 값이 있는경우 답변이다. -->
								<td style="padding-left: 20px;" height="30px;"><a
									href="questionInfo?qno=${board.qno}">ㄴ[답변]${board.qtitle}</a></td>
								<td>${board.qid}</td>
							</questionList:otherwise>
						</questionList:choose>
						<td style="text-align: left;">${board.qdate}</td>
						<td style="text-align: left;">${board.viewCount}</td>
					</tr>
				</tbody>
			</questionList:forEach>
		</table>
	</div>
	<div align="center">
		<!-- jstl if를 사용해서 조건문을 사용하자 if는 단일 조건일때 사용함 -->
		<!-- 컨트롤러에서 모델에 등록한 pageMaker를 view에 출력한다 -->
		<questionList:if test="${pageMaker.prev}">
			<!-- makeQuery 메서드는 동적으로 url을 만들어주는 메서드이다 -->
			<a href="questionList${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
		</questionList:if>

		<!-- 시작 페이지와 끝나는 페이지를 조건으로 forEach를 사용하여 반복문을 돌린다 -->
		<!-- idx는 forEach 안에서 사용될 변수이다 -->
		<questionList:forEach begin="${pageMaker.startPage}"
			end="${pageMaker.endPage}" var="idx">
			<!-- 
  		makeQuery 메서드는 동적으로 url을 스트링으로 만들어주는 메서드이다
  		page가 파라메터로 makeQuery에 들어가서 page가 바뀐다. 
  		perPageNum은 페이징 처리될 문의글 갯수이다
  		questionList?page=2&perPageNum=10 
  		페이징 처리 되서 생기는 숫자마다 url로 링크해서 이동 가능하게 만든다. -->
			<a href="questionList${pageMaker.makeQuery(idx)}">${idx}</a>
		</questionList:forEach>

		<questionList:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<!-- if 조건에 맞을 경우 끝 페이지보다 + 1 해서 다음 이라는 단어를 생성하고 makeQuery를 사용하여 링크한다 -->
			<a href="questionList${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
		</questionList:if>
	</div>
	<%
	if (fId.equals("admin")) {
	%>
	<div align="center">
		<jsp:include page="chart.jsp"></jsp:include>
	</div>
	<%
	}
	%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
