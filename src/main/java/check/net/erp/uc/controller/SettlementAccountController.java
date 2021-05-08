package check.net.erp.uc.controller;

import check.net.erp.uc.command.CSettlementAccountDelete;
import check.net.erp.uc.command.CSettlementAccountDetail;
import check.net.erp.uc.command.CSettlementAccountList;
import check.net.erp.uc.command.CSettlementAccountSave;
import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 结算账户控制器
 */
@Controller("/settlementAccount")
public class SettlementAccountController extends BaseController {

    /**
     * 结算账户列表
     */
    @PostMapping("/list")
    public Result list() {
        return doAction(CSettlementAccountList.class);
    }

    /**
     * 保存账户
     */
    @PostMapping("/save")
    public Result save() {
        return doAction(CSettlementAccountSave.class);
    }

    /**
     * 账户详情
     */
    @PostMapping("/detail")
    public Result detail() {
        return doAction(CSettlementAccountDetail.class);
    }

    /**
     * 账户删除
     */
    @PostMapping("/delete")
    public Result delete() {
        return doAction(CSettlementAccountDelete.class);
    }

}
