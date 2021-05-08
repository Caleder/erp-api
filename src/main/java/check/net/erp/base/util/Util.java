package check.net.erp.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import check.net.erp.base.BizException;
import check.net.erp.base.JsonKit;
import check.net.erp.base.Logger;
import check.net.erp.base.StrKit;

public class Util {
	private static Logger logger = Logger.getLogger(Util.class);

	public static Random random = new Random();

	public static final SimpleDateFormat serialNumberFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static final int SORT_ASC = 0;
	public static final int SORT_DESC = 1;

	/**
	 * 获取应用基址
	 *
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String servletPath = request.getServletPath();

		String baseUrl = request.getRequestURL().toString();

		if (!servletPath.equals("/")) {
			baseUrl = baseUrl.replaceAll(servletPath, "");
		}

		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}

		return baseUrl;
	}

	/**
	 * 组合请求字符串
	 *
	 * @return
	 */
	public static String createParamsStr(Map<String, Object> dataObj) {
		if (dataObj == null) {
			return "";
		}

		Set<String> keySet = dataObj.keySet();
		if (keySet.size() == 0) {
			return "";
		}

		String paramsStr = "";
		for (String key : keySet) {
			paramsStr += key + "=" + dataObj.get(key) + "&";
		}
		paramsStr = paramsStr.substring(0, paramsStr.length() - 1);

		return paramsStr;
	}

    /**
     * 获取访问的域名
     *
     * @param request
     * @return
     */
	public static String getDomain(HttpServletRequest request) {
	    return request.getServerName();
    }

	/**
	 * 对json串进行格式化
	 *
	 * @param jsonString
	 * @return
	 */
	public static String formatJson(String jsonString) {
		return JSON.toJSONString(JSON.parse(jsonString), true);
	}

	/**
	 * 拼装缓存键值
	 *
	 * @param cachePrefix
	 * @param identifiers
	 * @return
	 */
	public static String createCacheKey(String cachePrefix, Object... identifiers) {
		String cacheKey = cachePrefix;
		for (Object identifier : identifiers) {
			cacheKey += "_" + identifier;
		}

		return cacheKey;
	}

	/**
	 * 创建in子句，包括()
	 *
	 * @param idList
	 * @return
	 */
	public static String createSqlIn(List<Long> idList) {
		if (idList == null || idList.size() == 0) {
			return "(0)";
		}

		String inStr = "(";
		for (long id : idList) {
			inStr += id + ",";
		}
		inStr = inStr.substring(0, inStr.length() - 1);

		return inStr + ")";
	}

	/**
	 * 为String列表创建in子句
	 *
	 * @param strList
	 * @return
	 */
	public static String createSqlInForStr(List<String> strList) {
		if (strList == null || strList.size() == 0) {
			return "('')";
		}

		StringBuilder inStr = new StringBuilder("(");
		for (String str : strList) {
			inStr.append("'").append(str).append("',");
		}

		return inStr.substring(0, inStr.length() - 1) + ")";
	}

