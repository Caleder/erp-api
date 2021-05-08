package check.net.erp.uc.service.impl;

import check.net.erp.uc.dao.SupplierContactDao;
import check.net.erp.uc.model.SupplierContact;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import check.net.erp.uc.service.SupplierContactService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商联系人服务
 */
@Service
public class SupplierContactServiceImp extends ServiceImpl<SupplierContactDao, SupplierContact> implements SupplierContactService {
    @Override
    public void deleteBySupplier(String supplierId) {
        this.remove(new QueryWrapper<SupplierContact>().eq("supplierId", supplierId));
    }

    @Override
    public List<SupplierContact> findListBySupplier(String supplierId) {
        return this.list(new QueryWrapper<SupplierContact>().eq("supplierId", supplierId));
    }
}
