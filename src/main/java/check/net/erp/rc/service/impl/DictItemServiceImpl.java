package check.net.erp.rc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import check.net.erp.rc.dao.DictItemDao;
import check.net.erp.rc.model.DictItem;
import check.net.erp.rc.service.DictItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典项服务实现
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemDao, DictItem> implements DictItemService {
    @Override
    public List<DictItem> findListByCode(String dictCode) {
        return this.list(new QueryWrapper<DictItem>().eq("dictCode", dictCode).orderByAsc("sortNumber", "createdTime"));
    }

    @Override
    public boolean validate(String id, String dictCode) {
        return this.getOne(new QueryWrapper<DictItem>().eq("id", id).eq("dictCode", dictCode)) != null;
    }
}
