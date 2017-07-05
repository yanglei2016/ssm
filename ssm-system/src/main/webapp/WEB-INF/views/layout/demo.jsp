<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理系统</title>
<%@include file="/common/include.jsp" %>

<body>
     <div class="right"  id="mainFrame">
     <div class="right_cont">
<ul class="breadcrumb">当前位置：
  <a href="#">首页</a> <span class="divider">/</span>
  <a href="#">业务处理</a> <span class="divider">/</span>
  电脑开票
</ul>
   <div class="title_right"><strong>平安物流到货清单</strong></div>  
   <div style="width:100%; margin:auto">
       <table class="table table-bordered">
         <tr>
     <td width="10%" align="right" bgcolor="#f1f1f1">起站：</td>
     <td width="23%"><input type="text" name="input18" id="input18" class="span1-1" /></td>
     <td width="10%" align="right" bgcolor="#f1f1f1">到站：</td>
     <td width="23%"><input type="text" name="" id=""  class="span1-1"  /></td>
     <td width="10%" align="right" bgcolor="#f1f1f1">装车日期：</td>
     <td><input type="text"  class="laydate-icon span1-1" id="Calendar" value="2015-08-25"    /></td>
     </tr>
     <tr>
     <td align="right" bgcolor="#f1f1f1">货单号：</td>
     <td><input type="text" name="" id="" class="span1-1"  /></td>
     <td align="right" bgcolor="#f1f1f1">车牌号：</td>
     <td><input type="text" name="" id=""  class="span1" />
       <select name="select3" id="select3" class=" span1-1">
         <option>郑州</option>
       </select></td>
     <td colspan="2"><input type="radio" />
       全部&nbsp;&nbsp;&nbsp;&nbsp; 
         <input type="radio" />
         已提货&nbsp;&nbsp;&nbsp;&nbsp; 
         <input type="radio" />
         未提货</td>
     </tr>
     <tr>
       <td align="right" bgcolor="#f1f1f1">&nbsp;</td>
       <td colspan="5"><input type="radio" />
         全部&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="radio" />
          郑州一分公司&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" />
          分公司-郑州（分公司）</td>
       </tr>
       </table>
       <table  class="margin-bottom-20 table  no-border" >
        <tr>
     	<td class="text-center"><input type="button" value="确定" class="btn btn-info " style="width:80px;" /></td>
     </tr>
 </table>
       <table class="table table-bordered">
         <tr>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>序号</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>货单号</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>发货日期</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>起站</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>到站</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>发货人</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>收货人</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>收货人电话 </strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>货物名称</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>付款方式</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>运费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>送货费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>中转运费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>件数</strong></td>
         </tr>
         <tr>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
         </tr>
       </table>
      <table class="table table-bordered">
         <tr>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>重量</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>体积</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>包装</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>代收货款</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>返款</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>目的地</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>中转送货费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>保费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>运费合计</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>备注</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>分理处</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>提货方式</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>中转送货费</strong></td>
           <td align="center" nowrap="nowrap" bgcolor="#f1f1f1"><strong>中转费合计</strong></td>
         </tr>
         <tr>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
           <td align="center">&nbsp;</td>
         </tr>
       </table>
   <table class="table table-bordered">
  <tr>
    <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">现付：</td>
    <td width="23%"><input name="input" type="text" class="span1" id="input" value="0" />
      计<input name="input" type="text" class="span1" id="input" value="0" /> 
      张</td>
    <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">提付：</td>
    <td width="23%"><input name="input13" type="text" class="span1" id="input13" value="0" />
计
  <input name="input2" type="text" class="span1" id="input2" value="0" />
张</td>
    <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">回单付：</td>
    <td><input name="input14" type="text" class="span1" id="input14" value="0" />
计
  <input name="input3" type="text" class="span1" id="input3" value="0" />
张</td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">运费合计：</td>
    <td><input name="input12" type="text" class="span1" id="input12" value="0" />计
  <input name="input4" type="text" class="span1" id="input4" value="0" />
张</td>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">送货费：</td>
    <td><input name="input5" type="text" class="span1-1" id="input5" value="0" /></td>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">运费总合计：</td>
    <td><input name="input11" type="text" class="span1-1" id="input11" value="0" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap">代收款：</td>
    <td><input name="input6" type="text" class="span1-1" id="input6" value="0" /></td>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">返款：</td>
    <td><input name="input7" type="text" class="span1-1" id="input7" value="0" /></td>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">中转费用：</td>
    <td><input name="input10" type="text" class="span1-1" id="input10" value="0" /></td>
  </tr>
  <tr>
    <td align="right" nowrap="nowrap">中转送货费：</td>
    <td><input name="input8" type="text" class="span1-1" id="input8" value="0" /></td>
    <td align="right" nowrap="nowrap" bgcolor="#f1f1f1">中转费合计：</td>
    <td colspan="3"><input name="input9" type="text" class="span1-1" id="input9" value="0" /></td>
    </tr>
   </table>
   <table  class="margin-bottom-20 table  no-border" >
     <tr>
       <td class="text-center"><input type="button" value="打印" class="btn btn-info  margin-right-20" style="width:80px;" /><input type="button" value="数据导出" class="btn btn-info  " style="width:80px;" /></td>
     </tr>
   </table>
</div>
     <!-- 分页组件 -->
<nav>
  <ul class="pagination pager ">
  	<li class="pageInfo"><b>每页显示</b><input type="text" class="form-control" value="10">条</li>
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="current"><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <li class="jumpPage"><b>跳转到</b><input type="text" class="form-control">页<button class="btn btn-info  margin-right-20">确认</button></li>
  </ul>
</nav>
     
     </div>     
     </div>
</body>
</html>