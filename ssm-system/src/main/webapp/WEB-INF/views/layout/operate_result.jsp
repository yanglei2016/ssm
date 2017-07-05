<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/include.jsp" %>
<script type="text/javascript">
function showException(){
	var display =$('#exceptionDetailId').css('display');
	if(display == 'none'){
		$("#exceptionDetailId").show();
	}else{
		$("#exceptionDetailId").hide();
	}
}
</script>
</head>
<body>
<div class="right"  id="mainFrame">
	<div class="right_cont">
		<ul class="breadcrumb">当前位置：<a href="#">操作结果</a> <span class="divider">/</span>结果信息</ul>
		<div class="title_right"><strong>操作结果</strong></div>
		<div style="width:60%; margin:auto">
			<table class="table table-bordered" >
		        <tr style="height: 60px;">
				     <td width="30%" align="right" bgcolor="#f1f1f1"><strong>信息提示</strong>&nbsp;</td>
				     <td>
				     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 	<c:if test="${'success' eq code }">${message}</c:if>
					 	<c:if test="${'success' ne code }"><a style="color: red;">${message }</a></c:if>
				     </td>
				</tr>
			</table>
			
			<table class="margin-bottom-20  table no-border">
				<tr>
			     	<td class="text-center">
			     		<c:if test="${'success' eq code }">
				     	<input type="button" value="确定" class="btn btn-info  " style="width:80px;" name="saveBtn" onclick="location.href='${redirectUrl}'" />
				     	</c:if>
				     	<c:if test="${'success' ne code }">
				     		<!-- 失败有返回按钮 -->
				     		<input type="button" value="错误明细" class="btn btn-info" style="width:80px;" name="backBtn" onclick="showException()" />
				     		
				     		<input type="button" value="返回" class="btn btn-info  " style="width:80px;" name="backBtn" onclick="history.back()" />
				     	</c:if>
			     	</td>
				</tr>
			</table>
	   		
	   		<!-- 操作失败 ，异常详情-->
	   		<table class="table table-bordered" id="exceptionDetailId" style="display: none">
				<tr align="center" bgcolor="#f1f1f1">
				     <td><strong>错误详细信息</strong></td>
				</tr>
				<tr>
				     <td>
				     	<a style="color: red;">${exceptionDetail}</a>
				     </td>
				</tr>
			</table>
		</div>     
	</div>     
</div>
</body>
</html>