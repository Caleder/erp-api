package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.BizException;
import check.net.erp.base.Param;
import check.net.erp.interceptor.CurrentUser;
import check.net.erp.rc.service.LogService;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.UserService;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import check.net.erp.util.SimpleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 新增用户
 */
@Command
public class CUserAdd extends BaseCommand {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private LogService logService;

    @Param(required = true)
    private User user;
    @Param
    private CurrentUser currentUser;

    @Override
    protected void init() throws Exception {
        Assert.notBlank(user.getUsername(), "用户名不能为空！");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 20) {
            throw new BizException("用户名长度为4~20个字符！");
        }
        if (!SimpleValidator.validateUserName(user.getUsername())) {
            throw new BizException("用户名只能是中文、英文或数字！");
        }

        Assert.notTrue(SimpleValidator.validateMobile(user.getUsername()), "用户名不能为手机号！");

        Assert.notFalse(SimpleValidator.validatePassword(user.getPassword()), "密码为6~20位！");
        Assert.notFalse(SimpleValidator.validateTruename(user.getName()), "姓名不正确！");
        Assert.notFalse(SimpleValidator.validateMobile(user.getMobile()), "手机号不正确！");
    }

    @Override
    protected void doCommand() throws Exception {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new BizException("用户名为【" + user.getUsername() + "】的账号已存在！");
        }
        if (userService.findByMobile(user.getMobile()) != null) {
            throw new BizException("手机号为【" + user.getMobile() + "】的账号已存在！");
        }

        // 新增用户
        User persistedUser = new User();
        persistedUser.setUsername(user.getUsername());
        persistedUser.setPassword(encoder.encode(user.getPassword())); // 对密码加密
        persistedUser.setMobile(user.getMobile());
        persistedUser.setName(user.getName());
        userService.save(persistedUser);

        data.put("user", persistedUser);

        // 记录日志
        // logService.logUserAdd(currentUser.getId(), persistedUser.getUsername());
    }
}
