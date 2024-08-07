<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
	request.setAttribute("resultContent", gContent);
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>read.jsp</title>

	<script language=javascript>

	.
		
		//삭제 요청 로직 수행 기능 
		function sendDelete(){
			//js의 확인 및 입력 가능한 창 + 확인, 취소 버튼을 제공하는 내장 함수 
			var password = prompt("삭제할 게시물의 비밀번호를 입력하세요");
			
			if(password){  //데이터가 있으면 true
				document.requestForm.command.value ="delete";  //command=delete
				document.requestForm.password.value = password;  //hidden tag 에 입력된 pw값 대입
				document.requestForm.submit();
			}else{
				return false;
			}
		}
	</script>
	
</HEAD>

<body>
	<table align="center" cellpadding="5" cellspacing="2" width="60%" border='1'>
	    <tr>
	        <td width="1220" height="20" colspan="4" bgcolor="#336699">
	            <p align="center">
	            	<font color="white" size="3">
	            		<b>${requestScope.resultContent.num}번 게시물 자세히보기</b>
	            	</font>
	            </p>
	        </td>
	    </tr>
	    <%--
			request.setAttribute("resultContent", gContent);  GuestBookBean객체
		--%>
	    
	    <tr>
	        <td width="100" height="20">
	            <p align="right"><b><span style="font-size:9pt;">작성자</span></b></p>
	        </td>
	        <td width="450" height="20" colspan="3">
	        	<span style="font-size:9pt;"><b>${requestScope.resultContent.author}</b></span>
	        </td>
	    </tr>
	    <tr>
	        <td width="100" height="20" >
	            <p align="right"><b><span style="font-size:9pt;">작성일</span></b></p>
	        </td>
	        <td width="300" height="20">
	        	<span style="font-size:9pt;"><b>${requestScope.resultContent.writeday}</b></span>
	        </td>
	        <td width="100" height="20" >
				<p align="right"><b><span style="font-size:9pt;">조회수</span></b></p>
			</td>
	        <td width="100" height="20">
				<p><b><span style="font-size:9pt;"></span>${requestScope.resultContent.readnum}</b></p>
			</td>
	    </tr>
	    <tr>
	        <td width="100" height="20">
	            <p align="right"><b><span style="font-size:9pt;">제 목</span></b></p>
	        </td>
	        <td width="450" height="20" colspan="3">
	        	<span style="font-size:9pt;"><b>${requestScope.resultContent.title}</b></span>
	        </td>
	    </tr>
	    <tr>
			<td width="100" height="200" valign="top">
	            <p align="right"><b><span style="font-size:9pt;">내 용</span></b></p>
	        </td>
			<!-- 브라우저에 글 내용을 뿌려줄 때는 개행문자(\n)가 <br>태그로 변환된 문자열을 보여줘야 한다. -->
	        <td width="450" height="200" valign="top" colspan="3">
	        <span style="font-size:9pt;"><b>${requestScope.resultContent.content}</b></span></td>
	    </tr>
	    
	    <tr>
	        <td height="20" colspan="4" align="center" valign="middle">
	        
		<!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다.
			function sendUpdate(){
				document.requestForm.command.value ="updateForm";
				document.requestForm.submit();
			}
			
			document = html 문서를 의미하는 브라우저 내장 객체
				- document. : html 문서에서 무언가를 찾겠다는 설정
			document.requestForm = html문성에서 requestForm 이름으로 되어 있는 tag를 찾겠다
			document.requestForm.command = 이미 찾은 requstForm 하위의 command tag 찾음
			document.requestForm.command.value = "updateForm"; 
				- 찾은 tag의 value 속성에 updateForm 값 반영 
			document.requestForm.submit();
				- form의 action속성에 지정된 server 프로그램으로 실제 전송
				input의 type="submit"와 동일
				
			
			
		//삭제 요청 로직 수행 기능 
		function sendDelete(){
			//js의 확인 및 입력 가능한 창 + 확인, 취소 버튼을 제공하는 내장 함수 
			var password = prompt("삭제할 게시물의 비밀번호를 입력하세요");
			
			if(password){
				document.requestForm.command.value ="delete";
				document.requestForm.password.value = password;
				document.requestForm.submit();
			}else{
				return false;
			}
		}
					
		-->
				<form name="requestForm" method="post" action="board">
					<input type="hidden" name="num" value="${requestScope.resultContent.num}">
					<input type="hidden" name="command" value="">
					<input type="hidden" name="password" value="">
					
					<!-- onclick : java script의 함수 -->
					<input type="button" value="수정하기" onclick="sendUpdate()">
					<input type="button" value="삭제하기" onclick="sendDelete()">
				</form>
				
			</td>
	    </tr>
	</table>
	<hr>
	<div align='right'><span style="font-size:9pt;">&lt;<a href="board?command=list">리스트로 돌아가기</a>&gt;</span></div>


</body>
</html>