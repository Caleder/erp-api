package check.net.erp.uc.command;

import check.net.erp.base.*;
import check.net.erp.constant.Define;
import check.net.erp.rc.service.DictItemService;
import check.net.erp.uc.model.SettlementAccount;
import check.net.erp.uc.service.SettlementAccountService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户保存
 */
@Command
public class CSettlementAccountSave extends BaseCommand {

    @Autowired
    private SettlementAccountService accountService;
    @Autowired
    private DictItemService itemService;

    @Param(required = true)
    private SettlementAccount account;

    @Override
    protected void init() throws Exception {
        Assert.notBlank(account.getCode(), "账户编号不能为空！");
        Assert.notBlank(account.getName(), "账户名称不能为空！");
        Assert.notNull(account.getBalanceTime(), "余额日期不能为空！");
        Assert.notBlank(account.getType(), "账户类别不能为空！");
    }

    @Override
    protected void doCommand() throws Exception {
        if (!itemService.validate(account.getType(), Define.DICT_CODE_ACCOUNT_TYPE)) {
            throw new BizException("账户类型不正确！");
        }

        SettlementAccount persistedAccount;
        if (StrKit.notBlank(account.getId())) {
            persistedAccount = accountService.getById(account.getId());
            Assert.notNull(persistedAccount, "ID为【" + account.getId() + "】的账户不存在！");

        } else {
            persistedAccount = new SettlementAccount();
        }
        persistedAccount.setCode(account.getCode());
        persistedAccount.setName(account.getName());
        persistedAccount.setBalanceTime(account.getBalanceTime());
        persistedAccount.setBeginBalance(account.getBeginBalance());
        persistedAccount.setCurrentBalance(getCurrentBalance());
        persistedAccount.setType(account.getType());

        accountService.saveOrUpdate(persistedAccount);

        data.put("account", persistedAccount);
    }

    /**
     * 计算当前余额
     *
     * @return
     */
    private long getCurrentBalance() {
        return account.getBeginBalance();
    }
}
