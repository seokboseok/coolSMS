<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
	<body>
		<input type ="tel" id ="phone-number" placeholder="전화"> 
		<button type ="button" id ="auth-req-button">인증요청</button>
		
		<input type ="text" id ="auth-number" placeholder="인증"> 
		<button type ="button" id ="auth-res-button">확인</button>
		
		<script type="text/javascript">
			const authReqButton= document.querySelector('#auth-req-button');
			const authResButton= document.querySelector('#auth-res-button');
			
			var authCode="";
			
			authReqButton.onclick=()=>{
				const phoneNumberObj= document.querySelector('#phone-number');
				let phoneNumber = phoneNumberObj.value;
				alert("zmfflr");
				
				$.ajax({
					type:"get",
					url:"/check/sendSMS",
					data:{
					"phoneNumber" : phoneNumber
					},
					dataType:"text",
					success:function(data){
						authCode= data;
					},error:function(data){
						alert ('실패');
					}
				})
			}
			authResButton.onclick=()=>{
				const authnumberobj=document.querySelector('#auth-number');
				let authNumber=authnumberobj.value;
				if(authNumber==authCode){
					alert("인증성공");
				}else{
					alert("인증실패");
				}
			}
			
			</script>
	</body>
</html>