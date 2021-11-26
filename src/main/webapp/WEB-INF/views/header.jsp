<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">


</head>
<body>
	<header id ="header" class="fixed-top">
		<nav class = "navbar">
			<div class="navbar_logo">
				<h1 class=nav_main_logo><img src="resources/img/gom.png"  /><a href="${pageContext.request.contextPath}/main.do">Audio-cleaner</a></h1>
			
			</div>
			<ul class="nav_menu">
			<c:if test="${member1 == null}">
				<li><a href="${pageContext.request.contextPath}/login.do">Login</a></li>
				<li><a href="${pageContext.request.contextPath}/join.do">Join</a></li>
				<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
				<li>${member1.mem_id}님 환영합니다.</li>
			</c:if>
			<c:if test ="${member1 != null}">
				<li>${member1.mem_name}님 환영합니다.</li>
				<li>
					<a href="${pageContext.request.contextPath}/Logout.do">Logout</a>
				</li>
				<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
			</c:if>
			</ul>
			
		</nav>
	
	
	</header>

	

</body>
</html>