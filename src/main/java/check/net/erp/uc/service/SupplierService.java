package check.net.erp.uc.service;

import check.net.erp.uc.model.Supplier;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 供应商服务
 */
public interface SupplierService extends IService<Supplier> {

    /**
     * 根据编码获取对象
     *
     * @param code
     * @return
     */
    Supplier findByCode(String code);

    /**
     * 分页列表
     *
     * @param current
     * @param size
     * @param query
     * @return
     */
    Page<JSONObject> pageSearch(long current, long size, JSONObject query);
}
