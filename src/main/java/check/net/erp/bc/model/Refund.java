package check.net.erp.bc.model;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 退货单
 */
@Data
@TableName("bc_refund")
public class Refund extends BaseModel<Refund> {

    /**
     * 退货单ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 单据日期
     */
    private String issueDate;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 采购合同
     */
    private String contracts;

    public JSONArray getContractArray() {
        return getJSONArray(contracts);
    }

    public void setContractArray(JSONArray contractArray) {
        setContracts(contractArray == null ? "[]" : contractArray.toJSONString());
    }

    /**
     * 供应商ID
     */
    private String supplierId;

    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 优惠后金额
     */
    private Double discountedAmount;

    /**
     * 已退款金额
     */
    private Double refundAmount;

    /**
     * 欠款金额
     */
    private Double debtAmount;

    /**
     * 退款状态
     */
    private int refundStatus;

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
