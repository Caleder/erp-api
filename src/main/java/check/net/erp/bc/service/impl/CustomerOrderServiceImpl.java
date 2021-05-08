package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.CustomerOrderDao;
import check.net.erp.bc.model.CustomerOrder;
import check.net.erp.bc.service.CustomerOrderService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderDao, CustomerOrder> implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao orderDao;

    @Override
    public Page<CustomerOrder> pageSearch(long current, long size, JSONObject query) {
        return orderDao.queryPage(new Page<>(current, size), query);
    }
}
