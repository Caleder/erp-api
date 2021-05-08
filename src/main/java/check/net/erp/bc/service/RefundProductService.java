package check.net.erp.bc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.RefundProduct;

import java.util.List;

/**
 * 退货商品服务
 */
public interface RefundProductService extends IService<RefundProduct> {
    /**
     * 获取退货单商品列表
     *
     * @param refundId
     * @return
     */
    List<RefundProduct> findListByRefund(String refundId);

    /**
     * 根据退货单删除
     *
     * @param refundId
     */
    void deleteByRefund(String refundId);
}
