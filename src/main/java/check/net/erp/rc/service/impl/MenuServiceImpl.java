package check.net.erp.rc.service.impl;

import check.net.erp.base.StrKit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import check.net.erp.base.*;
import check.net.erp.rc.dao.MenuDao;
import check.net.erp.rc.model.Menu;
import check.net.erp.rc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findListByParentId(String parentId) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (StrKit.isBlank(parentId)) {
            wrapper.isNull("parentId");
        } else {
            wrapper.eq("parentId", parentId);
        }

        wrapper.orderByAsc("sortNumber");

        return menuDao.selectList(wrapper);
    }
}
