package check.net.erp.base;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILURE = 1;
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_FAILURE = "failure";
    public static final String ERROR_MESSAGE_UNKNOWN_EXCEPTION = "未知错误";
    private boolean success;
    private String message;
    private int code;
    private Object data;

    public Result() {
        this.success = true;
        this.message = "success";
        this.code = 0;
    }

    public Result(Object data) {
        this(true, 0, "success", data);
    }

    public Result(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = StrKit.notBlank(message) ? message : "未知错误";
    }

    public Result(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "Result [success=" + this.success + ", message=" + this.message + ", code=" + this.code + ", data=" + this.data + "]";
    }
}
