package check.net.erp.rc.controller;

import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.rc.command.CLogPage;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 日志控制器
 */
@Controller("/log")
public class LogController extends BaseController {

    /**
     * 分页列表
     */
    @PostMapping("/page")
    public Result page() {
        return doAction(CLogPage.class);
    }

}
