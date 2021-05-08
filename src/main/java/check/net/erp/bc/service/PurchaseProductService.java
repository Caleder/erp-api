package check.net.erp.bc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.PurchaseProduct;

import java.util.List;

/**
 * 购货商品服务
 */
public interface PurchaseProductService extends IService<PurchaseProduct> {
    /**
     * 获取购货单商品列表
     *
     * @param purchaseId
     * @return
     */
    List<PurchaseProduct> findListByPurchase(String purchaseId);

    /**
     * 根据购货单删除
     *
     * @param purchaseId
     */
    void deleteByPurchase(String purchaseId);
}
