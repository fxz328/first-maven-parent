<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- ajaxSetup ajax重定向问题 -->
<script type="text/javascript" src="common/ajaxSetup.js"></script>
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css" />
<script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script>
</head>
<body>
		<form id="addForm" action="insertUserInfo.ht" class="form-inline" method="post" enctype="multipart/form-data">
			<input type="hidden" name="userID" value="${user.userID}">
			  <div class="form-group">
			    <label for="userName">用户名：</label>
			    <input type="text" class="form-control" name="userName"  placeholder="请输入用户名..." value="${user.userName}">
			  </div>
			  <br><br>
			   <div class="form-group">
			    <label for="userPassword">密码：</label>
			    <input type="password" class="form-control" name="userPassword"  placeholder="请输入密码..." value="${user.userPassword}">
			  </div>
			  <br><br>
			  <div class="form-group">
			    <label for="userImage">头像：</label>
			    <img alt="" id = "user_img" src="<%=request.getContextPath()%>/${user.userImg}" width="80px">
			    <input  id="userImage" name="uimg"  type="file" />
			    
			    <input class="form-control" id="imgName" name="userImg" value="${user.userImg}" type="hidden" />
  			 </div>
  			 <input type="submit" value="提交" >
			</form>
		<script type="text/javascript">
	
	    //初始化一个uploadfiy上传控件
	    $(function(){
	    	$("#userImage").uploadify({
	    		'swf':'uploadify/uploadify.swf',//swf uploadify的控制展示属性 flash基础文件 上传的进度条 和上传按钮功能
	    		'uploader':'uploadFile.ht;sessionid="+"<%=request.getSession().getId()%>',//声明文件的上传地址 上传到对应的action请求
	    		'buttonText':'上传海报',
	    		'mulit':false,
	    		'fileTypeDesc':'只能上传图片',
	    		'fileTypeExts':'*.jpg;*.png',
	    		'fileObjName':'uimg',
	    		
	    		'onUploadSuccess':function(response,data){//第二个参数为后台返回的数据
	    			data = eval("("+data+")");
	    			//alert(data);
	    			//alert(data.backUrl);
	    			//console.log(data);
	    		   //替换图片原有路径 达到上传图片预览的目的
	    		    $("#user_img").attr("src",data.backUrl);
	    		    $("#imgName").val(data.backUrl);
	    		}
	    	})
	    })
	</script>	  
</body>
</html>