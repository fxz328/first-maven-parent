<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="/common/mystyle.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Manager</title>


<style type="text/css">
	body {padding-top:55px;}
	
</style>
</head>
<body>
		<!-- 导航条 -->
		<nav  class="navbar navbar-default navbar-fixed-top">
				<div class="container-fluid">
					 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					 </button>
					 <!-- Brand and toggle get grouped for better mobile display 品牌和切换为更好的移动显示分组-->
					 <!-- 头部信息 -->
					 <div class="navbar-header">
					 		<a class="navbar-brand glyphicon glyphicon-globe" href="http://www.jinkeit.com/" id="menu-toggle">
					 	User Manager 
					 	</a>
					 	
					 </div>
					
					 
					 <!-- Collect the nav links, forms, and other content for toggling 收集导航链接、表单和其他内容 -->
				
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<!-- 	  <ul class="nav navbar-nav">
						<li class="active"><a href="#"  class="glyphicon glyphicon-glass">&nbsp招标采购 <span class="sr-only">(current)</span></a></li>
						<li><a href="#" class="glyphicon glyphicon-grain">&nbsp师资团队</a></li>
						<li><a href="#" class="glyphicon glyphicon-sunglasses">&nbsp明星学员</a></li>
						<li><a href="#" class="glyphicon glyphicon-plane">&nbsp金科教育</a></li>
						<li class="dropdown">
						  <a href="#" class="dropdown-toggle glyphicon glyphicon-text-color" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">java课程 <span class="caret"></span></a>	
							 <ul class="dropdown-menu">
								<li><a href="#" class="glyphicon glyphicon-cloud">云计算</a></li>
								<li><a href="#" class="glyphicon glyphicon-th">云应用</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#" class="glyphicon glyphicon-info-sign">云服务</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#" class="glyphicon glyphicon-cloud-upload">大数据</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#" class="glyphicon glyphicon-cloud-download">数据挖掘</a></li>
						 	 </ul>
					     </li>
				   </ul> 
				   
				    <form class="navbar-form navbar-left">
			        	<div class="form-group">
			          		<input type="text" class="form-control" placeholder="Search">
			       	    </div>
			       			 <button type="submit" class="btn btn-default">Submit</button>
				 	 </form>
				   -->
				   
				 	
			  
			  		<!-- 右边的    导航 按钮 -->
			  		<c:if test="${userInfo==null}">
			  		<ul class="nav navbar-nav navbar-right">
				        <li><a href="login.jsp">登录 ，</a></li>
				        <li><a href="http://www.jinkeit.com/">注册</a></li>
				      </ul>
				      </c:if>
				      <c:if test="${userInfo!= null}">
				      <ul class="nav navbar-nav navbar-right">
				      <li><a href="logout.ht">欢迎，${userInfo.userName}</a></li>
				        <li><a href="logout.ht">退出登录</a></li>
				      </ul>
				      </c:if>
				</div>
			</div>
		</nav>
		 <!-- =======================导航栏结束============================= --> 
<!--=========栅格布局 ==============-->
	<div class="row">
		<!-- 左边的栏目 -->
		  <div class="col-md-3 ">
		  	<!--  <a class="list-group-item glyphicon glyphicon-asterisk" data-addtab="userMenu"  data-target="#tabs" data-url="http://www.baidu.com">
			  管理菜单
			 </a>  -->
			 <!-- tree 的插件 -->
			  <div id="tree" class="treeview"></div>                     
			 
		  </div>
		<div class="col-md-9">
			
				<!--   选项卡    -->
				<!-- Nav tabs -->
				<ul id="tabs" class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active">
				        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">欢迎</a>
				    </li>
				</ul>
			  	<!-- 选项卡  内容 -->
				<!-- Tab panes -->
				<div class="tab-content">
				    <div role="tabpanel" style="" class="tab-pane active " id="home">后台管理系统</div>
				</div> 
		</div>	 
    </div>	
