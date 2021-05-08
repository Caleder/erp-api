package check.net.erp.uc.dao;

import check.net.erp.uc.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * 用户Dao
 */
@Component
public interface UserDao extends BaseMapper<User> {
}
