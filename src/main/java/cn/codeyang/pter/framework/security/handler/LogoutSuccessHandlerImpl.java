package cn.codeyang.pter.framework.security.handler;

import cn.codeyang.pter.common.core.domain.model.LoginUser;
import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.common.utils.ServletUtils;
import cn.codeyang.pter.module.user.service.impl.TokenService;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */
@Configuration
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private final TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken(), loginUser.getUser().getId());
            //TODO-yangzy 记录用户退出日志
        }
        ServletUtils.renderString(response, JSON.toJSONString(R.ok(null, "退出成功")));
    }
}
