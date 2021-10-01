<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		/*width:1000px;
		height:650px;*/
		width: 80%;
		height: 400px;
		background:black;
		color:white;
		margin:auto;
		/*margin-top:50px;*/
	}
	
	#insertForm>table{
		border:1px solid white;
	}
	#insertForm input, #insertForm textarea{
		width:100%;
		box-sizing:border-box;
	}
	#insertForm img{
		border:1px dashed darkgray;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<!-- <br> -->
		<h2 align="center">사진 게시판 작성하기</h2>
		<!-- <br> -->
		
		
		<form id="insertForm" action="insert.th" method="post" enctype="multipart/form-data">
			<input type="hidden" name="writer" value="<%= loginUser.getUserNo() %>">
			<table align="center">
				<tr>
					<th width="100">제목</th>
					<td colspan="3"><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" rows="3" style="resize:none;"></textarea>
				</tr>
				<tr>
					<th>대표이미지</th>
					<td colspan="3">
						<img id="titleImg" width="150" height="100">
					</td>
				</tr>
				<tr>
					<th>내용이미지</th>
					<td>
						<img id="contentImg1" width="150" height="100">
					</td>
					<td>
						<img id="contentImg2" width="150" height="100">
					</td>
					<td>
						<img id="contentImg3" width="150" height="100">
					</td>
				</tr>
			</table>
			
			<div id="fileArea">
				<input type="file" name="file1" id="file1" onchange="loadImg(this, 1);">
				<input type="file" name="file2" id="file2" onchange="loadImg(this, 2);">
				<input type="file" name="file3" id="file3" onchange="loadImg(this, 3);">
				<input type="file" name="file4" id="file4" onchange="loadImg(this, 4);">
			</div>
			
			<!-- <br> -->
			
			<div class="btns" align="center">
		
				<button type="submit">작성하기</button>
			</div>
		</form>
		
	</div>
	
	<%@ include file="../common/footer.jsp" %>
	
	<script>
	$(function(){
		$("#fileArea").hide();
		
		$("#titleImg").click(function(){
			$("#file1").click();
		});
		
		$("#contentImg1").click(function(){
			$("#file2").click();
		});
		
		$("#contentImg2").click(function(){
			$("#file3").click();
		});
		
		$("#contentImg3").click(function(){
			$("#file4").click();
		});
		
	});
	
	function loadImg(inputFile, num){// 이미지 미리보기 
		//inputFile : 현재 변화가 생긴 input type = "file"
		//num : 조건문을 활용 하기 위해 전달받은 매개변수
		
		console.dir(inputFile);
		
		if(inputFile.files.length == 1){//file이 존재 할경우 
			var reader = new FileReader();// 파일을 읽어들이 FileReader객체를 생성 
			
			reader.readAsDataURL(inputFile.files[0]);//파일을 읽어주는 메소드  --> 해당 파일을 읽어서 url을 부여 (문자열로 저장 )
			
			
			reader.onload = function(e){//파일 읽기가 다완료 되면 실행할 메소드 
				console.log(e);
				switch(num){
				case 1 : $("#titleImg").attr("src", e.target.result); break;// result :  읽어들이 파일 내용 data:URL 형식 
				case 2 : $("#contentImg1").attr("src", e.target.result); break;
				case 3 : $("#contentImg2").attr("src", e.target.result); break;
				case 4 : $("#contentImg3").attr("src", e.target.result); break;
				}
			};
			
		}
	}
	
	
	</script>
	
</body>
</html>