package check.net.erp.uc.dao;

import check.net.erp.uc.model.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * 职员Dao
 */
@Component
public interface EmployeeDao extends BaseMapper<Employee> {
}
