package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.model.Warehouse;
import check.net.erp.uc.service.WarehouseService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 仓库分页列表
 */
@Command
public class CWarehousePage extends BaseCommand {

    @Autowired
    private WarehouseService warehouseService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current; // 页码
    @Param(defaultValue = Define.SIZE)
    private long size; // 每页数量

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Page<Warehouse> warehousePage = warehouseService.pageSearch(current, size, query);

        data.put("warehousePage", warehousePage);
    }
}
