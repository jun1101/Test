<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js" type="text/javascript"></script>
<script src="resources/js/jQuery.form.js" type="text/javascript"></script>
<script src="resources/js/upload.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/style.css"/>
<link rel="stylesheet" href="resources/css/style2.css"/>
	<title>Audio-cleaner</title>
	<style type="text/css">
	
	.video-frame::before{
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
    
    
    
    }
    .upbtn {
	width:80px;
	height:auto;
	border: 0;
}
.upsubmit{
	border: 0;
	background-color: transparent;
	width:80px;
	height:auto;
	border: 0;
}
div.files {
  width: 100%;
  box-sizing: border-box;
  overflow: auto;
  display: flex;
  min-height: 95vh;
}
div.right {
  width: 100%;
  float: left;
  box-sizing: border-box;
  color: #fff;
}
.file_sentence{
	font-weight: 600;
}

.percent, .status{
	font-size: 20px;
    font-weight: 600;
}
div.fileDrop{
	height: 100%;
	text-align: center;
	margin-top: 300px;
}

	
	</style>
</head>
<body>


<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
	<%@include file="../views/header.jsp" %>
	
	<main id="main">
	<div class="files">
		<div class="right">
			<div class="fileDrop">
				<label class="file-label" for="ajax-file">
				<img src="resources/img/music3.png" style="width: auto; height: 105px;">
				</label><br>
				<h5 class="file_sentence">Click Image or Drag & Drop<p>your sound file here</h5>
				<br><br>
				<div class="uploadedList"></div>
				<div id="percent" class="percent">0 %</div>
				<div id="status" class="status">ready</div>
				
				<form action="${pageContext.request.contextPath}/uploadForm.do" id="form3" method="POST" enctype="multipart/form-data">
					<input type="file" name="files" id="ajax-file" style="display: none;" />
					<input type="hidden" name="uploaduser" id="uploaduser" value="${m_idx}">
					<input type="hidden" name="userrole" id="userrole" value="${userrole}"/>
					
					<button type="submit" name="submit" class="upsubmit"><img src="resources/img/upload.png" class="upbtn"></button>
				</form>
				<input type="hidden" name="mem_pay" id="mem_pay" value="${mem_pay}"/>
				
			</div>
		</div>
	</div>
	</main>

</div>

        
</body>
</html>
