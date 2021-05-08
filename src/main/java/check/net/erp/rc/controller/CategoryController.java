package check.net.erp.rc.controller;

import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.rc.command.CCategoryDelete;
import check.net.erp.rc.command.CCategoryDetail;
import check.net.erp.rc.command.CCategoryList;
import check.net.erp.rc.command.CCategorySave;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 类别控制器
 */
@Controller("/category")
public class CategoryController extends BaseController {

    /**
     * 列表
     *
     * @return
     */
    @PostMapping("/list")
    public Result list() {
        return doAction(CCategoryList.class);
    }

    /**
     * 新增或修改
     *
     * @return
     */
    @PostMapping("/save")
    public Result save() {
        return doAction(CCategorySave.class);
    }

    /**
     * 删除
     *
     * @return
     */
    @PostMapping("/delete")
    public Result delete() {
        return doAction(CCategoryDelete.class);
    }

    /**
     * 根据Id获取
     */
    @PostMapping("/detail")
    public Result detail() {
        return doAction(CCategoryDetail.class);
    }


}
