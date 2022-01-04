<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/css/style2.css"/>
<link rel="stylesheet" href="resources/css/border.css"/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
	<%@include file="../header.jsp" %>
	
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
 <c:if test="${page.prev}">
 <span>[ <a href="${pageContext.request.contextPath}/board.do?num=${page.startPageNum - 1}">이전</a> ]</span>
</c:if>

<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
 <span>
 
  <c:if test="${select != num}">
   <a href="${pageContext.request.contextPath}/board.do?num=${num}">${num}</a>
  </c:if>    
  
  <c:if test="${select == num}">
   <b>${num}</b>
  </c:if>
    
 </span>
</c:forEach>

<c:if test="${page.next}">
 <span>[ <a href="${pageContext.request.contextPath}/board.do?num=${page.endPageNum + 1}">다음</a> ]</span>
</c:if>
</div>
<c:if test ="${member1 != null}">
<a style="color=#fff" href= "${pageContext.request.contextPath}/boardWrite.do">게시판작성</a>
</c:if>
</div>
<div>
  <select name="searchType">
      <option value="title">제목</option>
         <option value="content">내용</option>
      <option value="title_content">제목+내용</option>
      <option value="writer">작성자</option>
  </select>
  
  <input type="text" name="keyword" />
  
  <button type="button" id="searchBtn">검색</button>
 </div>
</body>

</html>