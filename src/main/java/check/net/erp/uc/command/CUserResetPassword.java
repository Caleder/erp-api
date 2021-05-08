package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.interceptor.CurrentUser;
import check.net.erp.rc.service.LogService;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.UserService;
import check.net.erp.util.SimpleValidator;
import check.net.erp.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 重置密码
 */
@Command
public class CUserResetPassword extends BaseCommand {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private LogService logService;

    @Param(required = true)
    private long userId;
    @Param(required = true)
    private String password;
    @Param
    private CurrentUser currentUser;

    @Override
    protected void init() throws Exception {
        Assert.notFalse(SimpleValidator.validatePassword(password), "密码格式不正确！");
    }

    @Override
    protected void doCommand() throws Exception {
        User user = userService.getById(userId);
        Assert.notNull(user, "ID为【" + userId + "】的用户不存在！");

        user.setPassword(encoder.encode(password));
        userService.saveOrUpdate(user);

        // 记录日志
        logService.logUserResetPassword(currentUser.getId(), user.getUsername());
    }
}
