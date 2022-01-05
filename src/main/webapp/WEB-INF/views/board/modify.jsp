<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>

<style>	.video-frame::before{
    content: '';
    background: linear-gradient(-135deg,black,transparent);
    position: absolute;
    width: 100%;
    height: 100%;
    top:0;
    left:0;
    z-index:-100;
    }
    .video-frame video{
    position:fixed;
    min-width: 100%;
    min-height:100%;
    z-index:-1;
    }
    .percent, .status, .file_sentence{
    color:#fff;
    
    </style>
</head>
<body>
<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
	<%@include file="../header.jsp" %>
	
<form name="update" method="POST" action="${pageContext.request.contextPath}/board_modify.do?no=${view.bno}" onsubmit="return check();">
<label>제목</label>
<input type="text" name="title" id="title" value="${view.title}" /><br />
<input type="hidden" name="bno" value="${view.bno}">

<label>작성자</label>
${member1.mem_name}<br />

<label>내용</label>
<textarea cols="50" rows="5" id="content" name=content>${view.content}</textarea><br />
<div id="cnt">(0 / 200)</div>

<button type="submit">완료</button>
</form>
</div>
</body>
<script>
$(document).ready(function() {
    $('#content').on('keyup', function() {
        $('#cnt').html("("+$(this).val().length+" / 200)");
 
        if($(this).val().length > 200) {
        	 if($(this).val().length > 200) {
             	$("#cnt").css("color","red");
                
                
             }
        }
    });
});


      





 function check(){
	 if(!document.update.title.value){
		 alert("제목을 입력해주세요");
		 return false;
	 }
	 if(!document.update.writer.value){
		 alert("작성자를 입력해주세요");
	 	return false;
	 }
	 if(!document.update.content.value){
		 alert("내용을 입력해주세요");
		 return false;
	 }
	 if($("#writer").val().length > 8) {
     	alert("너무 긴 이름은 사용할수 없습니다");
     	return false;    
 }
	 if($("content").val().length > 200) {
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