package check.net.erp.base;

import java.util.Map;

/**
 * 系统日志对象
 *
 * @author Chenw
 *
 */
public class SystemLog {

	/**
	 * 请求用户的IP
	 */
	private String ip;
	/**
	 * 请求目标
	 */
	private String target;

	private String fullIp;

	/**
	 * 执行时间
	 */
	private long executionTime;

	/**
	 * 请求参数
	 */
	private Map<String, Object> requestParamMap;

	/**
	 * 响应结果
	 */
	private Result responseResult;

	/**
	 * 错误堆栈
	 */
	private String stackTrace;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 完整IP
	 *
	 * @return
	 */
	public String getFullIp() {
		return fullIp;
	}

	public void setFullIp(String fullIp) {
		this.fullIp = fullIp;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public Map<String, Object> getRequestParamMap() {
		return requestParamMap;
	}

	public void setRequestParamMap(Map<String, Object> requestParamMap) {
		this.requestParamMap = requestParamMap;
	}

	public Result getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(Result responseResult) {
		this.responseResult = responseResult;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public String toString() {
		return "SystemLog [ip=" + ip + ", fullIp=" + fullIp + ", target=" + target + ", executionTime=" + executionTime + ", requestParamMap="
				+ JsonKit.toJson(requestParamMap) + ", responseResult=" + JsonKit.toJson(responseResult) + ", stackTrace=" + stackTrace + "]";
	}

}
