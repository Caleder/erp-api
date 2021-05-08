package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.bc.service.CustomerOrderProductService;
import check.net.erp.base.BaseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户订单删除
 */
@Command
@Transactional
public class CSellCustomerOrderDelete extends BaseCommand {

    @Autowired
    private check.net.erp.bc.service.CustomerOrderService CustomerOrderService;
    @Autowired
    private CustomerOrderProductService productService;

    @Param(required = true)
    private String customerOrderId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        CustomerOrder CustomerOrder = CustomerOrderService.getById(customerOrderId);
        Assert.notNull(CustomerOrder, "ID为【" + customerOrderId + "】的客户订单不存在！");

        CustomerOrderService.removeById(customerOrderId);

        productService.deleteByCustomerOrder(customerOrderId);
    }
}
