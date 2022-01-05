<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="resources/css/style2.css"/>
<script src="https://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>
<style>

</style>
</head>
<body>
<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
	<%@include file="../views/header.jsp" %>
	
	
	<h2 class="Join_name">JOIN</h2>
	<form name ="joinform"class="Join" action="${pageContext.request.contextPath}/signup.do" method="post" onsubmit="return check();">
		<div>
		<span>아이디</span>
		<input type="text" placeholder="Email Address" name="mem_id" id="mem_id">
		
		<button class="idChk" type="button" id= "idChk" onclick="fn_idChk();" value="N">중복확인</button>
		</div>
		<span>비밀번호</span>
		<input type="password" placeholder="Password  특수문자와 영문과 숫자를 포함한 8~20자리만 가능" name="mem_pass" id ="mem_pass" >
		<span>비밀번호 확인</span>
		<input type="password" placeholder="Password  특수문자와 영문과 숫자를 포함한 8~20자리만 가능" name="repw" id="mem_pass_check" >
		<span>이름</span>
		<input type="text" placeholder="이름을 입력하세요 " name="mem_name" id= "mem_name" >
		<span> 전화번호</span>
		<input type="text" placeholder="전화번호를 입력하세요 -를 뺀 숫자만" name="mem_phone" id= "mem_phone" >
		<button type="submit" id="Join_Btn" name="Join_btn" >회원가입</button>
		

		
	</form>
</div>
</body>
<script>
function fn_idChk(){
	var objId = document.getElementById("mem_id");//메일
	var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	
	
	if(!document.joinform.mem_id.value){
		 alert("아이디를 입력해주세요");
		 return false;
	 }
	if(!e_RegExp.test(objId.value)){ //이메일 유효성 검사
        alert("이메일 형식이 아닙니다. 이메일 형식으로 입력해주세요.");
        return false;
    }
	if($("#mem_id").val().length > 30) {
     	alert("너무 긴 아이디는 사용할수 없습니다.");
     	return false;    
 }
	
	$.ajax({
		url : "${pageContext.request.contextPath}/idChk",
		type : "post",
		dataType: "json",
		data : {"mem_id" : $("#mem_id").val()},
		success : function(data){
			if(data == 1){
				alert("사용할수 없는 아이디입니다");
			}else if (data == 0){
				$("#idChk").attr("value", "Y");
				alert("사용가능한 아이디 입니다.");
			}
			}
		
	
	});
	}



 function check(){
	 var pw = $("#mem_pass").val();
	 var Name = document.getElementById("mem_name"); //이름
	 var n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식
	 var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	 var objId = document.getElementById("mem_id");//메일
	 var objName = document.getElementById("mem_name"); //이름
	 var objPhone = document.getElementById("mem_phone")
	 var regexp = /^[0-9]*$/
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 
	 
	 if(!document.joinform.mem_id.value){
		 alert("아이디를 입력해주세요");
		 return false;
	 }
	 if(!document.joinform.mem_pass.value){
		 alert("비밀번호를 입력해주세요");
	 	return false;
	 }
	 if(!document.joinform.mem_pass_check.value){
		 alert("비밀번호 확인을 입력해주세요");
	 	return false;
	 }
	 if(document.joinform.mem_pass.value != document.joinform.repw.value){
		 alert("비밀번호가 일치하지 않습니다");
		 return false
	 }
	 
	 if(!document.joinform.mem_phone.value){
		 alert("전화번호를 입력해주세요");
	 	return false;
	 }
	 if(!document.joinform.mem_name.value){
		 alert("이름을 입력해주세요");
	 	return false;
	 }
	 if(!e_RegExp.test(objId.value)){ //이메일 유효성 검사
         alert("이메일 형식이 아닙니다. 이메일 형식으로 입력해주세요.");
         return false;
     }
	 if($("#mem_id").val().length > 30) {
	     	alert("너무 긴 아이디는 사용할수 없습니다.");
	     	return false;    
	 }
	 if($("#mem_name").val().length > 5) {
	     	alert("너무 긴 이름은 사용할수 없습니다.");
	     	return false;    
	 }
	 if($("#mem_phone").val().length > 14) {
	     	alert("너무 긴 전화번호는 사용할수 없습니다.");
	     	return false;    
	 }

	 if(pw.length < 8 || pw.length > 20){

	  alert(" 비밀번호를 8자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
	  alert("비밀번호는 공백 없이 입력해주세요.");
	  return false;
	 }else if(num < 0 || eng < 0 || spe < 0 ){
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	  return false;
	 } 
	    
	 
	 if(!n_RegExp.test(objName.value)){
         alert("이름에 특수문자,영어,숫자는 사용할수 없습니다. 한글만 입력하여주세요.");
         return false;
     }
	 if(!regexp.test(objPhone.value)){
         alert("전화번호에 숫자만 사용 가능합니다 숫자만 입력하여주세요.");
         return false;
         
         
         
         
     }
	 
	 var idChkVal = $("#idChk").val();
		if(idChkVal == "N"){
			alert("중복확인을 확인 해주세요");
			return false;
		}
		
		
		$.ajax({
			url : "${pageContext.request.contextPath}/idChk",
			type : "post",
			dataType: "json",
			data : {"mem_id" : $("#mem_id").val()},
			success : function(data){
				if(data == 1){
					alert("사용할수 없는 아이디입니다");
					return false;
				}else if (data == 0){
				
					alert("회원가입이 완료되었습니다.")
					return true;
				}
				}
			
		
		});
		
	 
	 
 }
 
</script>
</html>