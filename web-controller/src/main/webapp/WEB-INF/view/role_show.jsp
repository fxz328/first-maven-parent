<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <jsp:include page="/common/mystyle.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
 <!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
 <script type="text/javascript" src="ztree/js/jquery.ztree.all.min.js"></script>

</head>
<body>
	<!-- 搜索面板 -->
    <div class="tab-content" style="padding: 4px">
	    <form id="search_user_form">
	    	<label>角色：</label>
			<input id="query_user_name" class="form-control-sm" placeholder="请输入角色">
			<div align="center">
				<!-- Single button -->
				<div class="btn-group">
				  <button type="button" class="btn btn-success glyphicon glyphicon-search" onclick="search_user()">搜索</button>
				</div>
				<div class="btn-group">
				  <button type="button" class="btn btn-danger glyphicon glyphicon-repeat" onclick="reset_search_user_form()">重置</button>
				</div>
			</div>
		</form>
    </div>
	
		<!-- 表格工具条 -->
	  <div id="tabToolBar">
			<div class="btn-group">
		    <button type="button" class="btn btn-success" onclick="showAddUser()">
		    	<span class="glyphicon glyphicon-plus">新增</span>
		    </button>
		   <!--  <button type="button" class="btn btn-info" onclick="showUpdateUser()">
		    	<span class="glyphicon glyphicon-erase">修改</span>
		    </button> -->
		    <button type="button" class="btn btn-primary" onclick="deleteUser()">
		    	<span class="glyphicon glyphicon-trash" >批量删除</span>
		    </button>
		     <!-- <button type="button" class="btn btn-primary">
		    	<span class="glyphicon glyphicon-arrow-up" onclick="firstUser()">置顶</span>
		    </button> -->
	   	   </div>
		</div>
	<table id="roleTable"></table>
	
	<script type="text/javascript">
	//赋值角色
	<%-- function roleOperation(userid){
		dialog1("用户管理>>用户赋角色","<%=request.getContextPath()%>/toRolePage.ht?userID="+userid);
	} --%>
	
	function menuOperation(roleid){
		var dialog = bootbox.dialog({
		 title: "角色管理>>角色赋权限",
	 	message:$('<div></div>').load("<%=request.getContextPath()%>/toMenuPage.ht?roleID="+roleid),
	 	buttons: [{
            label: '确定',
            cssClass:"btn btn-success",
            callback: function(dialogItself){
            	//alert("hehe");
            	var role_json_array = get_selection_tree_nodes();
             	 console.log(role_json_array);
            	//使用ajax保存结果
            	$.ajax({
            		url:"<%=request.getContextPath() %>/insertRoleMenuList.ht",
            		data:JSON.stringify(role_json_array),
            		dataType:"json",
            		type:"post",
            		success:function(data) {
            			//关闭对话框
            			$("#roleTable").bootstrapTable('refresh');
            		},
            		contentType:"application/json"
            	});
            }
        }, {
            label: '取消',
            cssClass:"btn btn-danger",
            action: function(dialogItself){
            	$("#userTable").bootstrapTable('refresh');
            }
        }]
   	 	
	});
}
	//批量删除
	function deleteUser(){
		if($("[name='chk']:checked").length>0){
			var arr= $("[name='chk']:checked");
			var ids = "";
			
			for(var i=0;i<arr.length;i++){
				ids+=","+arr[i].value;
			}
			ids = ids.substr(1);
			//bootbox.alert(ids);
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/deletedUserAll.ht",
				data:{"ids":ids},
				success:function(result){
					bootbox.alert("删除成功");
					$("#userTable").bootstrapTable('refresh');
				}
			})
		}else{
			bootbox.alert("请至少选择一个");
		}
	}
	//单删
	function deleteUserById(ids){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/deletedUserAll.ht",
			data:{"ids":ids},
			success:function(result){
				bootbox.alert("删除成功");
				$("#userTable").bootstrapTable('refresh');
			}
		})
	}
	//修改
	function showUpdateUser(id){
		/* if($("[name='chk']:checked").length==1){
			var id=$("[name='chk']:checked")[0].value; */
			//bootbox.alert(id);
			dialog('修改信息',"<%=request.getContextPath()%>/toAddPage.ht?userID="+id);
			/* }else{
				
				bootbox.alert("请选择一个");
			} */
	}
	
	//新增
	function showAddUser(){
		dialog('新增','<%=request.getContextPath()%>/toAddPage.ht')
	}
	
	function dialog(title,url){
		var dialog = bootbox.dialog({
		 title: title,
	 	message:addPageInfo(url),
   	
   	 	
	});
}
	
	function addPageInfo(url){
		var html = "";
		$.ajax({
			type:"post",
			url:url,
			async:false,
			dataType:"html",
			success:function(result){
				//bootbox.alert(result);
				html = result;
			},
			error:function(){
				bootbox.alert("ajax失败");
			}
		})
		return html;
	}
	
	$(function(){
			$("#roleTable").bootstrapTable({
				url:"<%=request.getContextPath()%>/queryRoleTableInfo.ht",
				dataType:"json",
				 method:"post",
				 striped: true,  	// 斑马线效果     默认false 
				 //只允许选中一行
				 singleSelect:true,
				 //选中行是不选中复选框或者单选按钮
				 clickToSelect:true,
				 showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
				 cardView: false,                    //是否显示详细视图
				 uniqueId: "roleID",                 //每一行的唯一标识，一般为主键列
				 showColumns: true,                  //是否显示所有的列
				 showRefresh: true,                  //是否显示刷新按钮
				 minimumCountColumns: 2,     //  最少留两列
				 detailView: false,                  //是否显示父子表
				 //发送到服务器的数据编码类型  
				contentType:'application/x-www-form-urlencoded;charset=UTF-8',   //数据编码纯文本  offset=0&limit=5
				//toolbar:'#tabToolBar',   //  工具定义位置
				columns:[      // bootstrapTable  [] 
				           	{field:'roleID',title:'选中',
								formatter:function(value,row,index){   //  格式化  当前单元格内容
									return "<input type='checkbox' value="+value+" name='chk'/>";
								}
							},
				           	{field:'roleID',title:'ID'},
							{field:'roleName',title:'角色名称'},
							{field:'roleDesc',title:'描述'}
							,
							{
								field:'roleID',title:'操作',
								formatter:function(value){
									return '<button type="button" class="btn btn-success" onclick="menuOperation('+value+')">角色操作 </button><font>  </font><button type="button" class="btn btn-info" onclick="showUpdateUser('+value+')">编辑 </button> <button type="button" class="btn btn-primary" onclick="deleteUserById('+value+')">删除 </button>';
							    	
								   
								}
							}
				         ],
				       //传递参数（*）
						 queryParams: function(params) {
							 	var whereParams = {    
							 			/*
							 				分页  自定义的参数         默认传 limit（展示几条）    offset（从第几条开始    起始条数）           
							 			*/
							 			"end":params.limit,
							 			"start":params.offset,
							 			"u":params.search,//精确搜索产品名称
							 			"roleName":$("#query_user_name").val(),
							 			
							 	}
								 return whereParams;
							 },
							//设置分页
								pagination:true,
								paginationLoop:true,
								pageNumber:1,
								pageSize:1,
								pageList:[1,3,5,8,10],
								
							sidePagination:'server',
				});
		})
		
		function search_user(){
		//refresh 调用方法
		$("#roleTable").bootstrapTable('refresh',
				{query: {
					"roleName":$("#query_user_name").val(),
		 			
				}}
		);
	}

			
	</script>
</body>
</html>