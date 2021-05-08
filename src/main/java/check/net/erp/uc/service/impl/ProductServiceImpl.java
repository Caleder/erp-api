package check.net.erp.uc.service.impl;

import check.net.erp.uc.dao.ProductDao;
import check.net.erp.uc.model.Product;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import check.net.erp.base.StrKit;
import check.net.erp.uc.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Override
    public Page<Product> pageSearch(long current, long size, JSONObject query) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        if (StrKit.notBlank(query.getString("categoryId"))) {
            wrapper.eq("categoryId", query.getString("categoryId"));
        }
        if (StrKit.notBlank(query.getString("name"))) {
            wrapper.and(nameWrapper ->
                    nameWrapper.like("code", query.getString("name"))
                    .or()
                    .like("name", query.getString("name"))
                    .or()
                    .like("spec", query.getString("name")));
        }

        wrapper.orderByDesc("createdTime");

        return this.page(new Page<>(current, size), wrapper);
    }

    @Override
    public Product findByCode(String code) {
        return this.getOne(new QueryWrapper<Product>().eq("code", code));
    }
}
