package check.net.erp.base;

import com.alibaba.fastjson.JSONObject;

/**
 * 业务异常，指的是如果正常使用客户端访问时，是不会发生的。<br>
 *
 * @author Chen Wei
 *
 */
public class BizException extends RuntimeException {
	private static final long serialVersionUID = 177707884458471619L;

	private static final int DEFAULT_ERROR_CODE = 1;

	/**
	 * 错误码
	 */
	private int errorCode;

	/**
	 * 额外的信息
	 */
	private JSONObject errorExtra;

	public BizException() {
		this.errorCode = DEFAULT_ERROR_CODE;
	}

	public BizException(String message) {
		super(message);
		this.errorCode = DEFAULT_ERROR_CODE;
	}

	public BizException(String message, JSONObject errorExtra) {
		this(message);
		this.errorExtra = errorExtra;
	}

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public BizException(String message, int errorCode, Throwable cause) {
    	super(message, cause);
    	this.errorCode = errorCode;
    }

    public BizException(String message, int errorCode) {
    	this(message);
    	this.errorCode = errorCode;
    }

    // TODO 完善I18N
    public BizException(int errorCode) {
    	// this(Util.getI18nText(errorCode), errorCode);
    	this("未知错误");
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public JSONObject getErrorExtra() {
		return errorExtra;
	}

	public void setErrorExtra(JSONObject errorExtra) {
		this.errorExtra = errorExtra;
	}

}
