package check.net.erp.uc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import check.net.erp.base.BaseModel;

import java.util.Date;

/**
 * 供应商
 */
@Data
@TableName("uc_supplier")
public class Supplier extends BaseModel<Supplier> {

    /**
     * 供应商ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 客户分类ID
     */
    private String categoryId;

    /**
     * 余额日期
     */
    private Date balanceTime;

    /**
     * 期初应收款
     */
    private long beginReceivableAmount;

    /**
     * 期初预付款
     */
    private long beginPrepaidAmount;

    /**
     * 增值税税率
     */
    private int vatRate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用
     */
    private boolean active;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
