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
  	<c:if test="${pageType eq 'delete'}">
  		<c:set var="title" value="删除信息" />
  	</c:if>
  	<c:if test="${pageType eq 'detail'}">
  		<c:set var="title" value="详情信息" />
  	</c:if>
  	${title }
</ul>
   <div class="title_right"><strong>${title }</strong></div>
   <div style="width:100%; margin:auto">
   <form action="" method="post" name="myform" id="myform" >
   <input type="hidden" name="roleId" id="roleId" value="${role.roleId }" />
   <input type="hidden" name="pageType" id="pageType" value="${pageType }" />
   <input type="hidden" name="treeData" id="treeData" value='${treeData}' />
   <input type="hidden" name="menuIds" id="menuIds" value='${refMap.menuIds}' />
       <table class="table table-bordered" >
	        <tr>
	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色ID：</td>
	          <td width="30%">${role.roleId }</td>
	          <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色名称：</td>
	          <td width="30%">${role.roleName }</td>  
	        </tr>
	        <tr>
	          <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">备注：</td>
	          <td colspan="3">${role.remark }</td>
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
	     		<c:if test="${pageType eq 'delete'}">
		     		<iaf:ac operateNo="0901_004"><input type="button" value="删除" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="doRoleDelete()" /></iaf:ac>
		     	</c:if>
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