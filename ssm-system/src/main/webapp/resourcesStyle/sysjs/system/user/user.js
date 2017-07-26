$(function(){
	//初始化角色选择
	var userRoleJson = eval($("#userRoleJson").val());
	$.each(userRoleJson, function(i, userRole){
		$("#role_"+ userRole.roleId).attr("checked", status);
	});
});

// 跳转到新增页
function userInsert(){
	location.href='toUserOperation.do?pageType=insert';
}

//保存修改
function doUserSave(){
	var pageType = $("#pageType").val();
	if ($("#userId").val()==""){
		alert("用户编号不能为空");
		$("#userId").focus();
		return false;
	}
	if ($("#userName").val()==""){
		alert("用户名称不能为空")
		$("#userName").focus();
		return false;
	}
	if("insert" == pageType){
		if ($("#password").val()==""){
			alert("用户密码不能为空")
			$("#password").focus();
			return false;
		}
	}
	var roleIds = "";
	$('input[name="roleIds"]:checked').each(function(){
		roleIds += "," + $(this).val()
	}); 
	roleIds = roleIds.substr(1);
	if (roleIds == ""){
		alert("请选择角色")
		return false;
	}
	
	var myform = $("#myform");
	myform.attr("action", "doUserSave.do");
	if(confirm("您确定提交吗？")){
		myform.submit();
	}
}

//删除
function doUserDelete(){
	var myform = $("#myform");
	myform.attr("action", "doUserDelete.do");
	if(confirm("您确定删除吗？")){
		myform.submit();
	}
}

//跳转到新增页
function exportExcel(){
	location.href='exportExcel.do';
}
