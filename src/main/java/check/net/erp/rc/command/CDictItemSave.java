package check.net.erp.rc.command;

import check.net.erp.base.*;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Const;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Dict;
import check.net.erp.rc.model.DictItem;
import check.net.erp.rc.service.DictItemService;
import check.net.erp.rc.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 更新字典项
 */
@Command
public class CDictItemSave extends BaseCommand {

    @Autowired
    private DictService dictService;
    @Autowired
    private DictItemService itemService;

    @Param(required = true)
    private DictItem item;

    @Override
    protected void init() throws Exception {
        Assert.notBlank(item.getName(), "名称不能为空！");
        Assert.notBlank(item.getDictCode(), "字典编码不能为空！");

        System.out.println(Const.DEV_MODE);
        if (!Const.DEV_MODE && (!item.getDictCode().equals(Define.DICT_CODE_UNIT) && !item.getDictCode().equals(Define.DICT_CODE_SETTLEMENT))) {
            throw new BizException("没有权限！");
        }
    }

    @Override
    protected void doCommand() throws Exception {
        Dict dict = dictService.findByCode(item.getDictCode());
        Assert.notNull(dict, "编码为【" + item.getDictCode() + "】的字典不存在！");

        DictItem persistedItem;
        if (StrKit.isBlank(item.getId())) {
            persistedItem = new DictItem();
            persistedItem.setDictCode(item.getDictCode());
            persistedItem.setSortNumber(0);

        } else {
            persistedItem = itemService.getById(item.getId());
            Assert.notNull(persistedItem, "ID为【" + item.getId() + "】的字典项不存在！");
        }
        persistedItem.setName(item.getName());

        itemService.saveOrUpdate(persistedItem);

        data.put("item", persistedItem);
    }
}
