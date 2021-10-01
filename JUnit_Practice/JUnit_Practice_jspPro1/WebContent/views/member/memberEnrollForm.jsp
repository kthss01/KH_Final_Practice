<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>enroll form</title>
<style>
.outer {
	background: black;
	/*width:800px;
	height:500px;*/
	width: 60%;
	height: 400px;
	/*margin-top: 50px;*/
	margin-left: auto;
	margin-right: auto;
	color: white;
}

#enrollForm {
	/* border:1px solid white; */
	width: 100%;
	margin-left: auto;
	margin-right: auto;
}

#enrollForm td:nth-child(1) {
	text-align: right;
}

#enrollForm input {
	margin: 3px;
}

#joinBtn {
	background: yellowgreen;
}

#goMain {
	background: orangered;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<!-- <br> -->

		<h2 align="center">회원가입</h2>

		<form id="enrollForm"
			action="<%=request.getContextPath()%>/insert.me" method="post"
			onsubmit="return joinValidate();">
			<table>
				<tr>
					<td width="200px">* 아이디</td>
					<td><input type="text" maxlength="13" name="userId" required></td>
					<td width="200px">
						<button type="button" id="idCheckBtn" onclick="checkId();">중복확인</button>
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" maxlength="15" name="userPwd"
						required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" maxlength="15" name="checkPwd"
						required></td>
					<td><label id="pwdResult"></label></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" maxlength="5" name="userName" required></td>
					<td></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="tel" maxlength="11" name="phone"
						placeholder="(-없이)01012345678"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td><input type="checkbox" id="sports" name="interest"
						value="운동"> <label for="sports">운동</label> <input
						type="checkbox" id="climbing" name="interest" value="등산">
						<label for="climbing">등산</label> <input type="checkbox"
						id="fishing" name="interest" value="낚시"> <label
						for="fishing">낚시</label> <input type="checkbox" id="cooking"
						name="interest" value="요리"> <label for="cooking">요리</label>

						<input type="checkbox" id="game" name="interest" value="게임">
						<label for="game">게임</label> <input type="checkbox" id="etc"
						name="interest" value="기타"> <label for="etc">기타</label></td>
					<td></td>
				</tr>
			</table>
			<br>
  
			<div class="btns" align="center">
				<!-- 여기서 history.go(-1) 하면 다시 회원가입할 때 문제 생김 
					history.back()하면 이전 상태로 돌아가는거라 다시 회원가입이 성공한 직후 상태가 됨
					그래서 문제가 발생할 수 있음 바로 직전 상태가 특정 결과가 나온 상태가 아닐 때만 사용해주어야함
				-->
				<button type="button" id="goMain"
					onclick="location.href =<%=request.getContextPath()%>">메인으로</button>

				<button type="submit" id="joinBtn" disabled>가입하기</button>

			</div>
		</form>

	</div>

	<script>
	
	function joinValidate(){
		
		if(!(/^[a-z][a-z\d]{3,11}$/i.test($("#enrollForm input[name=userId]").val()))){
			$("#enrollForm input[name=userId]").focus();
	        return false;
		}
		
		if($("#enrollForm input[name=userPwd]").val() != $("#enrollForm input[name=checkPwd]").val()){
			$("#pwdResult").text("비밀번호 불일치").css("color", "red");
			return false;			
		}
		
		 if(!(/^[가-힣]{2,}$/.test($("#enrollForm input[name=userName]").val()))){
			 $("#enrollForm input[name=userName]").focus();
	        return false;
		 }
		 
		 return true;
		
		
	}
	
	function checkId() {
		const userId = $('#enrollForm input[name=userId]');
		if (userId.val() == "") {
			alert("아이디를 입력해주세요");
			return false;
		}
		
		$.ajax({
			url : "idCheck.me",
			type : "post",
			data : {
				userId : userId.val()
			},
			success : function(result) {
				if (result == "fail") {
					alert("사용할 수 없는 아이디입니다.");
					userId.focus();
				} else {
					if (confirm("사용 가능한 아이디입니다. 사용 하시겠습니까?")) {
						userId.attr("readonly", "true");
						$("#joinBtn").removeAttr("disabled");
					} else {
						userId.focus();
					}
				}
			},
			error : function() {
				console.log("서버 통신 실패");
			}
		});
	}
	
	</script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>