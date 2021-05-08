package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.uc.model.Employee;
import check.net.erp.uc.service.EmployeeService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存职员
 */
@Command
public class CEmployeeSave extends BaseCommand {

    @Autowired
    private EmployeeService employeeService;

    @Param(required = true)
    private Employee employee;

    @Override
    protected void init() throws Exception {
        Assert.notBlank(employee.getCode(), "职员编号不能为空！");
        Assert.notBlank(employee.getName(), "职员名称不能为空！");
    }

    @Override
    protected void doCommand() throws Exception {
        Employee persistedEmployee;
        if (StrKit.isBlank(employee.getId())) {
            persistedEmployee = new Employee();
            persistedEmployee.setActive(true);

        } else {
            persistedEmployee = employeeService.getById(employee.getId());
            Assert.notNull(persistedEmployee, "ID为【" + employee.getId() + "】的职员不存在！");
        }

        persistedEmployee.setCode(employee.getCode());
        persistedEmployee.setName(employee.getName());

        employeeService.saveOrUpdate(persistedEmployee);

        data.put("employee", persistedEmployee);
    }
}
