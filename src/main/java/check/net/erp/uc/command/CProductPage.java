package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Category;
import check.net.erp.rc.model.DictItem;
import check.net.erp.rc.service.CategoryService;
import check.net.erp.rc.service.DictItemService;
import check.net.erp.uc.model.Product;
import check.net.erp.uc.service.ProductService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品分页
 */
@Command
public class CProductPage extends BaseCommand {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DictItemService itemService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current; // 页码
    @Param(defaultValue = Define.SIZE)
    private long size; // 每页数量

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Page<Product> productPage = productService.pageSearch(current, size, query);

        for (Product product : productPage.getRecords()) {
            if (StrKit.notBlank(product.getCategoryId())) {
                Category category = categoryService.getById(product.getCategoryId());
                product.put("categoryName", category.getName());
            }

            if (StrKit.notBlank(product.getUnitId())) {
                DictItem unit = itemService.getById(product.getUnitId());
                product.put("unitName", unit.getName());
            }

            // TODO 计算当前库存
            product.put("stock", 2345.67);
        }

        data.put("productPage", productPage);
    }
}
