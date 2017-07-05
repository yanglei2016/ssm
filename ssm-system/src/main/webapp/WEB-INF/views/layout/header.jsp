<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="header">
    <div class="logo">
        <img  src="${pageContext.request.contextPath }/resourcesStyle/img/logo.png" style="height: 50px; width: 200px;" />
    </div>
    <div class="header-right">
		 <i class="icon-question-sign icon-white"></i> 
		 <a href="#">帮助</a> 
		 <i class="icon-off icon-white"></i> 
		 <a id="modal-973558" href="#modal-container-973558" role="button" data-toggle="modal">注销</a> 
		 <i class="icon-user icon-white"></i> 
		 <a href="#">${sessionScope.USER_INFO.userId} / ${sessionScope.USER_INFO.userName}</a> 
		 <i class="icon-envelope icon-white"></i> <a href="#">发送短信</a>
		<div id="modal-container-973558" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:300px; margin-left:-150px; top:30%">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">
					注销系统
				</h3>
			</div>
			<div class="modal-body">
				<p>
					您确定要注销退出系统吗？
				</p>
			</div>
			<div class="modal-footer">
				 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> 
				 <a class="btn btn-primary" style="line-height:20px;" href="logout.do" >确定退出</a>
			</div>
		</div>
	</div>
</div>
<!-- 顶部 -->
