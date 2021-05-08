package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.bc.service.CustomerOrderService;
import check.net.erp.constant.Define;
import check.net.erp.uc.model.Customer;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.CustomerService;
import check.net.erp.uc.service.UserService;
import check.net.erp.util.SimpleValidator;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.StrKit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 客户订单分页列表
 */
@Command
public class CSellCustomerOrderPage extends BaseCommand {

    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current;
    @Param(defaultValue = Define.SIZE)
    private long size;

    @Override
    protected void init() throws Exception {
        String startDate = query.getString("startDate");
        if (StrKit.notBlank(startDate)) {
            Assert.notFalse(SimpleValidator.validateDate(startDate), "起始时间不正确！");
        }

        String endDate = query.getString("endDate");
        if (StrKit.notBlank(endDate)) {
            Assert.notFalse(SimpleValidator.validateDate(endDate), "结束时间不正确！");
        }
    }

    @Override
    protected void doCommand() throws Exception {
        Page<CustomerOrder> customerOrderPage = customerOrderService.pageSearch(current, size, query);

        Customer customer;
        User auditor;
        User lister;
        for (CustomerOrder customerOrder : customerOrderPage.getRecords()) {
            customer = customerService.getById(customerOrder.getCustomerId());
            customerOrder.put("customerName", customer.getName());

            if (StrKit.notBlank(customerOrder.getListerId())) {
                lister = userService.getById(customerOrder.getListerId());
                customerOrder.put("listerName", lister.getName());
            }

            if (StrKit.notBlank(customerOrder.getAuditorId())) {
                auditor = userService.getById(customerOrder.getAuditorId());
                customerOrder.put("auditorName", auditor.getName());
            }
        }

        data.put("customerOrderPage", customerOrderPage);
    }
}
