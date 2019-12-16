package com.evan.homemaking.interceptor;

import com.evan.homemaking.common.cache.UserCache;
import com.evan.homemaking.common.exception.*;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.utils.SecurityUtil;
import com.evan.homemaking.security.context.SecurityContextHolder;
import com.evan.homemaking.service.AuthenticateService;
import com.evan.homemaking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.evan.homemaking.common.consts.Security.*;

/**
 * @ClassName LoginInterceptor
 * @Description Login interceptor for determine the login status of the current user.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 1:39
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final UserCache userCache;

    private final UserService userService;

    public LoginInterceptor(UserCache userCache, UserService userService) {
        this.userCache = userCache;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        if (LOGIN_REQUEST_URI.equals(requestURI) || REGISTER_REQUEST_URI.equals(requestURI)) {
            return true;
        }
        String userName = request.getHeader(REQUEST_USERNAME_IN_HEADER);
        Optional.ofNullable(userName).orElseThrow(() -> new BadRequestException("请求中缺少用户名信息"));
        //获取当前发起请求的user
        Optional<User> currentRequestUser = userService.getCurrentRequestUser(userName);
        String cachedToken = userCache.getMultiple(SecurityUtil.buildAccessTokenKey(currentRequestUser.get()), String.class)
                .orElseThrow(() -> new NotLoginException("当前用户" + userName + "还未登录，请先进行登录"));
        userService.storeCurrentUser(currentRequestUser.get());
        String accessToken = request.getHeader(REQUEST_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
            throw new BadRequestException("请求中缺少token验证信息");
        }
        if (accessToken.equals(cachedToken)) {
            return true;
        } else {
            log.error("The current user is not authorized for this operation.");
            throw new UnAuthorizedException("访问权限校验失败，请填写正确的校验信息");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContextHolder.clearContext();
    }
}
