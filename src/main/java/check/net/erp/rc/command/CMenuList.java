package check.net.erp.rc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.JsonKit;
import check.net.erp.base.Param;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.interceptor.CurrentUser;
import check.net.erp.rc.model.Menu;
import check.net.erp.rc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Command
public class CMenuList extends BaseCommand {

    @Autowired
    private MenuService menuService;

    private @Param
    CurrentUser currentUser;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        System.out.println("currentUser: " + JsonKit.toJson(currentUser));

        List<Menu> menuList = menuService.findListByParentId(null);
        for (Menu menu : menuList) {
            List<Menu> childList = menuService.findListByParentId(menu.getId());
            if (childList != null && childList.size() > 0) {
                menu.put("childList", childList);
            }
        }

        data.put("menuList", menuList);
    }
}
