package check.net.erp.bc.model;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 购货单
 */
@Data
@TableName("bc_purchase")
public class Purchase extends BaseModel<Purchase> {

    /**
     * 购货单ID
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
     * 已付款金额
     */
    private Double paidAmount;

    /**
     * 欠款金额
     */
    private Double debtAmount;

    /**
     * 付款状态
     */
    private int paidStatus;

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
