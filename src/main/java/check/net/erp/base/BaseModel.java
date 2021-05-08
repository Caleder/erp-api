package check.net.erp.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class BaseModel<M extends BaseModel> implements Serializable {

    private static final long serialVersionUID = -990334519496260591L;

    @TableField(exist = false)
    private Map<String, Object> __attrs;
    @TableField(exist = false)
    private List<String> __removedAttrs; // 已经被移除的属性

    public BaseModel() {
        __attrs = new HashMap<>();
        __removedAttrs = new ArrayList<>();
    }

    public M put(String key, Object value) {
        __attrs.put(key, value);
        __removedAttrs.remove(key);
        return (M) this;
    }

    public <T> T get(String attr) {
        return (T) (__attrs.get(attr));
    }

    /**
     * Get attribute of any mysql type. Returns defaultValue if null.
     */
    public <T> T get(String attr, Object defaultValue) {
        Object result = __attrs.get(attr);
        return (T)(result != null ? result : defaultValue);
    }

    public Map<String, Object> getAttrs() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("__attrs")) {
                continue;
            }

            field.setAccessible(true);

            try {
                __attrs.put(field.getName(), field.get(this));

            } catch (IllegalAccessException e) {
            }
        }

        for (String removedAttr : __removedAttrs) {
            __attrs.remove(removedAttr);
        }

        return  __attrs;
    }

    /**
     * Put map to the model without check attribute name.
     */
    public M put(Map<String, Object> map) {
        __attrs.putAll(map);
        __removedAttrs.removeAll(map.keySet());
        return (M)this;
    }

    /**
     * Put other model to the model without check attribute name.
     */
    public M put(BaseModel model) {
        __attrs.putAll(model.getAttrs());
        __removedAttrs.removeAll(model.getAttrs().keySet());
        return (M)this;
    }

    public M remove(String attr) {
        __attrs.remove(attr);
        __removedAttrs.add(attr);
        return (M)this;
    }

    /**
     * Get attribute of mysql type: varchar, char, enum, set, text, tinytext, mediumtext, longtext
     */
    public String getStr(String attr) {
        return (String)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
     */
    public Integer getInt(String attr) {
        return (Integer)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: bigint, unsign int
     */
    public Long getLong(String attr) {
        return (Long)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: unsigned bigint
     */
    public java.math.BigInteger getBigInteger(String attr) {
        return (java.math.BigInteger)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: date, year
     */
    public java.util.Date getDate(String attr) {
        return (java.util.Date)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: time
     */
    public java.sql.Time getTime(String attr) {
        return (java.sql.Time)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: timestamp, datetime
     */
    public java.sql.Timestamp getTimestamp(String attr) {
        return (java.sql.Timestamp)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: real, double
     */
    public Double getDouble(String attr) {
        return (Double)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: float
     */
    public Float getFloat(String attr) {
        return (Float)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: bit, tinyint(1)
     */
    public Boolean getBoolean(String attr) {
        return (Boolean)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: decimal, numeric
     */
    public java.math.BigDecimal getBigDecimal(String attr) {
        return (java.math.BigDecimal)__attrs.get(attr);
    }

    /**
     * Get attribute of mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob
     */
    public byte[] getBytes(String attr) {
        return (byte[])__attrs.get(attr);
    }

    /**
     * Get attribute of any type that extends from Number
     */
    public Number getNumber(String attr) {
        return (Number)__attrs.get(attr);
    }

    public JSONObject getJSONObject(String str) {
        if (StrKit.isBlank(str)) {
            return new JSONObject();
        }
        return JSONObject.parseObject(str);
    }

    public JSONArray getJSONArray(String str) {
        if (StrKit.isBlank(str)) {
            return new JSONArray();
        }
        return JSONArray.parseArray(str);
    }

}
