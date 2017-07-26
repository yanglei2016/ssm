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
    <a href="userList.do">用户管理</a>
  </ul>
  <div class="title_right"><strong>参数查询</strong></div>  
  <div style="width:100%; margin:auto">
	<form action="userList.do" method="post" name="myform" id="myform">
		<table class="table table-bordered">
			<tr>
				<td width="15%" align="right" bgcolor="#f1f1f1">用户编号</td>
				<td width="30%">
					
				</td>
				<td width="15%" align="right" bgcolor="#f1f1f1">用户名称</td>
				<td width="30%">
					
				</td>
			</tr>
		</table>
		<table class="margin-bottom-20 table  no-border">
			<tr>
				<td class="text-center">
					<input type="submit" value="查询"class="btn btn-info " style="width: 80px;" />
					<ssm:auth no="010101">
						<input type="button" id="addBtn" value="新增" class="btn btn-info " style="width: 80px;" onclick="userInsert()" />
					</ssm:auth>
					<ssm:auth no="010104">
						<input type="button" id="addBtn" value="导出" class="btn btn-info " style="width: 80px;" onclick="exportExcel()" />
					</ssm:auth>
				</td>
			</tr>
		</table>
	</form>

		<table class="table table-bordered">
			<tr>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>用户编号</strong></td>
				<td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>用户名称</strong></td>
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
						<td align="center">${bean.userId}</td>
						<td align="center">${bean.userName}</td>
						<td align="center">
							<a href="toUserOperation.do?pageType=detail&userId=${bean.userId}">详细</a>
			           		<ssm:auth no="010102">| <a href="toUserOperation.do?pageType=update&userId=${bean.userId}">修改</a></ssm:auth>
			           		<ssm:auth no="010103">| <a href="toUserOperation.do?pageType=delete&userId=${bean.userId}">删除</a></ssm:auth>
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