package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.model.SettlementAccount;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户详情
 */
@Command
public class CSettlementAccountDetail extends BaseCommand {

    @Autowired
    private SettlementAccountService accountService;

    @Param(required = true)
    private String accountId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        SettlementAccount account = accountService.getById(accountId);
        Assert.notNull(account, "ID为【" + accountId + "】的账户不存在！");

        data.put("account", account);
    }
}
