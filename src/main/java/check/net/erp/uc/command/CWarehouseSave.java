package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.uc.model.Warehouse;
import check.net.erp.uc.service.WarehouseService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存仓库
 */
@Command
public class CWarehouseSave extends BaseCommand {

    @Autowired
    private WarehouseService warehouseService;

    @Param(required = true)
    private Warehouse warehouse;

    @Override
    protected void init() throws Exception {
        Assert.notBlank(warehouse.getCode(), "仓库编号不能为空！");
        Assert.notBlank(warehouse.getName(), "仓库名称不能为空！");
    }

    @Override
    protected void doCommand() throws Exception {
        Warehouse persistedWarehouse;
        if (StrKit.isBlank(warehouse.getId())) {
            persistedWarehouse = new Warehouse();
            persistedWarehouse.setActive(true);

        } else {
            persistedWarehouse = warehouseService.getById(warehouse.getId());
            Assert.notNull(persistedWarehouse, "ID为【" + warehouse.getId() + "】的仓库不存在！");
        }

        persistedWarehouse.setCode(warehouse.getCode());
        persistedWarehouse.setName(warehouse.getName());

        warehouseService.saveOrUpdate(persistedWarehouse);

        data.put("warehouse", persistedWarehouse);
    }
}
