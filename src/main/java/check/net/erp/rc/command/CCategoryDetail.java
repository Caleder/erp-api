package check.net.erp.rc.command;

import check.net.erp.rc.service.CategoryService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.rc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID获取分类
 */
@Command
public class CCategoryDetail extends BaseCommand {

    @Autowired
    private CategoryService categoryService;

    @Param(required = true)
    private String categoryId;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Category category = categoryService.getById(categoryId);
        Assert.notNull(category, "ID为【" + categoryId + "】的分类不存在！");

        data.put("category", category);
    }
}
