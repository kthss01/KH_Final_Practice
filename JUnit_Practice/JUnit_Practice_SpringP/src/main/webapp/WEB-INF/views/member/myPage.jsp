<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
     <jsp:include page="../common/menubar.jsp"/>

     <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <form action="update.me" method="post" onsubmit="">
                <div class="form-group">
                    <label>* ID :</label>
                    <input type="text" class="form-control" name="userId" value="${ loginUser.userId }" readonly><br>
                    
                    <label for="userName">* Name :</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${ loginUser.userName }" readonly><br>
                    
                    <label for="email"> &nbsp; Email :</label>
                    <input type="email" class="form-control" id="email" name="email" value="${ loginUser.email }"><br>
                    
                    <label for="age"> &nbsp; Age :</label>
                    <input type="number" class="form-control" id="age" name="age" value="${ loginUser.age }"><br>
                    
                    <label for="phone"> &nbsp; Phone :</label>
                    <input type="tel" class="form-control" id="phone" name="phone" value="${ loginUser.phone }"><br>
                    
                    <label for="address"> &nbsp; Address :</label><br>
                  <%-- <input type="text" class="form-control" id="address" name="address" value="${ loginUser.address }"><br>--%>
                    
              	
				<c:forTokens var="addr" items="${ loginUser.address }" delims="/" varStatus="status">
					<c:if test="${ status.index eq 0 && addr >= '0' && addr <= '99999' }">
						<c:set var="post" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 0 && !(addr >= '0' && addr <= '99999') }">
						<c:set var="address1" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 1 }">
						<c:set var="address1" value="${ addr }"/>
					</c:if>
					<c:if test="${ status.index eq 2 }">
						<c:set var="address2" value="${ addr }"/>
					</c:if>
				</c:forTokens>
					<div class="form-inline">
					<label> &nbsp; 우편번호 : &nbsp;</label>
					<input type="text"  name="post" class="form-control mr-2 postcodify_postcode5" value="${ post }" size="6">
					<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
					</div>
					<br>
					<label> &nbsp; 도로명주소 : </label>
					<input type="text" name="address1" class="form-control postcodify_address" value="${ address1 }" size="30">
					<br>
				    <label> &nbsp; 상세주소 : </label>
					<input type="text" name="address2" class="form-control postcodify_extra_info" value="${ address2 }" size="30">
				
				
				<!-- jQuery와 Postcodify를 로딩한다. -->
				<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				<script>
					// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
					$(function(){
						$("#postcodify_search_button").postcodifyPopUp();
					});
				</script>
				<br><br>
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>
                    
                    <script>
                    	$(function(){
                    		if("${loginUser.gender}" == "M"){
                    			$("#Male").attr("checked", true);
                    		}else if("${loginUser.gender}" == "F"){
                    			$("#Female").attr("checked", true);
                    		}
                    	});
                    </script>
                    
                </div>
                <br>
                
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <%-- <a href="delete.me?userId=${ loginUser.userId }" class="btn btn-danger">탈퇴하기</a> --%>
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#updatePwdModal">비밀번호 변경</button>
                    <button type="button" onclick="$('#postForm').submit();" class="btn btn-danger">탈퇴하기</button>
                </div>
            </form>

        </div>
        <br><br>
    </div>
    
    <form action="delete.me" method="post" id="postForm">
    	<input type="hidden" name="userId" value="${ loginUser.userId }">
    </form>
    
   <!-- 로그인 클릭 시 뜨는 모달  -->
    <div class="modal fade" id="updatePwdModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">비밀번호 변경</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>

            <form action="updatePwd.me" method="post" onsubmit="return fnCheckPwd();">
                <!-- Modal Body -->
                <div class="modal-body">               
                    <label for="curPwd" class="mr-sm-2">현재 비밀번호:</label>
                    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="curPwd" name="curPwd">
                    
                    <label for="newPwd" class="mr-sm-2">변경할 비밀번호:</label>
                    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="newPwd" name="newPwd">
                    
                    <label for="checkPwd" class="mr-sm-2">변경할 비밀번호 확인:</label>
                    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="checkPwd" name="checkPwd">
                </div>
                
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">비밀번호 변경</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                </div>
            </form>
            </div>
        </div>
    </div>
    
    <script>
    	const fnCheckPwd = () => {
	    	const userPwd = $("input[name='curPwd']");
	    	const newPwd = $("input[name='newPwd']");
	    	const checkPwd = $("input[name='checkPwd']");
	    	
	    	// Pwd 비어있는지 체크
	    	if (userPwd.val().trim() === "" || newPwd.val().trim() === "" || checkPwd.val().trim() === "") {
	    		alert("비밀번호를 입력하세요");
	    		return false;
	    	}
	    	// 새 비밀번호 같은지 체크
	    	if (newPwd.val() !== checkPwd.val()) {
	    		alert("비밀번호가 다릅니다");
	    		checkPwd.val('');
	    		checkPwd.focus();
	    		return false;
	    	}
	    	
	    	return true;
    	}
    </script>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>