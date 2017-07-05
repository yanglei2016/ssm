<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${not empty resultList}">
<c:if test="${not empty parameterMap.PAGE_PARAMS}">
<form id="page_plug_in_form" name="page_plug_in_form" action="#">
<!-- 查询参数  start-->
<c:if test="${not empty parameterMap }">
	<c:forEach var="parameter" items="${parameterMap}">   
		<input type="hidden" name="${parameter.key }" value="${parameter.value }" />
	</c:forEach>
</c:if>
<!-- 查询参数 end-->


<nav>
  <ul class="pagination pager ">
  <!-- 分页对象 -->
  <c:set var="page" value="${parameterMap.PAGE_PARAMS }" />
  <!-- 分页条显示的页码数量 -->
  <c:set var="showCount" value="5" />
  <!-- 当前页 -->
  <c:set var="currentPage" value="${page.currentPage }" />
  <c:if test="${currentPage<0 }">
 	<c:set var="currentPage" value="0" />
  </c:if>
  <li class="pageInfo"><b>总记录</b>${page.count }<b>条</b></li>
  <li class="pageInfo"><b>每页显示</b><input type="text" name="pageSize" id="pageSize" class="form-control" value="${page.pageSize }"><b>条</b></li>
  <li><a href="#" aria-label="Previous" onclick="setPage('1')"><span aria-hidden="true">首页</span></a></li>
  <c:if test="${page.totalPage > 1 }">
  	<li><a href="#" aria-label="Previous" onclick="setPage('${currentPage}')"><span aria-hidden="true">上一页</span></a></li>
  </c:if>
  <c:set var="startPage" value="1" />
  <c:set var="endPage" value="${showCount}" />
  <c:choose>
  	<c:when test="${currentPage<3}">
  		<c:set var="startPage" value="1"/>
  	</c:when>
  	<c:when test="${currentPage>=3}">
  		<c:set var="startPage" value="${currentPage-1}"/>
  		<c:set var="endPage" value="${currentPage+3}"/>
  	</c:when>
  </c:choose>

  <c:forEach begin="${startPage }" end="${endPage }" varStatus="co">
  	<c:if test="${co.index <= page.totalPage }">
  		<c:if test="${currentPage+1 eq co.index }">
  			<li class="current"><a href="javascript:void(0)" onclick="setPage('${co.index}')">${co.index }</a></li>
  		</c:if>
  		<c:if test="${currentPage+1 ne co.index }">
  			<li><a href="javascript:void(0)" onclick="setPage('${co.index}')">${co.index }</a></li>
  		</c:if>
  	</c:if>
  </c:forEach>
   <c:if test="${endPage <= page.totalPage }">
   		<li><a href="javascript:void(0)" aria-label="Next" onclick="setPage('${currentPage+2}')"><span aria-hidden="true">下一页</span></a></li>
   </c:if>
    
  	<li><a href="#" aria-label="Previous" onclick="setPage('${page.totalPage }')"><span aria-hidden="true">尾页</span></a></li>
    <li class="jumpPage"><b>跳转到</b>
    <input type="hidden" name="totalPage" id="totalPage" value="${page.totalPage }" />
    <input type="text" name="currentPage" id="currentPage" class="form-control" value="${currentPage+1 }" />页<button class="btn btn-info  margin-right-20" onclick="submitPage();">确认</button>
    
    </li>
  </ul>
</nav>
</form>
<script type="text/javascript">
// 选择分页
function setPage(currentPage){
	if (flagPage(currentPage)){
		$("#currentPage").val(currentPage);
		submitPage();
	}
}

// 跳转页面
function submitPage(){
	if (flagPage($("#currentPage").val())){
		page_plug_in_form.submit();
	}
}
// 判断页面
function flagPage(currentPage){
	var result = true;
	if (parseInt(currentPage) < 1){
		result = false;
		alert("跳转页数小于【1】页");
	}else if (parseInt(currentPage) > parseInt($("#totalPage").val())){
		result = false;
		alert("跳转页数不能大于最大【"+$("#totalPage").val()+"】页");
	}else {
		result = true;
	}
// 	alert(currentPage + " totalPage=" + $("#totalPage").val() + "result=" + result );
	return result;
}
</script>
</c:if>  
</c:if> 
