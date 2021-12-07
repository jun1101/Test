<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta name="google-signin-client_id" content="830802709474-ucqn322s2tf8fbuda6sl4jat3on7vskn.apps.googleusercontent.com">
<meta charset="UTF-8">
<title>Login</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>

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
  			
	</form>
	

      <a href="javascript:kakaoLogin()">
          <span><img src="resources/img/kakao_login_medium_narrow.png"/></span>
      </a>
     
      
  
     <a href="${url}"> <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1" height="50"/></a>
     <div class="google_login"><img src="resources/img/btn_google.png" onmouseover="this.src='resources/img/btn_google_hover.png'"
			onmouseout="this.src='resources/img/btn_google.png'" style="width: 263px; height: 62px;" id="google_login" onclick="init();"></div>
   
	  <form name="kakaoForm" id="kakaoForm" method = "post" action="${pageContext.request.contextPath}/setSnsInfo.do">
			<input type="hidden" name="mem_id" id="kakaoEmail" />
			<input type="hidden" name="mem_kakao" id="kakaoId" />
			<input type="hidden" name="flag" id="flag" value="kakao" />
</form>
<form name="googleForm" id="googleForm" method="post" action="${pageContext.request.contextPath}/joinAfter.do">
		<input type="hidden" name="email" id="googleEmail" /> 
		<input type="hidden" name="googlelogin" id="googlelogin" />
	</form>
	
<script>
	$(document).ready(function() {
		$.ajax({
			type:'POST',
			url: '${pageContext.request.contextPath}/api/getKakaoApi.do',
			data :{},
			dataType : 'text',
			success : function(data){
				Kakao.init(data);
			},
			error: function(xhr, status, error){
				alert("API 등록 실패" +error);
			}
		});
	});
	function kakaoLogin() {
		Kakao.Auth.login({
			success: function (response) {
			Kakao.API.request({
				url: '/v2/user/me',
				success: function (response) {
					kakaoLoginPro(response);
				},
				fail: function (error) {
					console.log(error);
				},
			})
		},
			fail: function (error) {
				console.log(error);
			},
		})
	}
	function kakaoLoginPro(response){
		console.log(response);
		var data = {id:response.id,email:response.kakao_account.email}
		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/kakaoLoginPro.do',
			data : data,
			dataType : 'json',
			success : function(res){
				console.log("res : "+res.JavaData);
				if(res.JavaData == "YES"){
					alert("로그인되었습니다.");
					location.href = '${pageContext.request.contextPath}/main.do';
				}else if(res.JavaData == "register"){
					console.log("reg 탐");
					$("#kakaoEmail").val(response.kakao_account.email);
					$("#kakaoId").val(response.id);
					console.log($("#kakaoEmail").val());
					console.log($("#kakaoId").val());
					$("#kakaoForm").submit();
				}else{
					alert("로그인에 실패했습니다");
				}
				
			},
			error: function(xhr, status, error){
				alert("로그인에 실패했습니다."+error);
			}
		});
	}
	
	function init() {
		gapi.load('auth2', function() {
			
		});
	}
	
	
	function onSignIn(){
		var googleUser = gapi.auth2.getAuthInstance().currentUser.get();
		 var profile = googleUser.getBasicProfile();
		  var id_token = googleUser.getAuthResponse().id_token;
		  var name= profile.getName();
		  var email = profile.getEmail();
		  
		  $('#google_id_token').val(name);
		  $('#google_email').val(email);
		  $('#googleForm').submit();
	}
	
	
	function signOut() {
		if(gapi.auth2 != undefined){
		 	var auth2 = gapi.auth2.getAuthInstance();
	   		 auth2.signOut().then(function () {
	    	  console.log('User signed out.');
	    });
		}

		
		location.href= "${pageContext.request.contextPath}/Logout.do"
		
	  }
	
	
	var googleUser = {};
	function init() {
		 gapi.load('auth2', function() {
		  auth2 = gapi.auth2.init({
		        client_id: '830802709474-ucqn322s2tf8fbuda6sl4jat3on7vskn.apps.googleusercontent.com',
		        cookiepolicy: 'single_host_origin'
		      });
		      attachSignin(document.getElementById('google_login'));
		 });
	}

	function attachSignin(element) {
	    auth2.attachClickHandler(element, {},
	        function(googleUser) {
	    	var profile = googleUser.getBasicProfile();
	    	var id_token = googleUser.getAuthResponse().id_token;
				$(function() {
					$.ajax({
					    url: '${pageContext.request.contextPath}/googleLoginPro.do',
					    type: 'post',
					    data: {
							"idtoken": id_token
						    },
					    success: function (res) {
					            if(res.JoinChk == "False"){
									alert("구글 api 오류가 발생했습니다. 다시 시도해주세요.");
								}
								else if(res.JoinChk == "YES"){
									alert("구글 로그인 성공");
									location.href='${pageContext.request.contextPath}/main.do';
								}
								else{
									alert("구글 로그인에 실패했습니다. 잠시후 다시 시도해주세요.");
								}
					        }
					});
				})
	        });
	  }
		
	function googleLogout(){
		var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	      auth2.disconnect();
	      
	    });
	    
		
	}
</script>


		
	

</body>
</html>