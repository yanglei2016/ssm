<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/sysjs/system/role/role.js"></script>
</head>
<body>
  <div class="right"  id="mainFrame">
  <div class="right_cont">
  <ul class="breadcrumb">当前位置：
    <a href="#">系统管理</a> <span class="divider">/</span>
    <a href="roleList.do">角色管理</a>
  </ul>
  <div class="title_right"><strong>参数查询</strong></div>  
  <div style="width:100%; margin:auto">
	<form action="roleList.do" method="post" name="myform" id="myform">
		<table class="table table-bordered">
			<tr>
				<td width="20%" align="right" bgcolor="#f1f1f1">编号</td>
				<td width="30%">
					
				</td>
				<td width="15%" align="right" bgcolor="#f1f1f1">名称</td>
				<td width="30%">
					
				</td>
				
			</tr>
		</table>
		<table class="margin-bottom-20 table  no-border">
			<tr>
				<td class="text-center">
					<input type="submit" value="查询"class="btn btn-info " style="width: 80px;" />
					<ssm:auth no="010201"><input type="button" id="addBtn" value="新增" class="btn btn-info "
						style="width: 80px;" onclick="roleInsert()" /></ssm:auth>
				</td>
			</tr>
		</table>
	</form>

		<table class="table table-bordered">
			<tr>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>角色编号</strong></td>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>角色名称</strong></td>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>备注</strong></td>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1" width="15%"><strong>操作</strong></td>
			</tr>
			<c:if test="${empty resultList}">
			<tr>
				<td align="center" colspan="9">暂无数据</td>
			</tr>
			</c:if>
			<c:if test="${not empty resultList }">
				<c:forEach var="bean" items="${resultList}" varStatus="status">
					<tr ${status.count % 2 == 1 ? "" : "bgcolor='#f2f2f2'"} onmouseover="mouseOver(this)" onmouseout="mouseOut(this)">
						<td align="center">${bean.roleId}</td>
						<td align="center">${bean.roleName}</td>
						<td align="center">${bean.remark}</td>
						<td align="center">
							<a href="toRoleOperation.do?pageType=detail&roleId=${bean.roleId}">详细</a>
			           		<ssm:auth no="010202">| <a href="toRoleOperation.do?pageType=update&roleId=${bean.roleId}">修改</a></ssm:auth>
			           		<ssm:auth no="010203">| <a href="toRoleOperation.do?pageType=delete&roleId=${bean.roleId}">删除</a></ssm:auth>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%@include file="/common/page_plug_in.jsp" %>
    </div> 
  </div>
  </div>
</body>