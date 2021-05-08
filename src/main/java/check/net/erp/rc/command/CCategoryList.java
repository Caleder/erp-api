package check.net.erp.rc.command;

import check.net.erp.rc.service.CategoryService;
import com.alibaba.fastjson.JSONObject;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 分类列表
 */
@Command
public class CCategoryList extends BaseCommand {

    @Autowired
    private CategoryService categoryService;

    @Param(required = true)
    private Integer type; // 类型
    @Param(defaultValue = "{}")
    private JSONObject query; // 查询参数

    @Override
    protected void init() throws Exception {
        Assert.notFalse(Define.validateCategoryType(type), "类别不正确！");
    }

    @Override
    protected void doCommand() throws Exception {
        List<Category> categoryList = categoryService.findListByType(type, query, null);

        // 如果是商品，那么级联获取子类别
        if (type == Define.CATEGORY_TYPE_PRODUCT) {
            findSubCategoryList(categoryList);
        }

        data.put("categoryList", categoryList);
    }

    /**
     * 获取子列表
     *
     * @param categoryList
     */
    private void findSubCategoryList(List<Category> categoryList) {
        if (categoryList == null || categoryList.size() == 0) {
            return;
        }

        for (Category category : categoryList) {
            List<Category> childList = categoryService.findListByType(category.getType(), query, category.getId());
            category.put("childList", childList);

            findSubCategoryList(childList);
        }
    }
}
