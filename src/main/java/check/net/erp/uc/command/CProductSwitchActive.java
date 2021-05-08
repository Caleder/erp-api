package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.uc.model.Product;
import check.net.erp.uc.service.ProductService;
import check.net.erp.base.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品启停
 */
@Command
public class CProductSwitchActive extends BaseCommand {

    @Autowired
    private ProductService productService;

    @Param(required = true)
    private String productId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Product product = productService.getById(productId);
        Assert.notNull(product, "ID为【" + productId + "】的商品不存在！");

        product.setActive(!product.isActive());
        productService.saveOrUpdate(product);

        data.put("product", product);
    }
}
