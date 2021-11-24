<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
			
			
			<li><a href="${pageContext.request.contextPath}/login.do">Login</a></li>
			<li><a href="${pageContext.request.contextPath}/join.do">Join</a></li>
			<li><a href="${pageContext.request.contextPath}/main.do">Home</a></li>
			</ul>
			
		</nav>
	
	
	</header>

	

</body>
</html>