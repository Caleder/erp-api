package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.PurchaseProductDao;
import check.net.erp.bc.model.PurchaseProduct;
import check.net.erp.bc.service.PurchaseProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseProductServiceImpl extends ServiceImpl<PurchaseProductDao, PurchaseProduct> implements PurchaseProductService {
    @Override
    public List<PurchaseProduct> findListByPurchase(String purchaseId) {
        return this.list(new QueryWrapper<PurchaseProduct>().eq("purchaseId", purchaseId));
    }

    @Override
    public void deleteByPurchase(String purchaseId) {
        this.remove(new QueryWrapper<PurchaseProduct>().eq("purchaseId", purchaseId));
    }
}
