package check.net.erp.uc.service.impl;

import check.net.erp.uc.dao.CustomerContactDao;
import check.net.erp.uc.model.CustomerContact;
import check.net.erp.uc.service.CustomerContactService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户联系人服务
 */
@Service
public class CustomerContactServiceImp extends ServiceImpl<CustomerContactDao, CustomerContact> implements CustomerContactService {
    @Override
    public void deleteByCustomer(String customerId) {
        this.remove(new QueryWrapper<CustomerContact>().eq("customerId", customerId));
    }

    @Override
    public List<CustomerContact> findListByCustomer(String customerId) {
        return this.list(new QueryWrapper<CustomerContact>().eq("customerId", customerId));
    }
}
