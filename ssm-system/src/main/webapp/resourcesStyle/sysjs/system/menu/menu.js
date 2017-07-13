//*************************菜单树*********begin*************************
	//树形设置
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClickMenu
		}
	};
	
	//初始化菜单树
	$(document).ready(function(){
		var treeData = eval($("#treeData").val());
		$.fn.zTree.init($("#menuTree"), setting, treeData);
	});
	
	//菜单单击
	function onClickMenu(event, treeId, treeNode){
		var url = "selectOneMenu.do";
		var param = {'menuId' : treeNode.id };
		var method = "onClickMenuReturn";
		
		juqeryAjaxRequest(url, param, method);
	}
	
	//菜单单击返回
	function onClickMenuReturn(resultMsg, param){
//		alert("resultMsg:"+resultMsg);
		resultMsg = $.parseJSON(resultMsg);
		if(resultMsg.code == "success"){
			$("#menuId").val(resultMsg.menu.menuId);
			$("#menuName").val(resultMsg.menu.menuName);
			$("#parentId").val(resultMsg.menu.parentId);
			$("#menuType").val(resultMsg.menu.menuType);
			$("#reqUrl").val(resultMsg.menu.reqUrl);
			$("#orderSeq").val(resultMsg.menu.orderSeq);
		}else{
			alert(resultMsg.message);
		}
	}
	
	//删除菜单
	function deleteMenu(){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var treeNodes = treeObj.getSelectedNodes();
		var menuIds = "";
		if(treeNodes == null || treeNodes.length != 1){
			alert("请选择要删除的的菜单！");
		}else{
			if(confirm("确定要删除此菜单及其所有子菜单吗？")){
				menuIds = getSelectedChildrenNodes(treeNodes[0]);
				deleteMenuByIds(menuIds);
			}
		}
	}
	
	//根据选择的菜单ID删除菜单
	function deleteMenuByIds(menuIds){
		var url = "deleteMenu.do";
		var param = {'menuIds' : menuIds};
		var method = "deleteMenuReturn";
		juqeryAjaxRequest(url, param, method);
	}
	
	//删除菜单后的返回
	function deleteMenuReturn(resultMsg, param){
		resultMsg = $.parseJSON(resultMsg);
		if(resultMsg.code == "success"){
			var treeObj = $.fn.zTree.getZTreeObj("menuTree");
			var treeNode = treeObj.getNodeByParam("id", param.menuIds.split(",")[0], null);
			treeObj.removeNode(treeNode);
			alert("删除菜单成功！");
		}else{
			alert(resultMsg.message);
		}
	}
	
	//获取所选节点的子节点ID
	function getSelectedChildrenNodes(treeNode){
		var menuIds = treeNode.id;
		if(treeNode.isParent){
			if(treeNode.isParent){
				var childrenNodes = treeNode.children;
				if(childrenNodes){
					for(var i = 0; i < childrenNodes.length; i++){
						menuIds += ',' + childrenNodes[i].id;
					}
				}
			}
		}
		return menuIds;
	}
	
	//打开新增页面
	function toMenuSave(){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var treeNodes = treeObj.getSelectedNodes();
		if(treeNodes == null || treeNodes.length != 1){
			alert("请选择要新增的菜单的父菜单！");
		}else{
			var parentId = treeNodes[0].id;
			var parentMenuName = treeNodes[0].name;
			windowOpen('toMenuSave.do?parentId='+ parentId +'&parentMenuName='+ encodeURIComponent(encodeURIComponent(parentMenuName)), '', 'status,scrollbars,resizable,location=no');
		}
	}
	

	function doMenuSave(){
		var menuId = $("#menuId").val();
		var menuName = $("#menuName").val();
		var parentId = $("#parentId").val();
		var menuType = $("#menuType").val();
		var reqUrl = $("#reqUrl").val();
		var orderSeq = $("#orderSeq").val();
		
		
		$("#menuId", window.opener.document).val(menuId);
		$("#menuName", window.opener.document).val(menuName);
		$("#parentId", window.opener.document).val(parentId);
		$("#menuType", window.opener.document).val(menuType);
		$("#reqUrl", window.opener.document).val(reqUrl);
		$("#orderSeq", window.opener.document).val(orderSeq);
		
		$("#pageType", window.opener.document).val("insert");
		$("#treeForm", window.opener.document).submit();
	    window.close();
	}

//*************************菜单树*********end*************************	
	//打开新增页面
	function toMenuSaveDiv(){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var treeNodes = treeObj.getSelectedNodes();
		if(treeNodes == null || treeNodes.length != 1){
			alert("请选择要新增的菜单的父菜单！");
		}else{
			var parentId = treeNodes[0].id;
			var parentMenuName = treeNodes[0].name;
			$("#parentId1").val(parentId);
			
//			$("#addBtn").prop("data-toggle", "modal");
//			$("#addBtn").prop("href", "#addDiv");
			
			
			
			$("#aaaaaa").click();
		}
	}