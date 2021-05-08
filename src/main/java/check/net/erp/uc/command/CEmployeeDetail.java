package check.net.erp.uc.command;

import check.net.erp.uc.model.Employee;
import check.net.erp.uc.service.EmployeeService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 职员详情
 */
@Command
public class CEmployeeDetail extends BaseCommand {

    @Autowired
    private EmployeeService employeeService;

    @Param(required = true)
    private String employeeId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Employee employee = employeeService.getById(employeeId);
        Assert.notNull(employee, "ID为【" + employeeId + "】的职员不存在！");

        data.put("employee", employee);
    }
}
