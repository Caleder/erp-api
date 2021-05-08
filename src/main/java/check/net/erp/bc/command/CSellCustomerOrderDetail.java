package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.bc.model.CustomerOrderProduct;
import check.net.erp.bc.service.CustomerOrderProductService;
import check.net.erp.bc.service.CustomerOrderService;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.*;
import check.net.erp.bc.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 客户订单详情
 */
@Command
public class CSellCustomerOrderDetail extends BaseCommand {

    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private CustomerOrderProductService productService;

    @Param(required = true)
    private String customerOrderId; // 客户订单ID

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        CustomerOrder customerOrder = customerOrderService.getById(customerOrderId);
        Assert.notNull(customerOrder, "ID为【" + customerOrderId + "】的客户订单不存在！");

        List<CustomerOrderProduct> productList = productService.findListByCustomerOrder(customerOrder.getId());
        customerOrder.put("productList", productList);

        data.put("customerOrder", customerOrder);
    }
}
