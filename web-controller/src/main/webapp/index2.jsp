<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="common/ajaxSetup.js"></script>
</head>
<body>
	<center>
	
			<input type="button" value="登录" onclick="login()">
	
</center>
<script type="text/javascript">
		
		//登录验证
		function login(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/queryUserInfo.ht",
				dataType:"json",
				success:function(result){
					console.log(result);
				}
			})
		}

</script>
</body>
</html>