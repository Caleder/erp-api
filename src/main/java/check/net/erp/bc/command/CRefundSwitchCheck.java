package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.model.Refund;
import check.net.erp.bc.service.RefundService;
import check.net.erp.base.BaseCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退货单切换审查
 */
@Command
public class CRefundSwitchCheck extends BaseCommand {

    @Autowired
    private RefundService refundService;

    @Param(required = true)
    private String refundId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Refund refund = refundService.getById(refundId);
        Assert.notNull(refund, "ID为【" + refundId + "】的退货单不存在！");

        refund.setChecked(!refund.isChecked());
        refundService.saveOrUpdate(refund);

        data.put("refund", refund);
    }
}
