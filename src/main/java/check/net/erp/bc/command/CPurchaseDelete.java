package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.service.PurchaseAccountService;
import check.net.erp.bc.service.PurchaseProductService;
import check.net.erp.bc.service.PurchaseService;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购货单删除
 */
@Command
@Transactional
public class CPurchaseDelete extends BaseCommand {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseProductService productService;
    @Autowired
    private PurchaseAccountService accountService;

    @Param(required = true)
    private String purchaseId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Purchase purchase = purchaseService.getById(purchaseId);
        Assert.notNull(purchase, "ID为【" + purchaseId + "】的购货单不存在！");

        purchaseService.removeById(purchaseId);

        productService.deleteByPurchase(purchaseId);
        accountService.deleteByPurchase(purchaseId);
    }
}
