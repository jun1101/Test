<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<title>Audio-cleaner</title>
</head>
<body>
<%@include file="../views/header.jsp" %>
<section id="ex9">
        <style>
            #ex9 .upload-box{
                width:500px;
                height: 300px;
                border:1px solid gray;
                box-shadow: 2px 3px 9px hsl(0, 0%, 47%);
                padding:10px;
            }
        </style>
        <h1>파일업로드 : DND & Trigger</h1>
        <div class="upload-box">
            <button class="btn-upload">파일선택</button>
            <input class="btn-file d-none" type="file"> <!--파일 input box 형태-->     
        </div>
    </section>
    <hr>
<script> var sec9 = document.querySelector('#ex9');
    var btnUpload = sec9.querySelector('.btn-upload');
    var inputFile = sec9.querySelector('input[type="file"]');
    var uploadBox = sec9.querySelector('.upload-box');

    /* 박스 안에 Drag 들어왔을 때 */
    uploadBox.addEventListener('dragenter', function(e) {
        console.log('dragenter');
    });
    
    /* 박스 안에 Drag를 하고 있을 때 */
    uploadBox.addEventListener('dragover', function(e) {
        e.preventDefault();
        console.log('dragover');

        this.style.backgroundColor = 'green';
    });
    
    /* 박스 밖으로 Drag가 나갈 때 */
    uploadBox.addEventListener('dragleave', function(e) {
        console.log('dragleave');

        this.style.backgroundColor = 'white';
    });
    
    /* 박스 안에서 Drag를 Drop했을 때 */
    uploadBox.addEventListener('drop', function(e) {
        e.preventDefault();

        console.log('drop');
        this.style.backgroundColor = 'white';
    });
    </script>
</body>
</html>
