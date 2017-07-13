<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/sysjs/system/menu/menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.exedit.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resourcesStyle/css/zTree/zTreeStyle.css" type="text/css"/>
</head>
<body>
  <div class="right"  id="mainFrame">
  <div class="right_cont">
  <ul class="breadcrumb">当前位置：
    <a href="#">系统管理</a> <span class="divider">/</span>
    <a href="menuTree.do">菜单管理</a>
  </ul>
  <h4 align="center">菜单树</h4>
  <div class="title_right"></div>  
  <div style="width:70%; margin:auto">
	<table class="table table-bordered">
		<tr>
			<td width="45%" align="center" bgcolor="#f1f1f1">菜单树</td>
			<td width="10%" align="center" bgcolor="#f1f1f1">操作</td>
			<td width="45%" align="center" bgcolor="#f1f1f1">编辑信息</td>
		</tr>
		<tr>
			<td height="400px" align="center" valign="top">
				<div id="menuTree" class="ztree" style="height: 400px;overflow: scroll;"></div>
			</td>
			<td height="400px" align="center">
				<input type="button" value="新增" class="btn btn-info" style="width:80px;" name="saveBtn" onclick="toMenuSave()"/>
				<br/><br/>
				<input type="button" value="删除" class="btn btn-info" style="width:80px;" name="delBtn" onclick="deleteMenu()"/>
				<br/><br/>
				<input type="button" value="新增" class="btn btn-info" style="width:80px;" name="addBtn" id="addBtn" onclick="toMenuSaveDiv()"/>
				<a  class="btn btn-info btn-small"  id="aaaaaa" href="#addDiv" role="button" data-toggle="modal" style="display: none;"/>
			</td>
			<td height="400px" align="center" valign="top">
				<form action="doMenuSave.do" method="post" name="treeForm" id="treeForm">
				<input type="hidden" name="treeData" id="treeData" value='${treeData}' />
   				<input type="hidden" name="parentId" id="parentId" value="" />
   				<input type="hidden" name="pageType" id="pageType" value="update" />
				<table class="table table-bordered">
					<tr>
						<td width="40%" align="right" bgcolor="#f1f1f1">菜单编号</td>
						<td><input type="text" name="menuId" id="menuId" class="span3" value="" readonly="readonly"/></td>
					</tr>
					<tr>
						<td width="40%" align="right" bgcolor="#f1f1f1">菜单名称</td>
						<td><input type="text" name="menuName" id="menuName" class="span3" value="" /></td>
					</tr>
					<tr>
						<td width="40%" align="right" bgcolor="#f1f1f1">菜单类型</td>
						<td>
							<select name="menuType" id="menuType" class="span3 required">
			        			<option value="M">菜单</option>
			          			<option value="B">按钮</option>
			        		</select>
						</td>
					</tr>
					<tr>
						<td width="40%" align="right" bgcolor="#f1f1f1">请求链接</td>
						<td><input type="text" name="reqUrl" id="reqUrl" class="span3" value="" /></td>
					</tr>
					<tr>
						<td width="40%" align="right" bgcolor="#f1f1f1">序号</td>
						<td><input type="text" name="orderSeq" id="orderSeq" class="span3" value="" /></td>
					</tr>
				</table>
				<table  class="margin-bottom-20  table no-border" >
			       <tr>
			     	<td class="text-center">
				     	<input type="submit" value="保存" class="btn btn-info  " style="width:80px;" name="saveBtn"/>
			     	</td>
			     </tr>
			   </table>
			   </form>
			</td>
		</tr>
	</table>
	
	
	<div id="addDiv" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:700px; margin-left:-450px; top:20%">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">新增</h3>
		</div>
		<div class="modal-body">
		 <table class="table table-bordered">
		  <tbody>
		    <tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">上级菜单</td>
				<td><input type="text" name="parentId1" id="parentId1" class="span3" value="" readonly="readonly"/></td>
			</tr>
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">菜单编号</td>
				<td><input type="text" name="menuId" id="menuId" class="span3" value="" /></td>
			</tr>
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">菜单名称</td>
				<td><input type="text" name="menuName" id="menuName" class="span3" value="" /></td>
			</tr>
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">菜单类型</td>
				<td>
					<select name="menuType" id="menuType" class="span3 required">
	        			<option value="M">菜单</option>
			          	<option value="B">按钮</option>
	        		</select>
				</td>
			</tr>
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">请求链接</td>
				<td><input type="text" name="reqUrl" id="reqUrl" class="span3" value="" /></td>
			</tr>
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">序号</td>
				<td><input type="text" name="orderSeq" id="orderSeq" class="span3" value="" /></td>
			</tr>
		  </tbody>
		</table>
		</div>
		<div class="modal-footer">
			 <button class="btn btn-info" data-dismiss="modal" aria-hidden="true" style="width:80px">保存</button> 
             <button class="btn btn-info" data-dismiss="modal" aria-hidden="true" style="width:80px">取消</button> 
		</div>
	</div>
	
  </div> 
  </div>
  </div>
</body>