package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.PurchaseDao;
import check.net.erp.bc.model.Purchase;
import check.net.erp.bc.service.PurchaseService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, Purchase> implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public Page<Purchase> pageSearch(long current, long size, JSONObject query) {
        return purchaseDao.queryPage(new Page<>(current, size), query);
    }
}
