package check.net.erp.uc.command;

import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户列表
 *
 */
@Command
public class CUserPage extends BaseCommand {

    @Autowired
    private UserService userService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current; // 页码
    @Param(defaultValue = Define.SIZE)
    private long size; // 每页数量

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        Page<User> userPage = userService.pageSearch(current, size, query);

        data.put("userPage", userPage);
    }
}
