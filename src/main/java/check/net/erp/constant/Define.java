package check.net.erp.constant;

/**
 * 常量
 */
public class Define {

    public static final String CURRENT = "1";
    public static final String SIZE = "15";

    // --------------------------------系统设置的键
    public static final String SYSTEM_CONFIGURATION_KEY = "system_configuration";

    // --------------------------------日志类型
    public static final int LOG_TYPE_LOGIN = 10000; // 登录
    /** 用户相关 */
    public static final int LOG_TYPE_USER_ADD = 11010; // 新增用户
    public static final int LOG_TYPE_USER_ACTIVATE = 11020; // 启用
    public static final int LOG_TYPE_USER_DEACTIVATE = 11030; // 停用
    public static final int LOG_TYPE_USER_RESET_PASSWORD = 11040; // 修改密码

    // ---------------------------------类别类型
    public static final int CATEGORY_TYPE_CUSTOMER = 10; // 客户
    public static final int CATEGORY_TYPE_SUPPLIER = 20; // 供应商
    public static final int CATEGORY_TYPE_PRODUCT = 30; // 商品
    public static final int CATEGORY_TYPE_EXPENSE = 40; // 支出
    public static final int CATEGORY_TYPE_INCOME = 50; // 收入
    /** 校验类别 */
    public static boolean validateCategoryType(int type) {
       return CATEGORY_TYPE_CUSTOMER == type || CATEGORY_TYPE_SUPPLIER == type
                || CATEGORY_TYPE_PRODUCT == type || CATEGORY_TYPE_EXPENSE == type
                || CATEGORY_TYPE_INCOME == type;
    }

    // ----------------------------------字典编码
    public static final String DICT_CODE_UNIT = "unit"; // 计量单位
    public static final String DICT_CODE_SETTLEMENT = "settlement"; // 计算方式
    public static final String DICT_CODE_CUSTOMER_LEVEL = "customer_level"; // 客户等级
    public static final String DICT_CODE_ACCOUNT_TYPE = "account_type"; // 账户类别

    // -----------------------------------付款状态
    public static final int PAID_STATUS_UNPAID = 10; // 未付款
    public static final int PAID_STATUS_PARTIAL = 20; // 部分付款
    public static final int PAID_STATUS_PAID = 30; // 已付款

    // -----------------------------------退款状态
    public static final int REFUND_STATUS_UNPAID = 10; // 未退款
    public static final int REFUND_STATUS_PARTIAL = 20; // 部分退款
    public static final int REFUND_STATUS_PAID = 30; // 已退款
}
