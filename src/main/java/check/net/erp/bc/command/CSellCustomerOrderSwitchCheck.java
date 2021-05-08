package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.bc.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 客户订单切换审查
 */
@Command
public class CSellCustomerOrderSwitchCheck extends BaseCommand {

    @Autowired
    private CustomerOrderService CustomerOrderService;

    @Param(required = true)
    private String customerOrderId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        CustomerOrder CustomerOrder = CustomerOrderService.getById(customerOrderId);
        Assert.notNull(CustomerOrder, "ID为【" + customerOrderId + "】的客户订单不存在！");

        CustomerOrder.setChecked(!CustomerOrder.isChecked());
        CustomerOrderService.saveOrUpdate(CustomerOrder);

        data.put("customerOrder", CustomerOrder);
    }
}
