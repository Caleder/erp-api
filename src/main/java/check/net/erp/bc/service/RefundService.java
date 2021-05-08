package check.net.erp.bc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.Refund;

/**
 * 退货服务
 */
public interface RefundService extends IService<Refund> {

    /**
     * 分页列表
     *
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<Refund> pageSearch(long current, long size, JSONObject query);

}
