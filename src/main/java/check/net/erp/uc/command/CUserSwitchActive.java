package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.interceptor.CurrentUser;
import check.net.erp.rc.service.LogService;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.UserService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 切换是否启用
 */
@Command
public class CUserSwitchActive extends BaseCommand {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @Param(required = true)
    private long userId;
    @Param
    private CurrentUser currentUser;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        User user = userService.getById(userId);
        Assert.notNull(user, "ID为【" + userId + "】的用户不存在！");

        user.setActive(!user.isActive());
        userService.saveOrUpdate(user);

        // 记录日志
        if (user.isActive()) {
            logService.logUserActive(currentUser.getId(), user.getUsername());
        } else {
            logService.logUserDeactive(currentUser.getId(), user.getUsername());
        }

        data.put("active", user.isActive());
    }
}