<input type="hidden" id="loginName" value="${userInfo.userName}">
	<script type="text/javascript">
	var tree = [
				{
   				 	text: "系统管理"
 				 },
	            {
	              id:1,
	              text: "后台管理",
	              //nodes 子字节json数组
	              nodes: [
	                {
	                  id:2,
	                  text: "用户管理",
	                  url:'show.ht'
	                 /*  nodes: [
	                    
	                  ] */
	                },
	                {
	                		id:3,
		                  text: "角色管理",
		                  url:'showRole.ht'
		                }
		               
	              ]
	            },
	            {
	              text: "积分管理"
	            }
	          ];
	
	$(function(){
		//判断是否登录状态
		  if ($("#loginName").val()=="") {
			location.href="login.jsp";
		}  
	<%-- 	//选项卡高度
		// $.addtabs({iframeUse:true})
		$.addtabs({iframeHeight:900});
				$("#tree").treeview({
					//data属性 树节点信息 json数组
					  data:getTreeData(),         
					  //tree默认展开的节点级别默认为2
					  levels: 0,
						//含有子节点的图标
					  collapseIcon:'glyphicon glyphicon-star-empty',
					//没有子节点的图标
					  emptyIcon:'glyphicon glyphicon-usd',
					  //背景颜色
					  //backColor: 'green'
					  //是否显示复选框
					  showCheckbox:true,
					  //是否允许选中多个节点
					  //multiSelect:true,
					  //启用节点的超链接功能默认为false,节点需指定href属性
					 enableLinks:true,
					  backColor:"#b5b5b5",
					  //事件当节点选中时
					  onNodeSelected:function(event,node){
						  console.log(node);
						  //alert(node.href);
						  //动态向 nav-tabs 导航标签添加tab选项卡
						  //addTabs方法  add() 添加tab  close()关闭tab  closeAll() 关闭全部tab
						  if(null !=  node.href && "" != node.href){
							 $.ajax({
			            			url:"<%=request.getContextPath() %>/" + node.href,
			            			success:function(data) {
			            				alert(data);
			            				$.addtabs.add({id:node.text,title:node.text,content:data});
			            			}
			            		}); 
			            		
							 /* $.addtabs.add({
								   id:node.id,
								   title:node.text,
								   url:node.href,
							  })  */
						  }

					  }
				}) --%>
	
	
				<%-- //初始化树
		    	 $('#tree').treeview({
		    		 enableLinks:true,
		    		data:getTreeData(),
		    		onNodeSelected:function(event, node) {
		    			if (null != node.url && "" != node.url) {
		    				//发送ajax请求
		            		$.ajax({
		            			url:"<%=request.getContextPath() %>" + node.url,
		            			success:function(data) {
		            				$.addtabs.add({id:node.text,title:node.text,content:data});
		            			}
		            		});
		    			}
		    		}
		    	});  --%>
		    	
		    	
$.addtabs({iframeHeight:900});
				
				 $.ajax({
					type:"post",
					data:{userID:"${userInfo.userID}"},
					url:"<%=request.getContextPath()%>/selectTreeListJson.ht",
					success:function(result){
						$("#tree").treeview({
							//data属性 树节点信息 json数组
							  data: result,         
							  //tree默认展开的节点级别默认为2
							  levels: 0,
								//含有子节点的图标
							  collapseIcon:'glyphicon glyphicon-star-empty',
							//没有子节点的图标
							  emptyIcon:'glyphicon glyphicon-usd',
							  //背景颜色
							  //backColor: 'green'
							  //是否显示复选框
							  showCheckbox:true,
							  //是否允许选中多个节点
							  //multiSelect:true,
							  //启用节点的超链接功能默认为false,节点需指定href属性
							  //enableLinks:true,
							  backColor:"#b5b5b5",
							  //事件当节点选中时
							  onNodeSelected:function(event,node){
								  //动态向 nav-tabs 导航标签添加tab选项卡
								  //addTabs方法  add() 添加tab  close()关闭tab  closeAll() 关闭全部tab
								  if(null !=  node.url){
									  $.addtabs.add({
										   id:node.id,
										   title:node.text,
										   url:node.url,
									  })
								  }

							  }
						})
					}
				}) 
	})
	
	//获取菜单数据
    	function getTreeData() {
    		var tree_data = [];
    		//发送ajax请求
    		$.ajax({
    			async:false,//请求为同步
    			url:"<%=request.getContextPath() %>/selectTreeListJson.ht",
    			data:{userID:"${userInfo.userID}"},
    			dataType:"json",
    			success:function(data) {
    				tree_data = data;
    			}
    		});
    		return tree_data;
    	}
	
	</script>






</body>
</html>