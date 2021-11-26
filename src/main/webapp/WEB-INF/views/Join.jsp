<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="../views/header.jsp" %>

	<form class="Join" action="${pageContext.request.contextPath}/signup.do" method="post">
		<span>아이디</span>
		<input type="email" placeholder="Email Address" name="mem_id" id="mem_id">
		<span>비밀번호</span>
		<input type="password" placeholder="Password" name="mem_pass" id ="mem_pass" >
		<span>비밀번호 확인</span>
		<input type="password" placeholder="Password" >
		<span>이름</span>
		<input type="text" placeholder="이름을 입력하세요" name="mem_name" id= "mem_name" >
		<span> 전화번호</span>
		<input type="text" placeholder="전화번호를 입력하세요" name="mem_phone" id= "mem_phone" >
		<button type="submit" id="Join_Btn" name="Join_btn">회원가입</button>
		

		
	</form>

</body>
</html>