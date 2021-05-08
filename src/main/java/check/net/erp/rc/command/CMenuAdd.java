package check.net.erp.rc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.rc.model.Menu;
import check.net.erp.rc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

@Command
public class CMenuAdd extends BaseCommand {

    @Autowired
    private MenuService menuService;

    @Param(required = true)
    private Menu menu;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        menuService.save(menu);

        data.put("menu", menu);
    }
}
