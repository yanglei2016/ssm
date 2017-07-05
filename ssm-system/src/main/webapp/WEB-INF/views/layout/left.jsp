<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/include.jsp" %>
<script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>
<div class="left">
	<div id="my_menu" class="sdmenu">
		<!-- 
		<div class="collapsed">
			<span>用户管理</span>
			<a href="javascript:void(0)" onclick="clickMenu('system/user/systemUserList.do')">用户列表</a>
			<a href="javascript:void(0)" onclick="clickMenu('')">用户权限</a>
		</div>
		<div class="collapsed">
			<span>系统管理</span>
			<a href="javascript:void(0)" onclick="clickMenu('')">日志查询</a>
		</div>
		 -->
		<c:if test="${not empty leftMenuList}">
			<c:forEach items="${leftMenuList}" var="menu" varStatus="index">
				<c:if test="${menu.menuLevel == 1}">
					<c:if test="${!index.first}">
						</div>
					</c:if>
					<div class="collapsed">
						<span>${menu.menuName }</span>
				</c:if>
				<c:if test="${menu.menuLevel == 2}">
					<!-- 根目录不展示 -->
					<c:if test="${menuBean.menuId ne '0' }">
						<a href="javascript:void(0)" onclick="clickMenu('${menu.reqUrl}')" >${menu.menuName }</a>
					</c:if>
					<c:if test="${index.last}">
						</div>
					</c:if>
				</c:if>
			</c:forEach>
		</c:if>
	</div>
</div>
