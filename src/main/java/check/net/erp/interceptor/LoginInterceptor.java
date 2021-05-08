package check.net.erp.interceptor;

import com.alibaba.fastjson.JSONArray;
import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;
import check.net.erp.base.Assert;
import check.net.erp.base.BizException;
import check.net.erp.base.JsonKit;
import check.net.erp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
@Log
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        Assert.notBlank(authorization, "未登陆！");

        if (!authorization.startsWith("Bearer ")) {
            throw new BizException("未登陆！");
        }

        String token = authorization.substring(7);

        Claims claims = jwtUtil.parseJwt(token);
        Assert.notNull(claims, "令牌不正确！");

        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(Long.parseLong(claims.getId()));
        currentUser.setUsername(claims.getSubject());

        String roles = claims.get("roles", String.class);
        currentUser.setRoles(JSONArray.parseArray(roles));
        request.setAttribute("currentUser", currentUser);

        log.info("currentUser:" + JsonKit.toJson(currentUser));

        return true;
    }
}
