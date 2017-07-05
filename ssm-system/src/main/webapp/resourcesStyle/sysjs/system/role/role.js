// 跳转到新增页
function roleInsert(){
	location.href='toRoleOperation.do?pageType=insert';
}

//保存修改
function doRoleSave(){
	var myform = $("#myform");
	myform.attr("action", "doRoleSave.do");
	if ( $.trim($("#roleName").val())==""){
		alert("角色名称不能为空")
		$("#roleName").focus();
		return false;
	}
	
	var menuIds = getSelectedNodes();
	if(menuIds == ""){
		alert("请选择菜单元素");
		return false;
	}
	$("#menuIds").val(menuIds);
	
	if(confirm("您确定提交吗？")){
		myform.submit();
	}
}

//删除
function doRoleDelete(){
	var myform = $("#myform");
	myform.attr("action", "doRoleDelete.do");
	if(confirm("您确定删除吗？")){
		myform.submit();
	}
}

//*************************菜单树*********begin*************************
//树形设置
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "ps", "N": "s" }
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

//初始化菜单树
$(document).ready(function(){
	var treeData = eval($("#treeData").val());
	$.fn.zTree.init($("#menuTree"), setting, treeData);
	initCheckedNodes();
});

//获取所有选择节点
function getSelectedNodes(){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = treeObj.getCheckedNodes();
	var menuIds = "";
	for(var i = 0; i < nodes.length; i++){
		menuIds += "," + nodes[i].id;
	}
//	alert("menuIds:"+menuIds.substr(1));
	return menuIds.substr(1);
}

//初始化选中节点
function initCheckedNodes(){
	var menuIds = $("#menuIds").val();
	if(menuIds != ""){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var menuIdArray = menuIds.split(",");
		for(var i = 0; i < menuIdArray.length; i++){
			var node = treeObj.getNodeByParam("id", menuIdArray[i], null);
			treeObj.checkNode(node, true, false);
		}
	}
}


//*************************菜单树*********end*************************