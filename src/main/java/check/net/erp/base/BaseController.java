//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package check.net.erp.base;

import check.net.erp.base.util.BeanUtil;
import check.net.erp.base.util.Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {

    private static Logger logger = Logger.getLogger(BaseController.class);

    @Value("${erp.devMode}")
    private boolean devMode;

    public JSONObject __paramObj;

    private HttpServletRequest request;

    public BaseController() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = attributes.getRequest();

        System.out.println("\n-------------------------------------------request start-------------------------------------------");
        System.out.println("request ip: " + Util.getRealIp(request));
        System.out.println("user agent: " + request.getHeader("User-Agent"));

        String body = readBody(request);
        if (StrKit.isBlank(body)) {
            __paramObj = new JSONObject();

        } else {
            __paramObj = JSONObject.parseObject(body);
        }


        System.out.println("******************请求参数开始******************");
        System.out.println("=======[" + request.getRequestURI() + "]=======");

        System.out.println(JSON.toJSONString(__paramObj, true));
        System.out.println("******************请求参数结束******************");
    }

    public <T extends BaseCommand> Result doAction(Class<T> Clazz) {
        long startTime = System.currentTimeMillis();

        String stackTrace = "";
        Result result = null;
        try {
            result = BeanUtil.getBean(Clazz).doAction(__paramObj);

        } catch (BizException ex) {
            ex.printStackTrace();
            result = new Result(false, ex.getErrorCode(), ex.getMessage());
            stackTrace = printStackTrace(ex);

        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, Result.CODE_FAILURE, devMode ? e.getMessage() : Result.ERROR_MESSAGE_UNKNOWN_EXCEPTION);
            stackTrace = printStackTrace(e);

        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            SystemLog log = new SystemLog();
            log.setIp(Util.getRealIp(request));
            log.setFullIp(getRealIp(request));
            log.setTarget(request.getRequestURI());
            log.setRequestParamMap(__paramObj);
            log.setResponseResult(result);
            log.setExecutionTime(executionTime);
            log.setStackTrace(stackTrace.replace("\n","!br!").replace("\t"," "));
            logger.info(log.toString());

            System.out.println("本次请求[" + request.getRequestURI() + "]处理时间：[" + (endTime - startTime) + "]毫秒");
            System.out.println("\n-------------------------------------------request   end-------------------------------------------\n");
        }

        return result;
    }

    private String printStackTrace(Exception ex) {
        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            ex.printStackTrace(pw);

            return sw.toString();

        } catch (Exception e) {
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e2) {
            }
        }

        return "";
    }

    /**
     * 完整IP获取
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

        if (StrKit.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        } else {
            ip = request.getHeader("X-Real-IP");
            return StrKit.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip) ? ip : request.getRemoteAddr();
        }
    }

    /**
     * 处理Body
     *
     * @param request
     */
    private String readBody(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String content;
            while ((content = reader.readLine()) != null) {
                builder.append(content);
            }
            reader.close();

            return  builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

}
