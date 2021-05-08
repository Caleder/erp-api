package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.Refund;
import check.net.erp.bc.service.RefundAccountService;
import check.net.erp.bc.service.RefundProductService;
import check.net.erp.bc.service.RefundService;
import check.net.erp.bc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退货单删除
 */
@Command
@Transactional
public class CRefundDelete extends BaseCommand {

    @Autowired
    private RefundService refundService;
    @Autowired
    private RefundProductService productService;
    @Autowired
    private RefundAccountService accountService;

    @Param(required = true)
    private String refundId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Refund refund = refundService.getById(refundId);
        Assert.notNull(refund, "ID为【" + refundId + "】的退货单不存在！");

        refundService.removeById(refundId);

        productService.deleteByRefund(refundId);
        accountService.deleteByRefund(refundId);
    }
}
