package check.net.erp.bc.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.bc.model.Refund;
import org.springframework.stereotype.Component;

/**
 * 退货Dao
 */
@Component
public interface RefundDao extends BaseMapper<Refund> {

    /**
     * 分页查询
     *
     * @param page
     * @param query
     * @return
     */
    Page<Refund> queryPage(Page<Refund> page, JSONObject query);
}
