package check.net.erp.rc.command;

import check.net.erp.constant.Define;
import check.net.erp.rc.service.CategoryService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.base.command.Command;
import check.net.erp.rc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存分类
 */
@Command
public class CCategorySave extends BaseCommand {

    @Autowired
    private CategoryService categoryService;

    @Param(required = true)
    private Category category;

    @Override
    protected void init() throws Exception {
        Assert.notFalse(Define.validateCategoryType(category.getType()), "类别不正确！");

        Assert.notBlank(category.getName(), "名称不能为空！");
    }

    @Override
    protected void doCommand() throws Exception {
        Category persistedCategory;
        if (category.getId() == null) {
            persistedCategory = new Category();
            persistedCategory.setType(category.getType());
        } else {
            persistedCategory = categoryService.getById(category.getId());
            Assert.notNull(persistedCategory, "ID为【" + category.getId() + "】的分类不存在！");
        }
        persistedCategory.setName(category.getName());

        if (category.getType() == Define.CATEGORY_TYPE_PRODUCT && StrKit.notBlank(category.getParentId())) {
            Category parent = categoryService.getById(category.getParentId());
            Assert.notNull(parent, "ID为【" + category.getParentId() + "】的分类不存在！");

            persistedCategory.setParentId(category.getParentId());
        }

        categoryService.saveOrUpdate(persistedCategory);

        data.put("category", persistedCategory);
    }
}
