<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<style>
.navbar_logo h2 a{
	color:#fff;
}
.nav_menu li,.nav_menu li a{
color:#fff;
}
.nav_menu li:hover{
background:#a9a9a9;
}

</style>
<meta charset="UTF-8">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">


</head>
<body>
	<header id ="header" class="fixed-top">
		<nav class = "navbar">
			<div class="navbar_logo">
				<h2 class=nav_main_logo><img src="resources/img/gom.png"  /><a href="${pageContext.request.contextPath}/main.do">Audio-cleaner</a></h2>
			
			</div>
			<ul class="nav_menu">
			<c:if test="${sessionId == null && member1== null }">
			<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
				
				<li><a href="${pageContext.request.contextPath}/join.do">Join</a></li>
				<li><a href="${pageContext.request.contextPath}/login.do">Login</a></li>
				<li><a href="${pageContext.request.contextPath}/board.do?num=1">게시물 목록</a></li>
				
			</c:if>
			<c:if test ="${sessionId != null}">
			<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/pay.do">이용권 구매</a></li>
				<li>
					<a href="${pageContext.request.contextPath}/Logout.do">Logout</a>
				</li>
				
				<li>${sessionId}님 환영합니다.</li>
			</c:if>
			<c:if test ="${member1 != null}">
			<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/pay.do">이용권 구매</a></li>
			<li><a href="${pageContext.request.contextPath}/board.do?num=1">게시물 목록</a></li>
				
				<li>
					<a href="${pageContext.request.contextPath}/Logout.do">Logout</a>
				</li>
				
				<span>${member1.mem_id}님 환영합니다.</span>
			</c:if>
			<c:if test="${userId != null}">
			<li>123123123123123</li>
			</c:if>
			</ul>
			
		</nav>
	
	
	</header>

	

</body>
</html>