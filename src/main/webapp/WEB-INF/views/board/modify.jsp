<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>

<form name="update" method="POST" action="${pageContext.request.contextPath}/board_modify.do?no=${view.bno}">
<label>제목</label>
<input type="text" name="title" value="${view.title}" /><br />
<input type="hidden" name="bno" value="${view.bno}">

<label>작성자</label>
<input type="text" name="writer" value="${view.writer}"><br />

<label>내용</label>
<textarea cols="50" rows="5" name=content>${view.content}</textarea><br />

<button type="submit">완료</button>
</form>

</body>
</html>