package check.net.erp.uc.dao;

import check.net.erp.uc.model.Customer;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;

/**
 * 客户Dao
 */
@Component
public interface CustomerDao extends BaseMapper<Customer> {

    Page<JSONObject> queryPage(Page<JSONObject> page, JSONObject query);

}
