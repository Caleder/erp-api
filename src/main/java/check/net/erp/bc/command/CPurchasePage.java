package check.net.erp.bc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.bc.service.PurchaseService;
import check.net.erp.constant.Define;
import check.net.erp.uc.model.Supplier;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.SupplierService;
import check.net.erp.uc.service.UserService;
import check.net.erp.util.SimpleValidator;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.StrKit;
import check.net.erp.bc.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 购货单分页列表
 */
@Command
public class CPurchasePage extends BaseCommand {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private SupplierService supplierService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current;
    @Param(defaultValue = Define.SIZE)
    private long size;

    @Override
    protected void init() throws Exception {
        String startDate = query.getString("startDate");
        if (StrKit.notBlank(startDate)) {
            Assert.notFalse(SimpleValidator.validateDate(startDate), "起始时间不正确！");
        }

        String endDate = query.getString("endDate");
        if (StrKit.notBlank(endDate)) {
            Assert.notFalse(SimpleValidator.validateDate(endDate), "结束时间不正确！");
        }
    }

    @Override
    protected void doCommand() throws Exception {
        Page<Purchase> purchasePage = purchaseService.pageSearch(current, size, query);

        Supplier supplier;
        User auditor;
        User lister;
        for (Purchase purchase : purchasePage.getRecords()) {
            supplier = supplierService.getById(purchase.getSupplierId());
            purchase.put("supplierName", supplier.getName());

            if (StrKit.notBlank(purchase.getListerId())) {
                lister = userService.getById(purchase.getListerId());
                purchase.put("listerName", lister.getName());
            }

            if (StrKit.notBlank(purchase.getAuditorId())) {
                auditor = userService.getById(purchase.getAuditorId());
                purchase.put("auditorName", auditor.getName());
            }
        }

        data.put("purchasePage", purchasePage);
    }
}
