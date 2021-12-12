<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

<table>
 <thead>
  <tr>
   <th>번호</th>
   <th>제목</th>
   <th>작성일</th>
   <th>작성자</th>
   <th>조회수</th>
  </tr>
 </thead>
 
 <tbody>
 <c:forEach items="${list}" var="val">
 <tr>
 	<td>${val.bno}</td>
 	<td>
 	<a href="${pageContext.request.contextPath}/view.do?bno=${val.bno}">${val.title}</a>
 	</td>
 	<td>${val.regDate}</td>
 	<td>${val.writer}</td>
 	<td>${val.viewCnt}</td>
 	</tr>
 	</c:forEach>
  
 </tbody>

</table>
<a href="${pageContext.request.contextPath}/boardWrite.do">게시판작성</a>

</body>
</html>