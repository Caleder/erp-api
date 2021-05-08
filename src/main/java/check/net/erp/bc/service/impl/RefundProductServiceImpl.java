package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.RefundProductDao;
import check.net.erp.bc.model.RefundProduct;
import check.net.erp.bc.service.RefundProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundProductServiceImpl extends ServiceImpl<RefundProductDao, RefundProduct> implements RefundProductService {
    @Override
    public List<RefundProduct> findListByRefund(String refundId) {
        return this.list(new QueryWrapper<RefundProduct>().eq("refundId", refundId));
    }

    @Override
    public void deleteByRefund(String refundId) {
        this.remove(new QueryWrapper<RefundProduct>().eq("refundId", refundId));
    }
}
