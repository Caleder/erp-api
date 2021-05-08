package check.net.erp.rc.controller;

import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.rc.command.CMenuAdd;
import check.net.erp.rc.command.CMenuList;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制器
 */
@Controller("/menu")
public class MenuController extends BaseController {

    @PostMapping("/add")
    public Result add() {
        return doAction(CMenuAdd.class);
    }

    @PostMapping("/list")
    public Result list() {
        return doAction(CMenuList.class);
    }

}
