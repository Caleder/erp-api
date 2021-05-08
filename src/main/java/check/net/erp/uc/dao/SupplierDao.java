package check.net.erp.uc.dao;

import check.net.erp.uc.model.Supplier;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;

/**
 * 供应商Dao
 */
@Component
public interface SupplierDao extends BaseMapper<Supplier> {

    Page<JSONObject> queryPage(Page<JSONObject> page, JSONObject query);

}
