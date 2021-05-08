package check.net.erp.rc.command;

import check.net.erp.rc.service.DictItemService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.rc.model.DictItem;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 字典详情
 */
@Command
public class CDictItemDetail extends BaseCommand {

    @Autowired
    private DictItemService itemService;

    @Param(required = true)
    private String itemId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        DictItem item = itemService.getById(itemId);
        Assert.notNull(item, "ID为【" + itemId + "】的字典项不存在！");

        data.put("item", item);
    }
}
