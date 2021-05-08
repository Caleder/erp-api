package check.net.erp.rc.controller;

import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.rc.command.CDictItemDelete;
import check.net.erp.rc.command.CDictItemDetail;
import check.net.erp.rc.command.CDictItemList;
import check.net.erp.rc.command.CDictItemSave;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 字典控制器
 */
@Controller("/dict")
public class DictController extends BaseController {

    /**
     * 获取字典项列表
     */
    @PostMapping("/itemList")
    public Result itemList() {
        return doAction(CDictItemList.class);
    }

    /**
     * 字典项详情
     */
    @PostMapping("/itemDetail")
    public Result itemDetail() {
        return doAction(CDictItemDetail.class);
    }

    /**
     * 保存字典项
     */
    @PostMapping("itemSave")
    public Result itemSave() {
        return doAction(CDictItemSave.class);
    }

    /**
     * 删除字典项
     */
    @PostMapping("itemDelete")
    public Result itemDelete() {
        return doAction(CDictItemDelete.class);
    }

}
