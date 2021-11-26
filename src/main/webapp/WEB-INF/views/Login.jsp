<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
<%@include file="../views/header.jsp" %>

	<form role="form" class="Login" action="${pageContext.request.contextPath}/signin.do" method="post">
		<span>아이디</span>
		<input type="email" placeholder="Email Address" name="mem_id" id="mem_id">
		<span>비밀번호</span>
		<input type="password" placeholder="Password" name="mem_pass" id="mem_pass" >
		
		
		<button type="submit" id="Login_Btn" name="Login_btn">로그인</button>
		<c:if test="${msg == false}">
  		 <p style="color:#f00;">로그인에 실패했습니다.</p>
  		</c:if>
  		<ul>
  		
	<li onclick="kakaoLogin();">
      <a href="javascript:void(0)">
          <span><img src="resources/img/kakao_login_medium_narrow.png" alt="" /></span>
      </a>
	</li>
	
</ul>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('990f91e6a8cf837975d28943a81b7d7e'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }

</script>

		
	</form>

</body>
</html>