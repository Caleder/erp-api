package check.net.erp.uc.service;

import check.net.erp.uc.model.SupplierContact;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 供应商联系人服务
 */
public interface SupplierContactService extends IService<SupplierContact> {
    /**
     * 根据供应商删除
     * @param supplierId
     */
    void deleteBySupplier(String supplierId);

    /**
     * 根据客户获取联系人列表
     *
     * @param supplierId
     * @return
     */
    List<SupplierContact> findListBySupplier(String supplierId);
}
