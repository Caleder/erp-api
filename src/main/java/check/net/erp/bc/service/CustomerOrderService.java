package check.net.erp.bc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.CustomerOrder;

/**
 * 客户订单服务
 */
public interface CustomerOrderService extends IService<CustomerOrder> {

    /**
     * 分页列表
     *
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<CustomerOrder> pageSearch(long current, long size, JSONObject query);

}
