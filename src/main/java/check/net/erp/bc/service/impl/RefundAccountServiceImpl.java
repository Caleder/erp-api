package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.RefundAccountDao;
import check.net.erp.bc.model.RefundAccount;
import check.net.erp.bc.service.RefundAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundAccountServiceImpl extends ServiceImpl<RefundAccountDao, RefundAccount> implements RefundAccountService {
    @Override
    public List<RefundAccount> findListByRefund(String refundId) {
        return this.list(new QueryWrapper<RefundAccount>().eq("refundId", refundId));
    }

    @Override
    public void deleteByRefund(String refundId) {
        this.remove(new QueryWrapper<RefundAccount>().eq("refundId", refundId));
    }
}
