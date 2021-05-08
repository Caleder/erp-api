package check.net.erp.bc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import check.net.erp.bc.model.CustomerOrderProduct;
import org.springframework.stereotype.Component;

/**
 * 客户订单商品Dao
 */
@Component
public interface CustomerOrderProductDao extends BaseMapper<CustomerOrderProduct> {
}
