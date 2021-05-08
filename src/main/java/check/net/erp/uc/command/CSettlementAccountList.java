package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.rc.model.DictItem;
import check.net.erp.rc.service.DictItemService;
import check.net.erp.uc.model.SettlementAccount;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 账户列表
 */
@Command
public class CSettlementAccountList extends BaseCommand {

    @Autowired
    private SettlementAccountService accountService;
    @Autowired
    private DictItemService itemService;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        List<SettlementAccount> accountList = accountService.list();

        for (SettlementAccount account : accountList) {
            DictItem item = itemService.getById(account.getType());
            account.put("typeName", item.getName());
        }

        data.put("accountList", accountList);
    }
}
