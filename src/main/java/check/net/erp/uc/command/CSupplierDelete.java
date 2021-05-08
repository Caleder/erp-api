package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.uc.model.Supplier;
import check.net.erp.uc.service.SupplierContactService;
import check.net.erp.uc.service.SupplierService;
import check.net.erp.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商删除
 */
@Command
@Transactional
public class CSupplierDelete extends BaseCommand {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierContactService contactService;

    @Param(required = true)
    private String supplierId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Supplier supplier = supplierService.getById(supplierId);
        Assert.notNull(supplier, "ID为【" + supplierId + "】的供应商不存在！");

        supplierService.removeById(supplierId);

        contactService.deleteBySupplier(supplierId);
    }
}
