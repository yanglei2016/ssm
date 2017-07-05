<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/sysjs/system/user/user.js"></script>
</head>
<body>
     <div class="right"  id="mainFrame">
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">系统管理</a> <span class="divider">/</span>
  <a href="userList.do">用户管理</a> <span class="divider">/</span>
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
   <input type="hidden" name="userRoleJson" id="userRoleJson" value='${userRoleJson}' />
   
   <form action="" method="post" name="myform" id="myform" >
   <input type="hidden" name="userId" id="userId" value="${user.userId }" />
   <input type="hidden" name="pageType" id="pageType" value="${pageType }" />
       <table class="table table-bordered" >
	        <tr>
	          <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户编号：</td>
	          <td width="60%">${user.userId }</td>
	        </tr>
	        <tr>
	          <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户名称：</td>
	          <td width="60%">${user.userName }</td>  
	        </tr>
	        <tr>
	           <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色列表：</td>
	           <td width="60%">
	           		<c:if test="${not empty roleList}">
	           		<table class="margin-bottom-20 table no-border">
	           			<c:forEach items="${roleList}" var="role" varStatus="index">
		           			<c:if test="${index.count % 4 == 1}">
	           				<tr>
	           					<td>
	           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}" disabled="disabled"/>${role.roleName}</label>
	           					</td>
		           			</c:if>
		           			<c:if test="${index.count % 4 > 1}">
		           				<td>
		           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}" disabled="disabled"/>${role.roleName}</label>
		           				</td>
		           			</c:if>
		           			<c:if test="${index.count % 4 == 0}">
		           				<td>
		           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}" disabled="disabled"/>${role.roleName}</label>
		           				</td>
		           			</tr>
		           			</c:if>
	           			</c:forEach>
	           		</table>
	           		</c:if>
	           		
	           </td>
	        </tr>
	      </table>
	           
	     <table  class="margin-bottom-20  table no-border" >
	       <tr>
	     	<td class="text-center">
	     		<c:if test="${pageType eq 'delete'}">
		     		<input type="button" value="删除" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="doUserDelete()" />
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
