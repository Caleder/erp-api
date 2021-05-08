package check.net.erp.bc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 客户订单
 */
@Data
@TableName("bc_customer_order")
public class CustomerOrder extends BaseModel<CustomerOrder> {

    /**
     * 客户订单ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 单据日期
     */
    private String issueDate;

    /**
     * 交货日期
     */
    private String deliveryDate;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 优惠后金额
     */
    private Double discountedAmount;

    /**
     * 数量
     */
    private Double quantity;

    /**
     * 优惠率
     */
    private Double discountRate;

    /**
     * 制单人ID
     */
    private String listerId;

    /**
     * 审核人ID
     */
    private String auditorId;

    /**
     * 审核状态
     */
    private boolean checked;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
