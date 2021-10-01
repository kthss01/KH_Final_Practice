<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.ArrayList, com.kh.board.model.vo.*" pageEncoding="UTF-8"%>
    
<% 
	Board b = (Board)request.getAttribute("b"); 
	ArrayList<Attachment> fileList = (ArrayList<Attachment>)request.getAttribute("fileList");

	Attachment titleImg = fileList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		/*width:1000px;
		height:800px;*/
		width: 80%;
		height: 400px;
		background:black;
		color:white;
		margin:auto;
		/*margin-top:50px;*/
	}
	#contentArea{
		height:20px;
	}
	
	.detail td{
		text-align:center;
		border:1px solid white;
	}
	.detailImgArea{
		width:110px;
		height:110px;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<!-- <br> -->
		<h2 align="center">사진게시판 상세보기</h2>
		<!-- <br> -->
		
		<table class="detail" align="center">
			<tr>
				<td width="70px">제목</td>
				<td colspan="5"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%= b.getBoardWriter() %></td>
				<td>조회수</td>
				<td><%= b.getCount() %></td>
				<td>작성일</td>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="6">
					<p id="contentArea"><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="4">
					<div id="titleImgArea" align="center">
						<img width="150px" height="100px" id="titleImg" src="<%= contextPath %>/resources/board_upfiles/<%= titleImg.getChangeName() %>">
					</div>
				</td>
				<td>
					<a download="<%=titleImg.getOriginName() %>"  href="<%= contextPath %>/resources/board_upfiles/<%=titleImg.getChangeName()%>">다운로드</a>
							</td>
			</tr>			
		</table>
		
		<table class="detail" align="center">
			<tr>
				<% for(int i=1; i<fileList.size(); i++){ %>
				<td>
					<div class="detailImgArea" align="center">
						<img width="100px" height="80px" src="<%=contextPath%>/resources/board_upfiles/<%=fileList.get(i).getChangeName()%>"><br>
						<a download="<%=fileList.get(i).getOriginName() %>" href="<%= contextPath %>/resources/board_upfiles/<%=fileList.get(i).getChangeName()%>">다운로드</a>
						
					</div>
				</td>
				<% } %>
			</tr>
		</table>
		
	</div>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>