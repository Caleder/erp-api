package check.net.erp.rc.command;

import check.net.erp.rc.service.DictItemService;
import check.net.erp.rc.service.DictService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.rc.model.Dict;
import check.net.erp.rc.model.DictItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 字典项列表
 */
@Command
public class CDictItemList extends BaseCommand {

    @Autowired
    private DictService dictService;
    @Autowired
    private DictItemService itemService;

    @Param(required = true)
    private String dictCode;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Dict dict = dictService.findByCode(dictCode);
        Assert.notNull(dict, "编码为【" + dictCode + "】的字典不存在！");

        List<DictItem> itemList = itemService.findListByCode(dictCode);
        data.put("itemList", itemList);
    }
}
