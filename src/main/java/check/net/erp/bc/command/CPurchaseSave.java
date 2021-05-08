package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.uc.model.Product;
import check.net.erp.uc.model.SettlementAccount;
import check.net.erp.uc.model.Supplier;
import check.net.erp.uc.model.Warehouse;
import check.net.erp.uc.service.ProductService;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.uc.service.SupplierService;
import check.net.erp.uc.service.WarehouseService;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.StrKit;
import check.net.erp.bc.model.Purchase;
import check.net.erp.bc.model.PurchaseAccount;
import check.net.erp.bc.model.PurchaseProduct;
import check.net.erp.bc.service.PurchaseAccountService;
import check.net.erp.bc.service.PurchaseProductService;
import check.net.erp.bc.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 购货单保存
 */
@Command
public class CPurchaseSave extends BaseCommand {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseProductService purchaseProductService;
    @Autowired
    private PurchaseAccountService purchaseAccountService;
    @Autowired
    private SettlementAccountService settlementAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;

    @Param(required = true)
    private Purchase purchase;
    @Param(defaultValue = "[]")
    private List<PurchaseProduct> productList;
    @Param(defaultValue = "[]")
    private List<PurchaseAccount> accountList;

    private Purchase persistedPurchase;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        // 校验数据
        Assert.notBlank(purchase.getSupplierId(), "供应商ID不能为空！");
        Supplier supplier = supplierService.getById(purchase.getSupplierId());
        Assert.notNull(supplier, "ID为【" + purchase.getSupplierId() + "】的供应商不存在！");

        // 计算
        if (StrKit.isBlank(purchase.getId())) {
            persistedPurchase = new Purchase();

            // TODO 校验编码是否合法
            persistedPurchase.setCode(purchase.getCode());
            persistedPurchase.setChecked(false);

        } else {
            persistedPurchase = purchaseService.getById(purchase.getId());
            Assert.notNull(persistedPurchase, "ID为【" + purchase.getId() + "】的购货单不存在！");

            // 删除原来的商品
            purchaseProductService.deleteByPurchase(purchase.getId());

            // 删除原来的账户
            purchaseAccountService.deleteByPurchase(purchase.getId());
        }

        persistedPurchase.setIssueDate(purchase.getIssueDate());
        persistedPurchase.setContracts(purchase.getContracts());
        persistedPurchase.setSupplierId(purchase.getSupplierId());
        persistedPurchase.setTotalAmount(purchase.getTotalAmount());
        persistedPurchase.setDiscountedAmount(purchase.getDiscountedAmount());
        persistedPurchase.setPaidAmount(purchase.getPaidAmount());
        persistedPurchase.setDebtAmount(purchase.getDebtAmount());
        persistedPurchase.setPaidStatus(Define.PAID_STATUS_PAID); // TODO 计算出来
        persistedPurchase.setQuantity(purchase.getQuantity());
        persistedPurchase.setDiscountRate(purchase.getDiscountRate());
        persistedPurchase.setListerId(purchase.getListerId());
        purchaseService.saveOrUpdate(persistedPurchase);

        // 新增商品
        addProductList();

        // 新增账户
        addAccountList();

        data.put("purchase", persistedPurchase);
    }

    /**
     * 新增账户列表
     */
    private void addAccountList() {
        if (accountList == null || accountList.size() == 0) return;

        List<PurchaseAccount> persistedPurchaseAccountList = new ArrayList<>();
        PurchaseAccount persistedPurchaseAccount;
        for (PurchaseAccount purchaseAccount : accountList) {
            Assert.notBlank(purchaseAccount.getAccountId(), "结算账户的ID不能为空！");
            SettlementAccount settlementAccount = settlementAccountService.getById(purchaseAccount.getAccountId());
            Assert.notNull(settlementAccount, "ID为【" + purchaseAccount.getAccountId() + "】的结算账户不存在！");

            persistedPurchaseAccount = new PurchaseAccount();
            persistedPurchaseAccount.setPurchaseId(persistedPurchase.getId());
            persistedPurchaseAccount.setAccountId(purchaseAccount.getAccountId());

            // TODO 校验金额
            persistedPurchaseAccount.setPaidAmount(purchaseAccount.getPaidAmount());

            persistedPurchaseAccountList.add(persistedPurchaseAccount);
        }

        purchaseAccountService.saveBatch(persistedPurchaseAccountList);
    }

    /**
     * 新增商品列表
     */
    private void addProductList() {
        if (productList == null || productList.size() == 0) return;

        List<PurchaseProduct> persistedPurchaseProductList = new ArrayList<>();
        PurchaseProduct persistedPurchaseProduct;
        for (PurchaseProduct purchaseProduct : productList) {
            Assert.notBlank(purchaseProduct.getProductId(), "商品ID不能为空！");
            Product product = productService.getById(purchaseProduct.getProductId());
            Assert.notNull(product, "ID为【" + purchaseProduct.getProductId() + "】的商品不存在！");


            Assert.notBlank(purchaseProduct.getWarehouseId(), "仓库ID不能为空！");
            Warehouse warehouse = warehouseService.getById(purchaseProduct.getWarehouseId());
            Assert.notNull(warehouse, "ID为【" + purchaseProduct.getWarehouseId() + "】的仓库不存在！");

            persistedPurchaseProduct = new PurchaseProduct();
            persistedPurchaseProduct.setCode(purchaseProduct.getCode());
            persistedPurchaseProduct.setPurchaseId(persistedPurchase.getId());
            persistedPurchaseProduct.setProductId(product.getId());
            persistedPurchaseProduct.setWarehouseId(warehouse.getId());

            // TODO 校验数据
            persistedPurchaseProduct.setQuantity(purchaseProduct.getQuantity());
            persistedPurchaseProduct.setPrice(purchaseProduct.getPrice());
            persistedPurchaseProduct.setDiscountRate(purchaseProduct.getDiscountRate());
            persistedPurchaseProduct.setDiscountAmount(purchaseProduct.getDiscountAmount());
            persistedPurchaseProduct.setAmount(purchaseProduct.getAmount());
            persistedPurchaseProduct.setRemark(purchaseProduct.getRemark());

            persistedPurchaseProductList.add(persistedPurchaseProduct);
        }

        purchaseProductService.saveBatch(persistedPurchaseProductList);
    }
}
