package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.uc.model.Customer;
import check.net.erp.uc.model.Product;
import check.net.erp.uc.model.Warehouse;
import check.net.erp.uc.service.CustomerService;
import check.net.erp.uc.service.ProductService;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.uc.service.WarehouseService;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.StrKit;
import check.net.erp.bc.model.CustomerOrderProduct;
import check.net.erp.uc.model.*;
import check.net.erp.uc.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户订单保存
 */
@Command
public class CSellCustomerOrderSave extends BaseCommand {

    @Autowired
    private check.net.erp.bc.service.CustomerOrderService CustomerOrderService;
    @Autowired
    private check.net.erp.bc.service.CustomerOrderProductService CustomerOrderProductService;
    @Autowired
    private SettlementAccountService settlementAccountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CustomerService customerService;

    @Param(required = true)
    private CustomerOrder customerOrder;
    @Param(defaultValue = "[]")
    private List<CustomerOrderProduct> productList;

    private CustomerOrder persistedCustomerOrder;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
// 校验数据
        Assert.notBlank(customerOrder.getCustomerId(), "客户ID不能为空！");
        Customer customer = customerService.getById(customerOrder.getCustomerId());
        Assert.notNull(customer, "ID为【" + customerOrder.getCustomerId() + "】的客户不存在！");

        // 计算
        if (StrKit.isBlank(customerOrder.getId())) {
            persistedCustomerOrder = new CustomerOrder();

            // TODO 校验编码是否合法
            persistedCustomerOrder.setCode(customerOrder.getCode());
            persistedCustomerOrder.setChecked(false);

        } else {
            persistedCustomerOrder = CustomerOrderService.getById(customerOrder.getId());
            Assert.notNull(persistedCustomerOrder, "ID为【" + customerOrder.getId() + "】的客户订单不存在！");

            // 删除原来的商品
            CustomerOrderProductService.deleteByCustomerOrder(customerOrder.getId());
        }

        persistedCustomerOrder.setIssueDate(customerOrder.getIssueDate());
        persistedCustomerOrder.setDeliveryDate(customerOrder.getDeliveryDate());
        persistedCustomerOrder.setCustomerId(customerOrder.getCustomerId());
        persistedCustomerOrder.setTotalAmount(customerOrder.getTotalAmount());
        persistedCustomerOrder.setDiscountedAmount(customerOrder.getDiscountedAmount());
        persistedCustomerOrder.setQuantity(customerOrder.getQuantity());
        persistedCustomerOrder.setDiscountRate(customerOrder.getDiscountRate());
        persistedCustomerOrder.setListerId(customerOrder.getListerId());
        CustomerOrderService.saveOrUpdate(persistedCustomerOrder);

        // 新增商品
        addProductList();

        data.put("customerOrder", persistedCustomerOrder);
    }

    /**
     * 新增商品列表
     */
    private void addProductList() {
        if (productList == null || productList.size() == 0) return;

        List<CustomerOrderProduct> persistedCustomerOrderProductList = new ArrayList<>();
        CustomerOrderProduct persistedCustomerOrderProduct;
        for (CustomerOrderProduct CustomerOrderProduct : productList) {
            Assert.notBlank(CustomerOrderProduct.getProductId(), "商品ID不能为空！");
            Product product = productService.getById(CustomerOrderProduct.getProductId());
            Assert.notNull(product, "ID为【" + CustomerOrderProduct.getProductId() + "】的商品不存在！");


            Assert.notBlank(CustomerOrderProduct.getWarehouseId(), "仓库ID不能为空！");
            Warehouse warehouse = warehouseService.getById(CustomerOrderProduct.getWarehouseId());
            Assert.notNull(warehouse, "ID为【" + CustomerOrderProduct.getWarehouseId() + "】的仓库不存在！");

            persistedCustomerOrderProduct = new CustomerOrderProduct();
            persistedCustomerOrderProduct.setCustomerOrderId(persistedCustomerOrder.getId());
            persistedCustomerOrderProduct.setProductId(product.getId());
            persistedCustomerOrderProduct.setWarehouseId(warehouse.getId());

            // TODO 校验数据
            persistedCustomerOrderProduct.setQuantity(CustomerOrderProduct.getQuantity());
            persistedCustomerOrderProduct.setPrice(CustomerOrderProduct.getPrice());
            persistedCustomerOrderProduct.setDiscountRate(CustomerOrderProduct.getDiscountRate());
            persistedCustomerOrderProduct.setDiscountAmount(CustomerOrderProduct.getDiscountAmount());
            persistedCustomerOrderProduct.setAmount(CustomerOrderProduct.getAmount());
            persistedCustomerOrderProduct.setRemark(CustomerOrderProduct.getRemark());

            persistedCustomerOrderProductList.add(persistedCustomerOrderProduct);
        }

        CustomerOrderProductService.saveBatch(persistedCustomerOrderProductList);
    }
}
