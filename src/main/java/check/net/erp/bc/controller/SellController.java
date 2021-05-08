package check.net.erp.bc.controller;

import check.net.erp.base.Controller;
import check.net.erp.bc.command.*;
import check.net.erp.base.BaseController;
import check.net.erp.base.Result;
import check.net.erp.bc.command.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 销货控制器
 */
@Controller("/sell")
public class SellController extends BaseController {

    /**
     * 分页列表
     */
    @PostMapping("/customerOrder/page")
    public Result customerOrderPage() {
        return doAction(CSellCustomerOrderPage.class);
    }

    /**
     * 客户订单详情
     */
    @PostMapping("/customerOrder/detail")
    public Result customerOrderDetail() {
        return doAction(CSellCustomerOrderDetail.class);
    }

    /**
     * 创建一个新的单据编号
     */
    @PostMapping("/customerOrder/createCode")
    public Result customerOrderCreateCode() {
        return doAction(CSellCustomerOrderCreateCode.class);
    }

    /**
     * 客户订单保存
     */
    @PostMapping("/customerOrder/save")
    public Result customerOrderSave() {
        return doAction(CSellCustomerOrderSave.class);
    }

    /**
     * 客户订单删除
     */
    @PostMapping("/customerOrder/delete")
    public Result customerOrderDelete() {
        return doAction(CSellCustomerOrderDelete.class);
    }

    /**
     * 客户订单切换审查
     */
    @PostMapping("/customerOrder/switchCheck")
    public Result customerOrderSwitchCheck() {
        return doAction(CSellCustomerOrderSwitchCheck.class);
    }

}
