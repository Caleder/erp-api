package check.net.erp.uc.service;

import check.net.erp.uc.model.Warehouse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 仓库服务
 */
public interface WarehouseService extends IService<Warehouse> {

    /**
     * 分页查询
     *
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<Warehouse> pageSearch(long current, long size, JSONObject query);
}
