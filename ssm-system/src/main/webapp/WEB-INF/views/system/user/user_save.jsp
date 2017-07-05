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
  	编辑信息
</ul>
   <div class="title_right"><strong>编辑数据参数</strong></div>
   <div style="width:70%; margin:auto">
   <input type="hidden" name="userRoleJson" id="userRoleJson" value='${userRoleJson}' />
   
   <form action="doUserSave.do" method="post" name="myform" id="myform">
   <input type="hidden" name="pageType" id="pageType" value="${pageType }" />
       <table class="table table-bordered" >
            <c:if test="${pageType eq 'update' }">
       		<tr>
	          <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户编号：</td>
	          <td width="60%"><input type="text" name="userId" id="userId" class="required noSpace" readonly="readonly" value="${user.userId }" /></td>
	        </tr>
	        </c:if>
	        
	        <c:if test="${pageType eq 'insert' }">
	       	<tr>
	          <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户编号：</td>
	          <td width="60%"><input type="text" name="userId" id="userId" class="required noSpace" value="${user.userId }" /></td>
	        </tr>
	        </c:if>
	        <tr>
	          <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户名称：</td>
	          <td width="60%"><input type="text" name="userName" id="userName"  class="required noSpace" value="${user.userName }"/></td>
	        </tr>
	        <tr>
	           <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">用户密码：</td>
	           <td width="60%"><input type="password" name="password" id="password" class="required password" value=""/></td>
	        </tr>
	        <tr>
	           <td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">角色列表：</td>
	           <td width="60%">
	           		<c:if test="${not empty roleList}">
	           		<table class="margin-bottom-20 table no-border">
	           			<tr>
				           <td>
				           		<label class="lableAuto">
				           			<input type="checkbox" name="allRole" id="allRole" onclick="selectAllBox('allRole', 'roleIds')"/><a >全选</a>
				           		</label>
				           </td>
				        </tr>
	           			<c:forEach items="${roleList}" var="role" varStatus="index">
		           			<c:if test="${index.count % 4 == 1}">
	           				<tr>
	           					<td>
	           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}"/>${role.roleName}</label>
	           					</td>
		           			</c:if>
		           			<c:if test="${index.count % 4 > 1}">
		           				<td>
		           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}"/>${role.roleName}</label>
		           				</td>
		           			</c:if>
		           			<c:if test="${index.count % 4 == 0}">
		           				<td>
		           					<label class="lableAuto"><input type="checkbox" name="roleIds" id="role_${role.roleId}" value="${role.roleId}"/>${role.roleName}</label>
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
		     	<input type="button" value="保存" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="doUserSave()"/>
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
