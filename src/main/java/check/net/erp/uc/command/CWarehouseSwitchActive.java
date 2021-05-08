package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.model.Warehouse;
import check.net.erp.uc.service.WarehouseService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 仓库启停
 */
@Command
public class CWarehouseSwitchActive extends BaseCommand {

    @Autowired
    private WarehouseService warehouseService;

    @Param(required = true)
    private String warehouseId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Warehouse warehouse = warehouseService.getById(warehouseId);
        Assert.notNull(warehouse, "ID为【" + warehouseId + "】的仓库不存在！");

        warehouse.setActive(!warehouse.isActive());
        warehouseService.saveOrUpdate(warehouse);

        data.put("warehouse", warehouse);
    }
}
