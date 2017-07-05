//package com.yang.ssm.utils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//
//import com.iafclub.batchJob.remote.contans.SystemContants;
//import com.iafclub.batchJob.remote.entity.MenuVo;
//import com.iafclub.batchJob.remote.entity.UserVo;
//import com.opensymphony.oscache.util.StringUtil;
//
//public class SysAuthUtils {
//	
//	//	 日志对象
//	private static Logger logger = Logger.getLogger(SysAuthUtils.class);
//	
//	/**根据按钮编号<br />
//	 * 判断当前用户是否有权执行某按钮的某操作
//	 * 
//	 * @param request
//	 * @param operateNo 菜单元素ID
//	 * @param allMenuVos 所有
//	 * @return
//	 * @author 陈惟鲜
//	 * @date 2016年3月30日 上午10:44:36
//	 */
//	public static boolean flagIsAllowAccessByNo(HttpServletRequest request, String operateNo, List<MenuVo> allMenuVos) {
//		// 不设置key\value默认为有权限
//		if (StringUtil.isEmpty(operateNo)) {
//			return true;
//		}
//
//		HttpSession session = request.getSession();
//		
//		// 当前登录人信息
//		UserVo userVo = (UserVo)session.getAttribute(SystemContants.USER_INFO);
//
//		// 未登陆
//		if ( userVo == null) {
//			return true;
//		}
//		
//		// 获取当前用户所拥有的权限集合
//		List<MenuVo> userMenuVos = userVo.getMenuVos();
//		// 拆分标签
//		String[] operateNoArr = operateNo.split(";");
//		
////		 传递参数true，那么当session过期时，新的session被创建，接下来可通过session.isNew()的返回值来判断是不是同一个session
//		// 返回值为：true，新的session被创建，action提交执行时的那个用户session已经无效，报异常：java.lang.IllegalStateException:
//		// getAttribute: Session already invalidated
//		// 返回值为：false，同一个session，仍然有效，
//		if (session.isNew()) {
//			return false;
//		}
//
//		List<String> allAccessUrls = obj2Str(allMenuVos);
//		List<String> useAccessUrls = obj2Str(userMenuVos);
//		
//		 // 判断是否能访问
//		if (allMenuVos == null || allMenuVos.size() == 0)
//		{
//			return true;
//		}
//		 //	所有编号中判断是否有控制这个权限,true 有;false 没有
//		 if (!isSetAllow(operateNoArr, allAccessUrls)) {
//			return true;
//		 }
//		 // 当前用户权限是否存在
//		 if (useAccessUrls == null || useAccessUrls.size() == 0)
//		 {
//			 return false;
//		 }
//		 // 加了权限控制 判断权限是否在当前用户权限内
//		 if(isSetAllow(operateNoArr, useAccessUrls))
//		 {
//			return true;
//		 }
//		return false;
//	}
//	
//	
//	/**根据按钮编号<br />
//	 * 判断当前用户是否有权执行某按钮的某操作
//	 * 
//	 * @param request
//	 * @param allMenuVos
//	 * @return
//	 * @author 陈惟鲜
//	 * @date 2016年3月30日 上午10:44:46
//	 */
//	public static boolean flagIsAllowAccessByUri(HttpServletRequest request,String requestUri, List<MenuVo> allMenuVos) {
//		// 所有菜单如果为空，则有权限
//		if (allMenuVos == null) {
//			return true;
//		}
//		HttpSession session = request.getSession();
//		
//		// 当前登录人信息
//		UserVo userVo = (UserVo)session.getAttribute(SystemContants.USER_INFO);
//
//		// 未登陆
//		if ( userVo == null || StringUtil.isEmpty(userVo.getUserId()) || userVo.getRoleVos().size() == 0) {
//			return true;
//		}
//		
//		// 获取当前用户所拥有的权限集合
//		List<MenuVo> menuVos = userVo.getMenuVos();
//		
////		 不设置key\value默认为有权限
//		if (StringUtil.isEmpty(requestUri)) {
//			return true;
//		}
////		 传递参数true，那么当session过期时，新的session被创建，接下来可通过session.isNew()的返回值来判断是不是同一个session
//		// 返回值为：true，新的session被创建，action提交执行时的那个用户session已经无效，报异常：java.lang.IllegalStateException:
//		// getAttribute: Session already invalidated
//		// 返回值为：false，同一个session，仍然有效，
//		if (session.isNew()) {
//			logger.error("session.isNew()是新的");
//			return false;
//		}
//		
//		// 判断 获取所有的菜单权限连接集合 是否控制uri
//		Set<String> allUrlSet = getMenuAndOperateUrlSet(allMenuVos);
//		// 判断当前登陆用户是否 有权访问uri
//		Set<String> userUrlSet = getMenuAndOperateUrlSet(menuVos);
//		if (collectionIsContainsUri(request, requestUri, allUrlSet, userUrlSet))
//		{
//			return true;
//		}
//		
//		return false;
//	}
//	
//	/***
//	 * 判断数组中的元素是否有存在集合中
//	 * @param operateNoArr 多个权限编号
//	 * @param allowSet 集合
//	 * @return true 有; false 没有
//	 */
//	private static boolean isSetAllow(String[] operateNoArr, List<String> allowSet) {
//		// 判断所有标签编号中是否包含有这个权限编号
//		boolean flag = false;// 没有
//		for (String s : operateNoArr) {
//			// 权限编号不为空并且 集合包含权限编号
//			if (!StringUtil.isEmpty(s) &&  allowSet.contains(s)) {
//				flag = true;
//				break;
//			}
//		}
//		return flag;
//	}
//	
//	/**权限集合对象转换未字符串集合
//	 * 
//	 * @param oprateInfoDTOSet
//	 * @return
//	 */
//	private static List<String> obj2Str(List<MenuVo> menuVos)
//	{
//		List<String> list = new ArrayList<String>();
//
//		if (menuVos != null)
//		{
//			for (MenuVo dto : menuVos)
//			{
//				if (dto == null || StringUtil.isEmpty(dto.getMenuId()))
//				{
//					continue;
//				}
//				list.add(dto.getMenuId());
//			}
//		}
//		return list;
//	}
//	
//	/*************************************************************新控制***************************************************************************/
//	/**获取菜单和权限连接中的url集合
//	 * 
//	 * @param menuInfoDTOList
//	 * @param oprateInfoDTOList
//	 * @return
//	 */
//	private static Set<String> getMenuAndOperateUrlSet(Collection<MenuVo> menuInfoDTOCollection)
//	{
//		Set<String> tempSet = new HashSet<String>();
//		// 菜单加入集合
//		if (menuInfoDTOCollection != null && menuInfoDTOCollection.size() > 0)
//		{
//			for (MenuVo dto : menuInfoDTOCollection)
//			{
//				if (dto == null || StringUtil.isEmpty(dto.getHttpurl()))
//				{
//					continue;
//				}
//				tempSet.add(dto.getHttpurl());
//			}
//		}
//		return tempSet;
//	}
//	
//	/**判断用户权限是否存在该uri
//	 * 
//	 * @param request
//	 * @param requestUri 请求连接
//	 * @param allUrlSet
//	 * @param collection
//	 * @return
//	 */
//	private static boolean collectionIsContainsUri(HttpServletRequest request, String requestUri, Collection<String> allUrlCollection, Collection<String> userCollection)
//	{
//		// 如果所有链接为空 ,可以任意访问
//		if (allUrlCollection == null || allUrlCollection.size() == 0)
//		{
//			return true;
//		}
//		
//		//	如果用户拥有链接集合为空 
//		if (userCollection == null || userCollection.size() == 0)
//		{
//			return false;
//		}
//
//		// 所有链接中是否包含uri
//		Set<String> allUrlSet = getCollectionAboutList(requestUri, allUrlCollection);
//		// 不包含这个uri，这个uri不在控制范围内
//		if (allUrlSet.size() == 0)
//		{
//			return true;
//		}
//		// 当前用户权限是否包含这个链接
//		Set<String> userUrlSet = getCollectionAboutList(requestUri, userCollection);
//		// 如果不包含，则无权访问
//		if (userUrlSet.size() == 0)
//		{
//			return false;
//		}
//		
//		//控制的url是否存在参数
//		Map<String, List<String>> mapResult = list2OperateMap(userUrlSet);
//		if (mapResult.size() == 0)
//		{
//			return true;
//		}
//		
//		// 判断参数是否在集合中
//		boolean paramFlag = parametersIsInRequest(request, mapResult, userUrlSet, requestUri);
//		if (paramFlag)
//		{
//			return true;
//		}
//
//		// 如果是无参数链接,用户权限链接是否包含这个无参数的链接
////		boolean userUrlContainsUriFlag = userUrlSet.contains(requestUri);
////		if (userUrlContainsUriFlag)
////		{
////			return true;
////		}
//		
//		return false;
//	}
//	
//	/**
//	 * 返回oprateInfoDTOList中包含有uri相关的对象集合
//	 * @param uri 请求连接
//	 * @param oprateInfoDTOList 当前用户所拥有的所有权限集合
//	 * @return
//	 */
//	private static Set<String> getCollectionAboutList(String uri, Collection<String> collection)
//	{
//		Set<String> tempSet = new HashSet<String>();
//		if (collection != null && collection.size() > 0)
//		{
//			for (String str : collection)
//			{
//				if (str.contains(uri))
//				{
//					tempSet.add(str);
//				}
//			}
//		}
//		
//		return tempSet;
//	}
//	
//	/**返回当前链接中uri对应的参数
//	 * 
//	 * @param someUrlSet
//	 * @return
//	 */
//	private static Map<String, List<String>> list2OperateMap(Set<String> someUrlSet)
//	{
//		Map<String, List<String>> mapResult = new HashMap<String, List<String>>();
//		// 初始化加载信息;
//		for (String urlString : someUrlSet)
//		{
//			splitUrl(mapResult, urlString);
//		}
//		
//		return mapResult;
//	}
//	
//	/**判断参数是否在request中
//	 * 
//	 * @param request
//	 * @param mapResult
//	 * @return
//	 */
//	private static boolean parametersIsInRequest(HttpServletRequest request, Map<String, List<String>> mapResult, Set<String> userUrlSet, String requestUri)
//	{
//		//  验证结果
//        boolean validateFlag = true;
//		// request中包含的参数集合 请求参数集合
//        Map<String, String> requestMap = new HashMap<String, String>();
//        
//        for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
//            String name = (String) e.nextElement();
//            String value = request.getParameter(name);
//            // 根据传递过来的参数请求获取权限控制的url参数
//            List<String> allowValueList = mapResult.get(name);
//            if (allowValueList != null && allowValueList.size() > 0)
//            {
//            	// 如果存在要控制的参数
//                requestMap.put(name, value);
//            	if (!allowValueList.contains(value))
//            	{
//            		validateFlag = false;
//            	}
//            }
//        }
//        // 当前是无参数链接请求，拥有权限包含请求uri
//        if (requestMap.size() == 0 && userUrlSet.contains(requestUri))
//        {
//        	validateFlag = true;
//        }
//        
//		return validateFlag;
//	}
//	
//	/**
//	 * 拆分url
//	 * @author chen_weixian
//	 * Jul 21, 2012   6:09:33 PM
//	 * @param mapResult Map<String, String[]>=Map<paraName, paraValues>
//	 * @param urlString 
//	 * @return
//	 */
//	private static void splitUrl(Map<String, List<String>> mapResult, String urlString)
//	{
//		if (urlString.indexOf("?") == -1){
//			return ;
//		}
//		// 根据问号进行拆分
//		String[] urlArr = urlString.split("\\?");
//		// 如果存在参数
//		if (urlArr.length > 1)
//		{
//			// 根据 & 拆分有多少参数
//			String[] paramArray = urlArr[1].split("&");
//			// 如果存在参数
//			if (paramArray.length > 0)
//			{
//				for (String paraAndValue : paramArray)
//				{
//					addParaToMap(mapResult, paraAndValue);
//				}
//			}
//		}
//	}
//	
//	/**将参数与值放入到map中
//	 * 
//	 * @param mapResult
//	 * @param paraAndValue
//	 */
//	private static void addParaToMap(Map<String, List<String>> mapResult, String paraAndValue)
//	{
////		 参数名
//		String paraName = null;
//		// 参数值
//		String paraValue = null;
//		if (paraAndValue.contains("="))
//		{
//			String[] para= paraAndValue.split("=");
//			if (para.length > 0)
//			{
//				paraName = para[0];
//			}
//			if (para.length > 1)
//			{
//				paraValue = para[1];
//			}
//		}
//		// 获取集合中参数的值集合
//		List<String> tempList = mapResult.get(paraName);
//		if (tempList == null)
//		{
//			tempList = new ArrayList<String>();
//		}
//		tempList.add(paraValue);
//		// 参数加入到集合中
//		mapResult.put(paraName, tempList);
//	}
//	
//	
//}
