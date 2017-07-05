/**
 * JQuery Ajax 请求封装
 * 
 * @param url		:调用后台程序的链接地址
 * @param param		:参数 json格式{key1:"value1", key2:"value2", key3:value3}
 * @param method	:调用成功后执行的方法名称
 * @param async		：同步还是异步方式，false为同步，true为异步，默认为异步
 * 
 */
function juqeryAjaxRequest(url, param, method, async){
	if(async == false){ //同步处理
		$.ajaxSetup({
			async:false
		});
	}
	
	$.post(url, param, 
		function(resultMsg){
			method = method + "(resultMsg, param);";
			eval(method);
		}
	);
	$(this).ajaxError(function(request, settings){
		if(settings.status == "500"){
			$(this).remove();
			alert("加载页面信息失败！");
			return ;
		}
		else if(settings.status == "404"){
			$(this).remove();
			alert("找不到要加载信息的页面！");
			return ;
		}else{
			$(this).remove();
			alert("未知错误，请联系管理员！");
			return ;
		}
	});
}

/**
 * window.open封装，增加自动计算页面位置和大小功能
 * 
 * @param	url为window.open的sURL参数
 * @param	features为window.open的sFeatures参数
 */
function windowOpen(url, features){
	var iWidth = window.screen.availWidth * 0.6;      			//弹出窗口的宽度
    var iHeight = window.screen.availHeight * 0.7;    			//弹出窗口的高度
    var iTop = (window.screen.availHeight - 40 - iHeight) / 2;  //获得窗口的垂直位置 
    var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 	//获得窗口的水平位置 
    window.open(url, '', features +',left='+ iLeft +',top='+ iTop +',width='+ iWidth +',height='+ iHeight);
}

function mouseOver(obj){
	$(obj).addClass("mouse_color");
}
function mouseOut(obj){
	$(obj).removeClass("mouse_color");
}

/**
 * checkbox全选/全不选公共方法
 * 
 * @param	ctrlId	控制框id值
 * @param	boxName	备选框name属性值
 */
function selectAllBox(ctrlId, boxName){
	var status = $("#"+ ctrlId).prop("checked");
	$('[name="'+ boxName +'"]:checkbox').each(function(){
		$(this).attr("checked", status);
	});
}