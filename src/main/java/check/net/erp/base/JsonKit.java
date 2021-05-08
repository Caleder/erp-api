package check.net.erp.base;

import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonKit {
    private static int convertDepth = 15;
    private static String timestampPattern = "yyyy-MM-dd HH:mm:ss";
    private static String datePattern = "yyyy-MM-dd HH:mm:ss";
    private static boolean isTimestamp = false;

    public JsonKit() {
    }

    public static void setConvertDepth(int convertDepth) {
        if (convertDepth < 2) {
            throw new IllegalArgumentException("convert depth can not less than 2.");
        } else {
            JsonKit.convertDepth = convertDepth;
        }
    }

    public static void setTimestampPattern(String timestampPattern) {
        if (timestampPattern != null && !"".equals(timestampPattern.trim())) {
            JsonKit.timestampPattern = timestampPattern;
        } else {
            throw new IllegalArgumentException("timestampPattern can not be blank.");
        }
    }

    public static void isTimestamp(boolean isTimestamp) {
        JsonKit.isTimestamp = isTimestamp;
    }

    public static void setDatePattern(String datePattern) {
        if (datePattern != null && !"".equals(datePattern.trim())) {
            JsonKit.datePattern = datePattern;
        } else {
            throw new IllegalArgumentException("datePattern can not be blank.");
        }
    }

    private static String mapToJson(Map map, int depth) {
        if (map == null) {
            return "null";
        } else {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            Iterator iter = map.entrySet().iterator();
            sb.append('{');

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(',');
                }

                Entry entry = (Entry)iter.next();
                toKeyValue(String.valueOf(entry.getKey()), entry.getValue(), sb, depth);
            }

            sb.append('}');
            return sb.toString();
        }
    }

    private static String toKeyValue(String key, Object value, StringBuilder sb, int depth) {
        sb.append('"');
        if (key == null) {
            sb.append("null");
        } else {
            escape(key, sb);
        }

        sb.append('"').append(':');
        sb.append(toJson(value, depth));
        return sb.toString();
    }

    private static String listToJson(List list, int depth) {
        if (list == null) {
            return "null";
        } else {
            boolean first = true;
            StringBuilder sb = new StringBuilder();
            Iterator iter = list.iterator();
            sb.append('[');

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(',');
                }

                Object value = iter.next();
                if (value == null) {
                    sb.append("null");
                } else {
                    sb.append(toJson(value, depth));
                }
            }

            sb.append(']');
            return sb.toString();
        }
    }

    private static String escape(String s) {
        if (s == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            escape(s, sb);
            return sb.toString();
        }
    }

    private static void escape(String s, StringBuilder sb) {
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            switch(ch) {
            case '\b':
                sb.append("\\b");
                continue;
            case '\t':
                sb.append("\\t");
                continue;
            case '\n':
                sb.append("\\n");
                continue;
            case '\f':
                sb.append("\\f");
                continue;
            case '\r':
                sb.append("\\r");
                continue;
            case '"':
                sb.append("\\\"");
                continue;
            case '/':
                sb.append("\\/");
                continue;
            case '\\':
                sb.append("\\\\");
                continue;
            }

            if (ch >= 0 && ch <= 31 || ch >= 127 && ch <= 159 || ch >= 8192 && ch <= 8447) {
                String str = Integer.toHexString(ch);
                sb.append("\\u");

                for(int k = 0; k < 4 - str.length(); ++k) {
                    sb.append('0');
                }

                sb.append(str.toUpperCase());
            } else {
                sb.append(ch);
            }
        }

    }

    public static String toJson(Object value) {
        return toJson(value, convertDepth);
    }

    public static String toJson(Object value, int depth) {
        if (value != null && depth-- >= 0) {
            if (value instanceof String) {
                return "\"" + escape((String)value) + "\"";
            } else if (value instanceof Double) {
                return !((Double)value).isInfinite() && !((Double)value).isNaN() ? value.toString() : "null";
            } else if (value instanceof Float) {
                return !((Float)value).isInfinite() && !((Float)value).isNaN() ? value.toString() : "null";
            } else if (value instanceof Number) {
                return value.toString();
            } else if (value instanceof Boolean) {
                return value.toString();
            } else if (value instanceof Date) {
                if (isTimestamp) {
                    return "\"" + ((Date)value).getTime() + "\"";
                } else if (value instanceof Timestamp) {
                    return "\"" + (new SimpleDateFormat(timestampPattern)).format(value) + "\"";
                } else {
                    return value instanceof Time ? "\"" + value.toString() + "\"" : "\"" + (new SimpleDateFormat(datePattern)).format(value) + "\"";
                }
            } else if (value instanceof Map) {
                return mapToJson((Map)value, depth);
            } else if (value instanceof List) {
                return listToJson((List)value, depth);
            } else {
                String result = otherToJson(value, depth);
                return result != null ? result : "\"" + escape(value.toString()) + "\"";
            }
        } else {
            return "null";
        }
    }

    private static String otherToJson(Object value, int depth) {
        if (value instanceof Character) {
            return "\"" + escape(value.toString()) + "\"";
        } else if (value instanceof BaseModel) {
            Map map = ((BaseModel)value).getAttrs();
            return mapToJson(map, depth);
        } else if (!(value instanceof Object[])) {
            return value instanceof Enum ? "\"" + ((Enum)value).toString() + "\"" : beanToJson(value, depth);
        } else {
            Object[] arr = (Object[])((Object[])value);
            List list = new ArrayList(arr.length);

            for(int i = 0; i < arr.length; ++i) {
                list.add(arr[i]);
            }

            return listToJson(list, depth);
        }
    }

    private static String beanToJson(Object model, int depth) {
        Map map = new HashMap();
        Method[] methods = model.getClass().getMethods();
        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method m = var4[var6];
            String methodName = m.getName();
            int indexOfGet = methodName.indexOf("get");
            if (indexOfGet == 0 && methodName.length() > 3) {
                String attrName = methodName.substring(3);
                if (!attrName.equals("Class")) {
                    Class<?>[] types = m.getParameterTypes();
                    if (types.length == 0) {
                        try {
                            Object value = m.invoke(model);
                            map.put(StrKit.firstCharToLowerCase(attrName), value);
                        } catch (Exception var14) {
                            throw new RuntimeException(var14.getMessage(), var14);
                        }
                    }
                }
            } else {
                int indexOfIs = methodName.indexOf("is");
                if (indexOfIs == 0 && methodName.length() > 2) {
                    String attrName = methodName.substring(2);
                    Class<?>[] types = m.getParameterTypes();
                    if (types.length == 0) {
                        try {
                            Object value = m.invoke(model);
                            map.put(StrKit.firstCharToLowerCase(attrName), value);
                        } catch (Exception var15) {
                            throw new RuntimeException(var15.getMessage(), var15);
                        }
                    }
                }
            }
        }

        return mapToJson(map, depth);
    }
}
