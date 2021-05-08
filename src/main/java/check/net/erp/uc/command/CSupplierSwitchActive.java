package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.model.Supplier;
import check.net.erp.uc.service.SupplierService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 供应商启停
 */
@Command
public class CSupplierSwitchActive extends BaseCommand {

    @Autowired
    private SupplierService supplierService;

    @Param(required = true)
    private String supplierId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Supplier supplier = supplierService.getById(supplierId);
        Assert.notNull(supplier, "ID为【" + supplierId + "】的供应商不存在！");

        supplier.setActive(!supplier.isActive());
        supplierService.saveOrUpdate(supplier);

        data.put("supplier", supplier);
    }
}
