package check.net.erp.bc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 退货商品
 */
@Data
@TableName("bc_refund_product")
public class RefundProduct extends BaseModel<RefundProduct> {

    /**
     * 退货商品ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 序列号
     */
    private String code;

    /**
     * 退货ID
     */
    private String refundId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 仓库ID
     */
    private String warehouseId;

    /**
     * 数量
     */
    private Double quantity;

    /**
     * 单价
     */
    private Double price;

    /**
     * 折扣率
     */
    private Double discountRate;

    /**
     * 折扣额
     */
    private Double discountAmount;

    /**
     * 购货金额
     */
    private Double amount;

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
