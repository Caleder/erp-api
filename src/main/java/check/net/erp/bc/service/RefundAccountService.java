package check.net.erp.bc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.RefundAccount;

import java.util.List;

/**
 * 退货账户服务
 */
public interface RefundAccountService extends IService<RefundAccount> {
    /**
     * 获取退货单的账户列表
     *
     * @param refundId
     * @return
     */
    List<RefundAccount> findListByRefund(String refundId);

    /**
     * 根据退物单删除
     *
     * @param refundId
     */
    void deleteByRefund(String refundId);
}
