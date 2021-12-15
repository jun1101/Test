$(document).ready(function(){
	let fileList =[];
	const $fileDrop = $('div.fileDrop');
	const $uploadedList = $('div.uploadedList');
	
	$fileDrop.on("dragenter", function(e) {
		$(this).addClass('drag-over');
	}).on("dragleave", function(e){
		$(this).removeClass('drag-over')
	}).on("dragover", function(e){
		e.stopPropagation();
		e.preventDefault();
	}).on('drop', function(e){
		e.preventDefault();
		
		if(!$('#userrole').val()){
			alert('로그인을 해주세요');
			location.href="http://localhost:8080/Audio/login.do";
			return;
		}
		
		
		let files = e.originalEvent.dataTransfer.files;
		if(files != null && files != undefined){
			var tag = "";
			for(i=0; i<files.length; i++){
				var f = files[i];
				fileList.push(i);
				var fileName = f.name;
				
				let ext = fileName.split('.').pop().toLowerCase();
				if($.inArray(ext, ['avi', 'wmv', 'flac', 'mp3', 'mp4','wav'])== -1){
					alert("등록 불가한 파일 형식입니다.");
					location.reload();
				}
				
			var fileSize = f.size / 1024 / 1024;
			fileSize = fileSize < 1 ? fileSize.toFixed(3) : fileSize.toFixed(1);
			tag +=
				"<div class='fileList'>" +
					"<span class='fileName'>"+fileName+"</span>&emsp;"+
					 "<span class='fileSize'>"+fileSize+" MB</span>" +
	                 "<span class='clear'></span>" +
	           "</div>";
			}
			$(this).append(tag);
			}
			$(this).removeClass('drag-over');
			$("#ajax-file").prop("files", e.originalEvent.dataTransfer.files);
			
			let today = new Date();
			let sessionday = $('#mem_pay').val();
			let spday = sessionday.split(' ');
			let monthidx = 0;
			switch (spday[1]){
				case "Jan":
					monthidx = 0;
					break;
				case "Feb":
					monthidx = 1;
					break;
				case "Mar":
					monthidx = 2;
					break;
				case "Apr":
					monthidx = 3;
					break;
				case "May":
					monthidx = 4;
					break;
				case "Jun":
					monthidx = 5;
					break;
				case "Jul":
					monthidx = 6;
					break;
				case "Aug":
					monthidx = 7;
					break;
				case "Sep":
					monthidx = 8;
					break;
				case "Oct":
					monthidx = 9;
					break;
				case "Nov":
					monthidx = 10;
					break;
				case "Dec":
					monthidx = 11;
					break;
			}
			let mem_pay = new Date(spday[5], monthidx, spday[2]);
			
			let userrole =$('#userrole').val();
			let sendsize = fileList[0].size / 1024 / 1024;
			
				if(userrole == 'user'){
					if(fileList.length > 1){
						alert('파일을 한개씩만 올려주세요');
						location.reload();
					}
					else{
						if(sendsize > 100){
							if(!$('#mem_pay').val()){
								alert('이용권이 필요합니다. 이용권을 구매해주세요');
							}
							else if(today > mem_pay) {
								alert('이용권이 만료되었습니다. 이용권을 재구매 해주세요')
								location.reload();
							}
						}
					}
				}
		
	const $percent = $('#percent'); 
	const $status = $('#status');
	
	let upFiles = [];
	$('#form3').ajaxForm({
		beforeSend: function(xhr, opts) {
			let f = $('#ajax-file').val();
			console.log($('#ajax-file').val());
			if(!f){
				alert('파일이 없습니다');
				xhr.abort();
				return false;
				
			}
			
			$status.empty();
			$percent.html('0%');
		},
		uploadProgress: function(event, position, total, percentComplete){
			$status.html('uploading...')
			$percent.html(percentComplete + '%');
		},
		
		complete: function(xhr){
			console.log(xhr);
			let resJson = xhr.responseJSON;
			console.log(resJson);
			if(xhr.status !== 201){
				alert("업로드 에러 발생 ("+resJson[1]+")");
				console.log(resJson.length);
				location.reload();
				
			}
			console.log(resJson.length);
			$status.html(resJson.length + 'files Uploaded');
			alert("업로드 완료");
			location.reload();
		}
	});
});
});