package check.net.erp.uc.command;

import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.BizException;
import check.net.erp.base.Param;
import check.net.erp.rc.service.LogService;
import check.net.erp.uc.model.User;
import check.net.erp.uc.service.UserService;
import check.net.erp.util.JwtUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.java.Log;
import check.net.erp.base.*;
import check.net.erp.base.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户登录
 */
@Log
@Command
public class CUserLogin extends BaseCommand {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LogService logService;

    /** 登录名 */
    private @Param(required = true) String loginName;
    /** 密码 */
    private @Param(required = true) String password;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        User user = userService.findByLoginName(loginName);
        Assert.notNull(user, "登录名为【" + loginName + "】的用户不存在！");

        if (!encoder.matches(password, user.getPassword())) {
            throw new BizException("密码不正确！");
        }

        // 生成令牌
        JSONArray roles = new JSONArray();
        roles.add("admin");
        String token = jwtUtil.createJwt(user.getId(), user.getUsername(), roles.toJSONString());

        log.info("登录成功！");

        logService.logUserLogin(user.getUsername());

        data.put("token", token);
    }
}
