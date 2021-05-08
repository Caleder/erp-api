package check.net.erp.uc.controller;

import check.net.erp.uc.command.*;
import check.net.erp.base.BaseController;
import check.net.erp.base.Controller;
import check.net.erp.base.Result;
import check.net.erp.uc.command.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 商品控制器
 */
@Controller("/product")
public class ProductController extends BaseController {

    /**
     * 分页列表
     */
    @PostMapping("/page")
    public Result page() {
        return doAction(CProductPage.class);
    }

    /**
     * 商品保存
     */
    @PostMapping("/save")
    public Result save() {
        return doAction(CProductSave.class);
    }

    /**
     * 商品详情
     */
    @PostMapping("/detail")
    public Result detail() {
        return doAction(CProductDetail.class);
    }

    /**
     * 商品删除
     */
    @PostMapping("/delete")
    public Result delete() {
        return doAction(CProductDelete.class);
    }

    /**
     * 商品启停
     */
    @PostMapping("/switchActive")
    public Result switchActive() {
        return doAction(CProductSwitchActive.class);
    }

}
