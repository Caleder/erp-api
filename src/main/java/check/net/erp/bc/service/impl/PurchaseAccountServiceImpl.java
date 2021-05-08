package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.PurchaseAccountDao;
import check.net.erp.bc.model.PurchaseAccount;
import check.net.erp.bc.service.PurchaseAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseAccountServiceImpl extends ServiceImpl<PurchaseAccountDao, PurchaseAccount> implements PurchaseAccountService {
    @Override
    public List<PurchaseAccount> findListByPurchase(String purchaseId) {
        return this.list(new QueryWrapper<PurchaseAccount>().eq("purchaseId", purchaseId));
    }

    @Override
    public void deleteByPurchase(String purchaseId) {
        this.remove(new QueryWrapper<PurchaseAccount>().eq("purchaseId", purchaseId));
    }
}
