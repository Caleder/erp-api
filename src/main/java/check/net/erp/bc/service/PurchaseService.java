package check.net.erp.bc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import check.net.erp.bc.model.Purchase;

/**
 * 购货服务
 */
public interface PurchaseService extends IService<Purchase> {

    /**
     * 分页列表
     *
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<Purchase> pageSearch(long current, long size, JSONObject query);

}
