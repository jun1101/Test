<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp" %>
<form method="post">

<label>제목</label>
${view.title}<br />

<label>작성자</label>
${view.writer}<br />

<label>내용</label>
${view.content}

<div>
<a href="${pageContext.request.contextPath}/modify.do?bno=${view.bno}">게시물 수정</a>,<a href="${pageContext.request.contextPath}/board_delete.do?bno=${view.bno}">게시물 삭제</a>
</div>
<div>
<a href="${pageContext.request.contextPath}/board.do?num=1"> 목록</a>
</div>

<!--  <button type="submit">작성</button>
-->
</form>
</body>
</html>