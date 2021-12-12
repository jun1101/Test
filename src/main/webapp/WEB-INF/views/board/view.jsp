<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">

<label>제목</label>
<input type="text" name="title" value="${view.title}" /><br />

<label>작성자</label>
<input type="text" name="writer"value="${view.writer}" /><br />

<label>내용</label>
<textarea cols="50" rows="5" name="content">${view.content}</textarea><br />

<!--  <button type="submit">작성</button>
-->
</form>
</body>
</html>