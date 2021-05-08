package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.base.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户删除
 */
@Command
public class CSettlementAccountDelete extends BaseCommand {

    @Autowired
    private SettlementAccountService accountService;

    @Param(required = true)
    private String accountId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        accountService.removeById(accountId);
    }
}
