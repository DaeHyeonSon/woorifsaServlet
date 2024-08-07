<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	죄송합니다. 문제가 발생하였습니다.<br>
	발생된 문제 : ${requestScope.errorMsg}<br>

	<a href="board?command=list"> 리스트로 돌아가기</a> <a href="javascript:history.back(1)">뒤로가기</a>
</body>
</html>      