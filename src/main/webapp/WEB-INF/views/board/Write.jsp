<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/style2.css"/>
</head>
<body>
<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
	<%@include file="../header.jsp" %>
	
<form name ="writerform"method="get" action="${pageContext.request.contextPath}/postWrite.do" onsubmit="return check();">

<label>제목</label>
<input type="text" name="title" id="title" /><br />

<label>작성자</label>
<input type="text" name="writer" id="writer" /><br />

<label>내용</label>
<textarea cols="50" rows="5" name="content" id="content"></textarea><br />
<div id="cnt">(0 / 200)</div>

<button type="submit">작성</button>
</form>
</div>
</body>
<script>
$(document).ready(function() {
    $('#content').on('keyup', function() {
        $('#cnt').html("("+$(this).val().length+" / 200)");
 
        if($(this).val().length > 200) {
        	$("#cnt").css("color","red");
           
           
        }
        else if($(this).val().length <= 200){
        	$("#cnt").css("color","white");
        }
    });
});


function check(){
	 if(!document.writerform.title.value){
		 alert("제목을 입력해주세요");
		 return false;
	 }
	 if(!document.writerform.writer.value){
		 alert("작성자를 입력해주세요");
	 	return false;
	 }
	 if(!document.writerform.content.value){
		 alert("내용을 입력해주세요");
		 return false;
	 }
	 if($("#writer").val().length > 8) {
    	alert("너무 긴 이름은 사용할수 없습니다");
    	return false;    
}
	 if($("#content").val().length > 200) {
	     	alert("내용은 200자까지만 작성이 가능합니다");
	     	return false;    
	 }
	 if($("#title").val().length > 20) {
	     	alert("너무 긴 제목은 사용할수 없습니다");
	     	return false;    
	 }
	 
}
 
</script>
</html>