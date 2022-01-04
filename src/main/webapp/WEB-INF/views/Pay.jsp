<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="resources/css/style2.css"/>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class ="container">
	<div class="video-frame">
	<video src="resources/video/BackGround.mp4" autoplay muted loop></video>
	</div>
<%@include file="../views/header.jsp" %>
<section>
<h1>이용권 구매</h1>
	<div class="row">
		<div class="col">
			<div class="card">
				<div class="title">
				<i class="fa fa-paper-plane" aria-hidden="true"></i>
				<h2>1개월 이용권</h2>
				</div>
				<div class="price">
					<h4><sup>&#8361;</sup>3,000</h4>
				</div>
				<div class="option">
					<ul>
					<li> <i class="fa fa-check" aria-hidden="true"></i>무제한 업로드</li>
					<li> <i class="fa fa-check" aria-hidden="true"></i>용량 제한x</li>
					</ul>
				</div>
				<a href="#" onclick="iamport(1);">구매하기</a>
				
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="title">
				<i class="fa fa-paper-plane" aria-hidden="true"></i>
				<h2>6개월 이용권</h2>
				</div>
				<div class="price">
					<h4><sup>&#8361;</sup>16,000</h4>
				</div>
				<div class="option">
					<ul>
					<li> <i class="fa fa-check" aria-hidden="true"></i>무제한 업로드</li>
					<li> <i class="fa fa-check" aria-hidden="true"></i>용량 제한x</li>
					</ul>
				</div>
				<a href="#" onclick="iamport(6);">구매하기</a>
				
			</div>
		</div>
		<div class="col">
			<div class="card">
				<div class="title">
				<i class="fa fa-paper-plane" aria-hidden="true"></i>
				<h2>1년 이용권</h2>
				</div>
				<div class="price">
					<h4><sup>&#8361;</sup>16,000</h4>
				</div>
				<div class="option">
					<ul>
					<li> <i class="fa fa-check" aria-hidden="true"></i>무제한 업로드</li>
					<li> <i class="fa fa-check" aria-hidden="true"></i>용량 제한x</li>
					</ul>
				</div>
				<a href="#" onclick="iamport(12);">구매하기</a>
				
			</div>
		</div>
	</div>
</section>
</div>
<script>
function iamport(month){
		
		let member_email = $("#u_email").val();
		let product_name = '';
		let p_price = 0;
		if(month == 1){
			p_price = 100;
			product_name = '1개월 이용권';
		}else if(month == 6){
			p_price = 16000;
			product_name = '6개월 이용권';
		}else if(month == 12){
			p_price = 30000;
			product_name = '1년 이용권';
		}
		
		let user_idx = $("#u_idx").val();
		
		//가맹점 식별코드
		IMP.init('imp77383698');
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드

		IMP.request_pay({
		    pg : 'html5_inicis',// version 1.1.0부터 지원.
				/*
				'kakao':카카오페이,
				html5_inicis':이니시스(웹표준결제)
				'nice':나이스페이
				'jtnet':제이티넷
				'uplus':LG유플러스
				'danal':다날
				'payco':페이코
				'syrup':시럽페이
				'paypal':페이팔
				*/
		    pay_method : 'card',
		    /*
			'samsung':삼성페이,
			'card':신용카드,
			'trans':실시간계좌이체,
			'vbank':가상계좌,
			'phone':휴대폰소액결제
			*/
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    /*
			merchant_uid에 경우
			https://docs.iamport.kr/implementation/payment
			위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
			참고하세요.
			나중에 포스팅 해볼게요.
			*/
			 name : product_name , //결제창에서 보여질 이름
			 amount : p_price, //실제 결제되는 가격
			 buyer_email : member_email,
			 buyer_name : user_idx, //유저 인덱스로 넣음
			 buyer_tel : '010-1234-5678',
		    //buyer_addr : '선택',
		    //buyer_postcode : '선택',
		    m_redirect_url: 'http://localhost:8080/orderCompleteMobile.do'
			/*
			모바일 결제시,
			결제가 끝나고 랜딩되는 URL을 지정
			(카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
			*/
		}, function(rsp) {
			// 결제검증
			$.ajax({
	        	type : "POST",
	        	url : "${pageContext.request.contextPath}/verifyIamport.do/" + rsp.imp_uid 
	        }).done(function(data) {
	        	// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
	      
	        	if(rsp.paid_amount == data.response.amount){
		        	alert("결제 및 결제검증완료");
		        	$.ajax({
							type: "POST",
							url: "${pageContext.request.contextPath}/paysuccess.do",
							dataType: "JSON",
							data : {
								productname : data.response.name,
								p_price : data.response.amount,
								purchaseuser : data.response.buyerName,
								name : data.response.name
							}
							

							
						});
	        	} else {
	        		alert("결제 실패");
	        	}
	        });
		});
	}
	</script>
</body>
</html>