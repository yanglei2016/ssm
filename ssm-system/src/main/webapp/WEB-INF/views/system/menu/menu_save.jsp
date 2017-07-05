<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/js/zTree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resourcesStyle/sysjs/system/menu/menu.js"></script>
</head>
<body>
     <div class="right"  id="mainFrame">
     <div class="right_cont">
  <ul class="breadcrumb">当前位置：
    <a href="#">系统管理</a> <span class="divider">/</span>
    <a href="menuTree.do">菜单管理</a><span class="divider">/</span>
	菜单新增
  </ul>
</ul>
   <div class="title_right"><strong>编辑数据参数</strong></div>
   <div style="width:100%; margin:auto">
   <form action="doMenuSave.do" method="post" name="myform" id="myform">
   <input type="hidden" name="parentId" id="parentId" value="${parentId}" />
       <table class="table table-bordered">
			<tr>
				<td width="40%" align="right" bgcolor="#f1f1f1">上级菜单</td>
				<td>[${parentId}]&nbsp;${parentMenuName}</td>
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
		</table>
	     <table  class="margin-bottom-20  table no-border" >
	       <tr>
	     	<td class="text-center">
		     	<input type="button" value="保存" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="doMenuSave()"/>
		     	<input type="button" value="关闭" class="btn btn-info  " style="width:80px;" name="backBtn" onclick="window.close()"/>
	     	</td>
	     </tr>
	   </table>
	 </form>
		</div>     
     </div>     
     </div>
</body>
</html>