	/**
	 * list转为str，中间用,分隔
	 *
	 * @param list
	 * @return
	 */
	public static String strListToString(List<String> list) {
		if (list == null || list.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer("");
		for (Object item : list) {
			sb.append(item + ",");
		}

		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * list转为str，中间用,分隔
	 *
	 * @param list
	 * @return
	 */
	public static <T> String listToString(List<T> list) {
		if (list == null || list.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer("");
		for (T item : list) {
			sb.append(item + ",");
		}

		return sb.toString().substring(0, sb.length() - 1);
	}

	/**
	 * 将字符串转为int list。（此串各项用,分隔）
	 *
	 * @param itemStr
	 * @return
	 */
	public static List<Integer> strToIntList(String itemStr) {
		List<Integer> itemList = new ArrayList<Integer>();
		if (itemStr == null || itemStr.length() == 0) {
			return itemList;
		}

		String[] itemArray = itemStr.split(",");
		for (String item : itemArray) {
			itemList.add(Integer.parseInt(item.trim()));
		}

		return itemList;
	}

	/**
	 * 将字符串转为long list。（此串各项用,分隔）
	 *
	 * @param itemStr
	 * @return
	 */
	public static List<Long> strToLongList(String itemStr) {
		List<Long> itemList = new ArrayList<Long>();
		if (itemStr == null || itemStr.length() == 0) {
			return itemList;
		}

		String[] itemArray = itemStr.split(",");
		for (String item : itemArray) {
			itemList.add(Long.parseLong(item.trim()));
		}

		return itemList;
	}

	/**
	 * 将字符串转为string list。（此串各项用,分隔）
	 *
	 * @param itemStr
	 * @return
	 */
	public static List<String> strToStrList(String itemStr) {
		List<String> itemList = new ArrayList<String>();
		if (itemStr == null || itemStr.length() == 0) {
			return itemList;
		}

		String[] itemArray = itemStr.split(",");
		for (String item : itemArray) {
			itemList.add(item.trim());
		}

		return itemList;
	}

	/**
	 * 将字符串转为string set。（此串各项用,分隔）
	 *
	 * @param itemStr
	 * @return
	 */
	public static Set<String> strToStrSet(String itemStr) {
		Set<String> itemSet = new HashSet<String>();
		if (itemStr == null || itemStr.length() == 0) {
			return itemSet;
		}

		String[] itemArray = itemStr.split(",");
		for (String item : itemArray) {
			itemSet.add(item.trim());
		}

		return itemSet;
	}

	/**
	 * 过期日期
	 *
	 * @param expireAfterSeconds
	 */
	public static Date getExpireDate(long expireAfterSeconds) {
		return new Date(System.currentTimeMillis() + (expireAfterSeconds * 1000L));
	}

	/**
	 * 产生随机UUID，不带"-"
	 *
	 * @return
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取文件的扩展名
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			throw new BizException("没有扩展名");
		}

		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	// ============================

	/**
	 * 获取客户端真实IP
	 *
	 * @param request
	 * @return
	 */
	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if(StrKit.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}

		ip = request.getHeader("X-Real-IP");
		if(StrKit.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}

		return request.getRemoteAddr();
	}

	/**
	 * 拆分请求参数
	 *
	 * @param queryParas
	 *            形如a=b&c=d
	 * @return
	 */
	public static Map<String, String> splitQueryParas(String queryParas) {
		Map<String, String> queryParaMap = new HashMap<String, String>();
		if (StrKit.isBlank(queryParas)) {
			return queryParaMap;
		}

		String[] nameValuePairs = queryParas.split("&");
		for (String nameValuePair : nameValuePairs) {
			String[] nameValueArray = nameValuePair.split("=");
			if (nameValueArray.length != 2) {
				// throw new IllegalArgumentException("queryParas is wrong");
				continue;
			}

			queryParaMap.put(nameValueArray[0].trim(), nameValueArray[1].trim());
		}

		return queryParaMap;
	}

	/**
	 * 将jsonArray字符串转为Long类型的列表
	 *
	 * @param arrayStr
	 * @return
	 */
	public static List<Long> jsonArrayToLongList(String arrayStr) {
		JSONArray array = JSONArray.parseArray(arrayStr);
		List<Long> list = new ArrayList<Long>();
		for (int index = 0; index < array.size(); index++) {
			list.add(array.getLong(index));
		}

		return list;
	}

	/**
	 * 计算页码
	 *
	 * @param offset
	 * @param limit
	 * @return
	 */
	public static int getPageNumber(int offset, int limit) {
		return (offset / limit) + 1;
	}

	/**
	 * 计算开始位置
	 *
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static int getOffset(int pageNumber, int pageSize) {
		return (pageNumber - 1) * pageSize;
	}

	/**
	 * 计算总页数
	 *
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
	public static int getTotalPage(int pageSize, int totalCount) {
		return totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
	}

	/**
	 * 尝试通过get调用接口
	 *
	 * @param apiUrl
	 * @param paramMap
	 * @param totalRetryCount
	 *            重试次数
	 * @return
	 */
	public static JSONObject tryGetJson(String apiUrl, Map<String, Object> paramMap, int totalRetryCount) {
		int retriedCount = 1;
		Map<String, String> params = new HashMap<String, String>();
		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			params.put(key, paramMap.get(key) + "");
		}
		while (true) {
			try {
				logger.info("正在进行第[" + retriedCount + "]次调用[" + apiUrl + "], 参数：" + JsonKit.toJson(paramMap));
				String response = HttpKit.get(apiUrl, params);
				logger.info(response);
				return JSONObject.parseObject(response);

			} catch (Exception e) {
				e.printStackTrace();
				retriedCount++;

				if (retriedCount > totalRetryCount) {
					throw new BizException("尝试调用接口[" + apiUrl + "]超过" + totalRetryCount + "次，请检查网络环境");
				}
			}
		}
	}

	/**
	 * 创建序列号
	 *
	 * @param prefix
	 *            前缀
	 * @param appendix
	 *            后缀
	 * @return
	 */
	public static String createSerialNumber(String prefix, String appendix) {
		String serialNumber = serialNumberFormatter.format(new Date());

		if (StrKit.notBlank(prefix)) {
			serialNumber = prefix + serialNumber;
		}
		if (StrKit.notBlank(appendix)) {
			serialNumber = serialNumber + appendix;
		}

		return serialNumber;
	}

	/**
	 * 把金额由分变为元
	 *
	 * @return
	 */
	public static double changeAmountToYuan(long fen) {
		return fen / 100.00d;
	}

	public static long changeAmountToFen(double yuan) {
		return (long) (yuan * 100L);
	}

	/**
	 * 获取json格式的头部
	 *
	 * @return
	 */
	public static Map<String, String> getJsonTypeHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");

		return headers;
	}


	/**
	 * 重试3次
	 *
	 * @param url
	 * @param data
	 * @param headers
	 * @return
	 */
	public static String tryDoPost(String url, Map<String, String> params, String data, Map<String, String> headers) {
		for (int i = 0; i < 3; i ++) {
			String resp = tryOnePost(url, params, data, headers);
			if (StrKit.notBlank(resp)) {
				return resp;
			}
		}

		return null;
	}

	/**
	 * 重试3次
	 *
	 * @param url
	 * @param headers
	 * @return
	 */
	public static String tryDoGet(String url, Map<String, String> params, Map<String, String> headers) {
		for (int i = 0; i < 3; i ++) {
			String resp = tryOneGet(url, params, headers);
			if (StrKit.notBlank(resp)) {
				return resp;
			}
		}

		return null;
	}

	private static String tryOnePost(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {
		String resp = null;
		try {
			resp = HttpKit.post(url, queryParas, data, headers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	private static String tryOneGet(String url, Map<String, String> params, Map<String, String> headers) {
		String resp = null;
		try {
			resp = HttpKit.get(url, params, headers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

}
