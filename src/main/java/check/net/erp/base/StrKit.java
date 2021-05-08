package check.net.erp.base;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Random;

public class StrKit {
    private static final String DEFAULT_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DEFAULT_CHARSET = "utf-8";
    public static final Random RANDOM = new Random();
    public static final String EMPTY = "";

    public StrKit() {
    }

    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] - 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean notBlank(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean notBlank(String... strings) {
        if (strings == null) {
            return false;
        } else {
            String[] var1 = strings;
            int var2 = strings.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String str = var1[var3];
                if (str == null || "".equals(str.trim())) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean notNull(Object... paras) {
        if (paras == null) {
            return false;
        } else {
            Object[] var1 = paras;
            int var2 = paras.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object obj = var1[var3];
                if (obj == null) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String getRndChars(int len, String source) {
        if (source == null) {
            source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }

        StringBuilder sb = new StringBuilder();
        int c = source.length();

        for(int i = 1; i < len + 1; ++i) {
            sb.append(source.charAt(RANDOM.nextInt(c - 1)));
        }

        return sb.toString();
    }

    public static String getRndChars(int len) {
        return getRndChars(len, (String)null);
    }

    public static String md5(String str) {
        return str == null ? null : md5(str.getBytes(Charset.forName("utf-8")));
    }

    public static String md5(byte[] source) {
        String s = null;
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] chars = new char[32];
            int k = 0;

            for(int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                chars[k++] = hexDigits[byte0 >>> 4 & 15];
                chars[k++] = hexDigits[byte0 & 15];
            }

            s = new String(chars);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return s;
    }
}
