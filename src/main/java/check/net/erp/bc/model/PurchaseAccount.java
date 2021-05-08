package check.net.erp.bc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 购货账户
 */
@Data
@TableName("bc_purchase_account")
public class PurchaseAccount extends BaseModel<PurchaseAccount> {

    /**
     * 购货账户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 购货ID
     */
    private String purchaseId;

    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 付款金额
     */
    private Double paidAmount;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
