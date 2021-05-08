package check.net.erp.bc.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.bc.model.CustomerOrder;
import org.springframework.stereotype.Component;

/**
 * 客户订单Dao
 */
@Component
public interface CustomerOrderDao extends BaseMapper<CustomerOrder> {

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<CustomerOrder> queryPage(Page<CustomerOrder> page, JSONObject query);

}
