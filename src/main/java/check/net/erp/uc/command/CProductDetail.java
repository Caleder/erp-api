package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.rc.service.CategoryService;
import check.net.erp.uc.model.Product;
import check.net.erp.uc.service.ProductService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品详情
 */
@Command
public class CProductDetail extends BaseCommand {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Param(required = true)
    private String productId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Product product = productService.getById(productId);
        Assert.notNull(product, "ID为【" + productId + "】的商品不存在！");

        if (StrKit.notBlank(product.getCategoryId())) {
            product.put("categoryName", categoryService.getById(product.getCategoryId()).getName());
        }

        data.put("product", product);
    }
}
