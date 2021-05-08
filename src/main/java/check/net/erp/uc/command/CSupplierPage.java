package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.rc.service.CategoryService;
import check.net.erp.uc.service.SupplierContactService;
import check.net.erp.uc.service.SupplierService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 供应商分页列表
 */
@Command
public class CSupplierPage extends BaseCommand {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierContactService contactService;
    @Autowired
    private CategoryService categoryService;

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
        Page<JSONObject> supplierPage = supplierService.pageSearch(current, size, query);

        for (JSONObject supplier : supplierPage.getRecords()) {
            Category category = categoryService.getById(supplier.getString("categoryId"));
            if (category != null) {
                supplier.put("categoryName", category.getName());
            }

            long beginBalance = supplier.getLongValue("beginPrepaidAmount") - supplier.getLongValue("beginReceivableAmount");
            supplier.put("beginBalance", beginBalance);
        }

        data.put("supplierPage", supplierPage);
    }
}
