package check.net.erp.uc.controller;

import check.net.erp.uc.command.*;
import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.uc.command.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 客户控制器
 */
@Controller("/customer")
public class CustomerController extends BaseController {

    /**
     * 分页列表
     */
    @PostMapping("/page")
    public Result page() {
        return doAction(CCustomerPage.class);
    }

    /**
     * 保存客户
     */
    @PostMapping("/save")
    public Result save() {
        return doAction(CCustomerSave.class);
    }

    /**
     * 客户详情
     */
    @PostMapping("/detail")
    public Result detail() {
        return doAction(CCustomerDetail.class);
    }

    /**
     * 客户删除
     */
    @PostMapping("/delete")
    public Result delete() {
        return doAction(CCustomerDelete.class);
    }

    /**
     * 客户启停
     */
    @PostMapping("/switchActive")
    public Result switchActive() {
        return doAction(CCustomerSwitchActive.class);
    }

    /**
     * 导入Excel
     */
    @PostMapping("/importExcel")
    public Result importExcel() {
        return doAction(CCustomerImportExcel.class);
    }

    /**
     * 导出Excel
     */
    @PostMapping("/exportExcel")
    public Result exportExcel() {
        return doAction(CCustomerExportExcel.class);
    }

}
