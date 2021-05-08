package check.net.erp.bc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 退货账户
 */
@Data
@TableName("bc_refund_account")
public class RefundAccount extends BaseModel<RefundAccount> {

    /**
     * 退货账户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 退货ID
     */
    private String refundId;

    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 退款金额
     */
    private Double refundAmount;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
