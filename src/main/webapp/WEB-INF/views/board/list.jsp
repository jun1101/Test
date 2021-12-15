<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>

<div class="row">
<div class="col-xs-12">
<table class= "table table-striped">
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
 	<td><fmt:formatDate value="${val.regDate}" pattern="yyyy-MM-dd"/></td>
 	<td>${val.writer}</td>
 	<td>${val.viewCnt}</td>
 	</tr>
 	</c:forEach>
  
 </tbody>

</table>
</div>
</div>

<div>
 <c:if test="${prev}">
 <span>[ <a href="${pageContext.request.contextPath}/board.do?num=${startPageNum - 1}">이전</a> ]</span>
</c:if>

<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
 <span>
 
  <c:if test="${select != num}">
   <a href="${pageContext.request.contextPath}/board.do?num=${num}">${num}</a>
  </c:if>    
  
  <c:if test="${select == num}">
   <b>${num}</b>
  </c:if>
    
 </span>
</c:forEach>

<c:if test="${next}">
 <span>[ <a href="${pageContext.request.contextPath}/board.do?num=${endPageNum + 1}">다음</a> ]</span>
</c:if>
</div>
<a href="${pageContext.request.contextPath}/boardWrite.do">게시판작성</a>

<div>
  <select name="searchType">
      <option value="title">제목</option>
         <option value="content">내용</option>
      <option value="title_content">제목+내용</option>
      <option value="writer">작성자</option>
  </select>
  
  <input type="text" name="keyword" />
  
  <button type="button">검색</button>
 </div>

</body>
</html>