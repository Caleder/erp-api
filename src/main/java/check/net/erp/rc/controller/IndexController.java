package check.net.erp.rc.controller;

import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.rc.command.CGetSystemConfiguration;
import check.net.erp.rc.command.CSetSystemConfiguration;
import check.net.erp.rc.command.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 首页控制器
 */
@Controller("/")
public class IndexController extends BaseController {

    /**
     * 获取系统设置
     */
    @PostMapping("/getSystemConfiguration")
    public Result getSystemConfiguration() {
        return doAction(CGetSystemConfiguration.class);
    }

    /**
     * 设置系统设置
     */
    @PostMapping("/setSystemConfiguration")
    public Result setSystemConfiguration() {
        return doAction(CSetSystemConfiguration.class);
    }

}
