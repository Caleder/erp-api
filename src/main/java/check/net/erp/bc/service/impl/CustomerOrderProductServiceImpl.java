package check.net.erp.bc.service.impl;

import check.net.erp.bc.dao.CustomerOrderProductDao;
import check.net.erp.bc.model.CustomerOrderProduct;
import check.net.erp.bc.service.CustomerOrderProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderProductServiceImpl extends ServiceImpl<CustomerOrderProductDao, CustomerOrderProduct> implements CustomerOrderProductService {
    @Override
    public List<CustomerOrderProduct> findListByCustomerOrder(String customerOrderId) {
        return this.list(new QueryWrapper<CustomerOrderProduct>().eq("customerOrderId", customerOrderId));
    }

    @Override
    public void deleteByCustomerOrder(String customerOrderId) {
        this.remove(new QueryWrapper<CustomerOrderProduct>().eq("customerOrderId", customerOrderId));
    }
}
