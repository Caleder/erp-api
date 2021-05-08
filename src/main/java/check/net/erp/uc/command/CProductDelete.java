package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.service.ProductService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品删除
 */
@Command
public class CProductDelete extends BaseCommand {

    @Autowired
    private ProductService productService;

    @Param(required = true)
    private String productId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        productService.removeById(productId);
    }
}
