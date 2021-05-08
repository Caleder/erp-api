package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.RefundDao;
import check.net.erp.bc.model.Refund;
import check.net.erp.bc.service.RefundService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl extends ServiceImpl<RefundDao, Refund> implements RefundService {

    @Autowired
    private RefundDao refundDao;

    @Override
    public Page<Refund> pageSearch(long current, long size, JSONObject query) {
        return refundDao.queryPage(new Page<>(current, size), query);
    }
}
