package check.net.erp.uc.command;

import check.net.erp.uc.service.EmployeeService;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 职员删除
 */
@Command
public class CEmployeeDelete extends BaseCommand {

    @Autowired
    private EmployeeService employeeService;

    @Param(required = true)
    private String employeeId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        employeeService.removeById(employeeId);
    }
}
