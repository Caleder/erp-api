package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.PurchaseAccount;
import check.net.erp.bc.service.PurchaseAccountService;
import check.net.erp.bc.service.PurchaseProductService;
import check.net.erp.bc.service.PurchaseService;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.Purchase;
import check.net.erp.bc.model.PurchaseProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 购货单详情
 */
@Command
public class CPurchaseDetail extends BaseCommand {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseProductService productService;
    @Autowired
    private PurchaseAccountService accountService;

    @Param(required = true)
    private String purchaseId; // 购货ID

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Purchase purchase = purchaseService.getById(purchaseId);
        Assert.notNull(purchase, "ID为【" + purchaseId + "】的购货单不存在！");

        List<PurchaseProduct> productList = productService.findListByPurchase(purchase.getId());
        purchase.put("productList", productList);

        List<PurchaseAccount> accountList = accountService.findListByPurchase(purchase.getId());
        purchase.put("accountList", accountList);

        data.put("purchase", purchase);
    }
}
