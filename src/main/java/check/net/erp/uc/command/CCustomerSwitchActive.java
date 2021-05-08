package check.net.erp.uc.command;

import check.net.erp.uc.model.Customer;
import check.net.erp.uc.service.CustomerService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 客户启停
 */
@Command
public class CCustomerSwitchActive extends BaseCommand {

    @Autowired
    private CustomerService customerService;

    @Param(required = true)
    private String customerId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Customer customer = customerService.getById(customerId);
        Assert.notNull(customer, "ID为【" + customerId + "】的客户不存在！");

        customer.setActive(!customer.isActive());
        customerService.saveOrUpdate(customer);

        data.put("customer", customer);
    }
}
