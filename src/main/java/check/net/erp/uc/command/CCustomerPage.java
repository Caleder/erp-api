package check.net.erp.uc.command;

import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Category;
import check.net.erp.rc.service.CategoryService;
import check.net.erp.uc.model.CustomerContact;
import check.net.erp.uc.service.CustomerContactService;
import check.net.erp.uc.service.CustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 客户分页列表
 */
@Command
public class CCustomerPage extends BaseCommand {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerContactService contactService;
    @Autowired
    private CategoryService categoryService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current; // 页码
    @Param(defaultValue = Define.SIZE)
    private long size; // 每页数量

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Page<JSONObject> customerPage = customerService.pageSearch(current, size, query);

        for (JSONObject customer : customerPage.getRecords()) {
            Category category = categoryService.getById(customer.getString("categoryId"));
            if (category != null) {
                customer.put("categoryName", category.getName());
            }

            // 获取联系人列表
            List<CustomerContact> contactList = contactService.findListByCustomer(customer.getString("id"));
            customer.put("contactList", contactList);

            long beginBalance = customer.getLongValue("beginPrepaidAmount") - customer.getLongValue("beginReceivableAmount");
            customer.put("beginBalance", beginBalance);
        }

        data.put("customerPage", customerPage);
    }
}
