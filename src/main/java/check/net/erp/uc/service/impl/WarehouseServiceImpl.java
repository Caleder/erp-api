package check.net.erp.uc.service.impl;

import check.net.erp.uc.dao.WarehouseDao;
import check.net.erp.uc.model.Warehouse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import check.net.erp.uc.service.WarehouseService;
import org.springframework.stereotype.Service;

/**
 * 仓库服务实现
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, Warehouse> implements WarehouseService {
    @Override
    public Page<Warehouse> pageSearch(long current, long size, JSONObject query) {
        QueryWrapper<Warehouse> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("createdTime");

        return this.page(new Page<>(current, size), wrapper);
    }
}
