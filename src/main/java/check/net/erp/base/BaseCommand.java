package check.net.erp.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseCommand {
    private Result result = new Result();
    protected JSONObject data = new JSONObject();
    private JSONObject paramObj;

    public BaseCommand() {
    }

    public Result doAction(JSONObject paramObj) throws Exception {
        this.paramObj = paramObj;
        this.initArgs();

        try {
            this.init();
            this.doCommand();
        } finally {
            this.doFinally();
        }

        this.result.setData(JSONObject.parseObject(JsonKit.toJson(this.data)));
        return this.result;
    }

    private void initArgs() throws IllegalAccessException {
        Field[] fields = this.getClass().getDeclaredFields();
        Param param = null;
        Field[] var3 = fields;
        int var4 = fields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            param = (Param)field.getAnnotation(Param.class);
            if (param != null) {
                this.handleField(field, param);
            }
        }

    }

    private void handleField(Field field, Param param) throws IllegalAccessException {
        field.setAccessible(true);
        String paramName = StrKit.notBlank(param.name()) ? param.name() : param.value();
        paramName = StrKit.notBlank(paramName) ? paramName : field.getName();
        Object paramValue = null;

        try {
            paramValue = this.paramObj.getObject(paramName, field.getType());
            if (paramValue == null && "currentUser".equals(paramName)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                paramValue = request.getAttribute("currentUser");
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            if (StrKit.isBlank(param.defaultValue()) && var7 instanceof JSONException) {
                throw new BizException(StrKit.notBlank(var7.getMessage()) ? var7.getMessage() : "参数转换错误！");
            }

            if (StrKit.notBlank(param.defaultValue())) {
                field.set(this, JSONObject.parseObject(param.defaultValue(), field.getType()));
            }

            return;
        }

        if (paramValue == null && !param.required()) {
            if (StrKit.notBlank(param.defaultValue())) {
                field.set(this, JSONObject.parseObject(param.defaultValue(), field.getType()));
            }

        } else if (paramValue == null && param.required()) {
            throw new BizException("参数【" + paramName + "】不能为空！");
        } else {
            if (paramValue instanceof JSONArray && field.getGenericType() != null && field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType)field.getGenericType();
                paramValue = JSONArray.parseArray(JSONArray.toJSONString(paramValue), (Class)pt.getActualTypeArguments()[0]);
            }

            field.set(this, paramValue);
        }
    }

    protected abstract void init() throws Exception;

    protected abstract void doCommand() throws Exception;

    protected void doFinally() throws Exception {
    }
}
