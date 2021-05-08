package check.net.erp.bc.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.bc.model.Purchase;
import org.springframework.stereotype.Component;

/**
 * 购货Dao
 */
@Component
public interface PurchaseDao extends BaseMapper<Purchase> {

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<Purchase> queryPage(Page<Purchase> page, JSONObject query);
}
