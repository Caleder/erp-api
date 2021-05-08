package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.service.PurchaseService;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 购货单切换审查
 */
@Command
public class CPurchaseSwitchCheck extends BaseCommand {

    @Autowired
    private PurchaseService purchaseService;

    @Param(required = true)
    private String purchaseId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Purchase purchase = purchaseService.getById(purchaseId);
        Assert.notNull(purchase, "ID为【" + purchaseId + "】的购货单不存在！");

        purchase.setChecked(!purchase.isChecked());
        purchaseService.saveOrUpdate(purchase);

        data.put("purchase", purchase);
    }
}
