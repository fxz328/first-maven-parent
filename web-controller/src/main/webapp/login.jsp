<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login interface</title>
<!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body>
	 <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>登录界面</strong> </h1>
                            <!-- <div class="description">
                            	<p>
	                            	This is a free responsive login form made with Bootstrap. 
	                            	Download it on <a href="http://azmind.com"><strong>AZMIND</strong></a>, customize and use it as you like!
                            	</p>
                            </div> -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<!-- <div class="form-top-left">
                        			<h3>Login to our site</h3>
                            		<p>Enter your username and password to log on:</p>
                        		</div> -->
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" id="loginInfo" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户账号</label>
			                        	<input type="text" name="userName" placeholder="用户账号..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="userPassword" placeholder="密码..." class="form-password form-control" id="form-password">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">验证码</label>
			                        	<input type="text" name="userImgCode" placeholder="验证码..." class="form-password form-control">
			                        	<span onclick="change_imgcode()">
											<img id="imgcode_src_node" width="100" src="<%=request.getContextPath() %>/imgcode">
											&nbsp;<font color="#888888">看不清，点击换一张</font>
										</span>
										
			                        </div>
			                        <button type="button" class="btn" onclick="login()">登录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                
                    </div>
                </div>
            </div>
            
        </div>


  <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
<script type="text/javascript">
		//切换验证码
		function change_imgcode() {
			$("#imgcode_src_node").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
		}
		//登录验证
		function login(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/login.ht",
				data:$("#loginInfo").serialize(),
				success:function(result){
					console.log(result);
					if(result.flag==1){
						location.href = "index.jsp";
						return;
					}
					if (2 == result.flag) {
						alert("用户不存在");
						return;
					}
					if (3 == result.flag) {
						alert("密码错误,已输错"+result.failCount+"次");
						return;
					}
					if (4 == result.flag) {
						alert("验证码错误");
						return;
					}
					if (5 == result.flag) {
						alert("验证码为空");
						return;
					}
					if (6 == result.flag) {
						alert("登录连续错误三次，已被锁定！");
						return;
					}
				
				}
			})
		}

</script>
</body>
</html>