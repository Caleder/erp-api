package check.net.erp.bc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.PurchaseAccount;

import java.util.List;

/**
 * 购货账户服务
 */
public interface PurchaseAccountService extends IService<PurchaseAccount> {
    /**
     * 获取购物单的账户列表
     *
     * @param purchaseId
     * @return
     */
    List<PurchaseAccount> findListByPurchase(String purchaseId);

    /**
     * 根据购物单删除
     *
     * @param purchaseId
     */
    void deleteByPurchase(String purchaseId);
}
