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
import check.net.erp.bc.model.Refund;
import check.net.erp.bc.model.RefundAccount;
import check.net.erp.bc.model.RefundProduct;
import check.net.erp.bc.service.RefundAccountService;
import check.net.erp.bc.service.RefundProductService;
import check.net.erp.bc.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 退货单保存
 */
@Command
public class CRefundSave extends BaseCommand {

    @Autowired
    private RefundService refundService;
    @Autowired
    private RefundProductService refundProductService;
    @Autowired
    private RefundAccountService refundAccountService;
    @Autowired
    private SettlementAccountService settlementAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;

    @Param(required = true)
    private Refund refund;
    @Param(defaultValue = "[]")
    private List<RefundProduct> productList;
    @Param(defaultValue = "[]")
    private List<RefundAccount> accountList;

    private Refund persistedRefund;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        // 校验数据
        Assert.notBlank(refund.getSupplierId(), "供应商ID不能为空！");
        Supplier supplier = supplierService.getById(refund.getSupplierId());
        Assert.notNull(supplier, "ID为【" + refund.getSupplierId() + "】的供应商不存在！");

        // 计算
        if (StrKit.isBlank(refund.getId())) {
            persistedRefund = new Refund();

            // TODO 校验编码是否合法
            persistedRefund.setCode(refund.getCode());
            persistedRefund.setChecked(false);

        } else {
            persistedRefund = refundService.getById(refund.getId());
            Assert.notNull(persistedRefund, "ID为【" + refund.getId() + "】的退货单不存在！");

            // 删除原来的商品
            refundProductService.deleteByRefund(refund.getId());

            // 删除原来的账户
            refundAccountService.deleteByRefund(refund.getId());
        }

        persistedRefund.setIssueDate(refund.getIssueDate());
        persistedRefund.setContracts(refund.getContracts());
        persistedRefund.setSupplierId(refund.getSupplierId());
        persistedRefund.setTotalAmount(refund.getTotalAmount());
        persistedRefund.setDiscountedAmount(refund.getDiscountedAmount());
        persistedRefund.setRefundAmount(refund.getRefundAmount());
        persistedRefund.setDebtAmount(refund.getDebtAmount());
        persistedRefund.setRefundStatus(Define.REFUND_STATUS_PAID); // TODO 计算出来
        persistedRefund.setQuantity(refund.getQuantity());
        persistedRefund.setDiscountRate(refund.getDiscountRate());
        persistedRefund.setListerId(refund.getListerId());
        refundService.saveOrUpdate(persistedRefund);

        // 新增商品
        addProductList();

        // 新增账户
        addAccountList();

        data.put("refund", persistedRefund);
    }

    /**
     * 新增账户列表
     */
    private void addAccountList() {
        if (accountList == null || accountList.size() == 0) return;

        List<RefundAccount> persistedRefundAccountList = new ArrayList<>();
        RefundAccount persistedRefundAccount;
        for (RefundAccount refundAccount : accountList) {
            Assert.notBlank(refundAccount.getAccountId(), "结算账户的ID不能为空！");
            SettlementAccount settlementAccount = settlementAccountService.getById(refundAccount.getAccountId());
            Assert.notNull(settlementAccount, "ID为【" + refundAccount.getAccountId() + "】的结算账户不存在！");

            persistedRefundAccount = new RefundAccount();
            persistedRefundAccount.setRefundId(persistedRefund.getId());
            persistedRefundAccount.setAccountId(refundAccount.getAccountId());

            // TODO 校验金额
            persistedRefundAccount.setRefundAmount(refundAccount.getRefundAmount());

            persistedRefundAccountList.add(persistedRefundAccount);
        }

        refundAccountService.saveBatch(persistedRefundAccountList);
    }

    /**
     * 新增商品列表
     */
    private void addProductList() {
        if (productList == null || productList.size() == 0) return;

        List<RefundProduct> persistedRefundProductList = new ArrayList<>();
        RefundProduct persistedRefundProduct;
        for (RefundProduct refundProduct : productList) {
            Assert.notBlank(refundProduct.getProductId(), "商品ID不能为空！");
            Product product = productService.getById(refundProduct.getProductId());
            Assert.notNull(product, "ID为【" + refundProduct.getProductId() + "】的商品不存在！");


            Assert.notBlank(refundProduct.getWarehouseId(), "仓库ID不能为空！");
            Warehouse warehouse = warehouseService.getById(refundProduct.getWarehouseId());
            Assert.notNull(warehouse, "ID为【" + refundProduct.getWarehouseId() + "】的仓库不存在！");

            persistedRefundProduct = new RefundProduct();
            persistedRefundProduct.setCode(refundProduct.getCode());
            persistedRefundProduct.setRefundId(persistedRefund.getId());
            persistedRefundProduct.setProductId(product.getId());
            persistedRefundProduct.setWarehouseId(warehouse.getId());

            // TODO 校验数据
            persistedRefundProduct.setQuantity(refundProduct.getQuantity());
            persistedRefundProduct.setPrice(refundProduct.getPrice());
            persistedRefundProduct.setDiscountRate(refundProduct.getDiscountRate());
            persistedRefundProduct.setDiscountAmount(refundProduct.getDiscountAmount());
            persistedRefundProduct.setAmount(refundProduct.getAmount());
            persistedRefundProduct.setRemark(refundProduct.getRemark());

            persistedRefundProductList.add(persistedRefundProduct);
        }

        refundProductService.saveBatch(persistedRefundProductList);
    }
}
