<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/sysjs/system/role/role.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.exedit.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resourcesStyle/css/zTree/zTreeStyle.css" type="text/css"/>
</head>
<body>
     <div class="right"  id="mainFrame">
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">角色管理</a> <span class="divider">/</span>
  <a href="roleList.do">角色管理</a> <span class="divider">/</span>
  	编辑信息
</ul>
   <div class="title_right"><strong>编辑数据参数</strong></div>
   <div style="width:100%; margin:auto">
   <form action="doRoleSave.do" method="post" name="myform" id="myform">
   <input type="hidden" name="pageType" id="pageType" value="${pageType }" />
   <input type="hidden" name="treeData" id="treeData" value='${treeData}' />
   <input type="hidden" name="menuIds" id="menuIds" value='${refMap.menuIds}' />
       <table class="table table-bordered" >
            <c:if test="${pageType eq 'update' }">
       		<tr id="oldTr">
	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色ID：</td>
	          <td width="30%"><input type="text" name="roleId" id="roleId" class="required noSpace" readonly="readonly" value="${role.roleId }" /></td>
	          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色名称：</td>
	          <td width="30%"><input type="text" name="roleName" id="roleName"  class="required noSpace" value="${role.roleName }"/></td>
	        </tr>
	        </c:if>
	        <c:if test="${pageType eq 'insert' }">
	       	<tr id="oldTr">
<!-- 	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色ID：</td> -->
<%-- 	          <td width="30%"><input type="text" name="roleId" id="roleId" class="span1-1" value="${role.roleId }" /></td> --%>
	          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色名称：</td>
	          <td width="30%"><input type="text" name="roleName" id="roleName"  class="span1-1" value="${role.roleName }"/></td>
	        </tr>
	        </c:if>
	        <tr id="oldTr">
	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">备注：</td>
	          <td width="30%"><input type="text" name="remark" id="remark"  class="span1-1" value="${role.remark }"/></td>
	        </tr>
	        <tr id="oldTr">
	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">菜单元素：</td>
	          <td width="30%" colspan="3">
	          		<div id="menuTree" class="ztree" style="height: 400px;overflow: scroll;"></div>
	          </td>
	        </tr>
	     </table>
	     <table  class="margin-bottom-20  table no-border" >
	       <tr>
	     	<td class="text-center">
		     	<input type="button" value="保存" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="doRoleSave()" />
		     	<input type="button" value="返回" class="btn btn-info  " style="width:80px;" name="backBtn" onclick="history.back()" />
	     	</td>
	     </tr>
	   </table>
	 </form>
		</div>     
     </div>     
     </div>
</body>
</html>