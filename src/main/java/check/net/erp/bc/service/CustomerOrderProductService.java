package check.net.erp.bc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.CustomerOrderProduct;

import java.util.List;

/**
 * 客户订单商品服务
 */
public interface CustomerOrderProductService extends IService<CustomerOrderProduct> {
    /**
     * 获取客户订单商品列表
     *
     * @param customerOrderId
     * @return
     */
    List<CustomerOrderProduct> findListByCustomerOrder(String customerOrderId);

    /**
     * 根据客户订单删除
     *
     * @param customerOrderId
     */
    void deleteByCustomerOrder(String customerOrderId);
}
