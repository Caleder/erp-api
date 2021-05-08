package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.service.RefundAccountService;
import check.net.erp.bc.service.RefundProductService;
import check.net.erp.bc.service.RefundService;
import check.net.erp.base.BaseCommand;
import check.net.erp.bc.model.Refund;
import check.net.erp.bc.model.RefundAccount;
import check.net.erp.bc.model.RefundProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 退货单详情
 */
@Command
public class CRefundDetail extends BaseCommand {

    @Autowired
    private RefundService refundService;
    @Autowired
    private RefundProductService productService;
    @Autowired
    private RefundAccountService accountService;

    @Param(required = true)
    private String refundId; // 退货ID

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Refund refund = refundService.getById(refundId);
        Assert.notNull(refund, "ID为【" + refundId + "】的退货单不存在！");

        List<RefundProduct> productList = productService.findListByRefund(refund.getId());
        refund.put("productList", productList);

        List<RefundAccount> accountList = accountService.findListByRefund(refund.getId());
        refund.put("accountList", accountList);

        data.put("refund", refund);
    }
}
