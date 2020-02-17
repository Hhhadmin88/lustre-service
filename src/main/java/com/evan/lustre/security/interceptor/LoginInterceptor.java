package com.evan.lustre.security.interceptor;

import com.evan.lustre.common.cache.UserCache;
import com.evan.lustre.common.exception.BadRequestExceptionAbstract;
import com.evan.lustre.common.exception.NotFoundExceptionAbstract;
import com.evan.lustre.common.exception.UnAuthorizedExceptionAbstract;
import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.common.utils.SecurityUtil;
import com.evan.lustre.security.context.SecurityContextHolder;
import com.evan.lustre.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.evan.lustre.common.consts.Security.*;

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
        String userName = request.getHeader(REQUEST_USER_ACCOUNT);
        if (StringUtils.isBlank(userName)) {
            throw new BadRequestExceptionAbstract("用户名信息不能为空");
        }

        return Optional.of(userName).map(name -> {
            //Get the current requesting user.
            User currentRequestUser = Optional.ofNullable(userService.getOneUserByUserName(userName))
                    .orElseThrow(() -> new NotFoundExceptionAbstract("当前请求的用户不存在"));
            String cachedToken = userCache.getMultiple(SecurityUtil.buildAccessTokenKey(currentRequestUser), String.class)
                    .orElseThrow(() -> new UnAuthorizedExceptionAbstract("当前用户" + userName + "还未登录，请先进行登录"));
            String accessToken = request.getHeader(REQUEST_ACCESS_TOKEN);
            if (StringUtils.isBlank(accessToken)) {
                throw new UnAuthorizedExceptionAbstract("请求中缺少token验证信息");
            }
            if (accessToken.equals(cachedToken)) {
                userService.storeCurrentUser(currentRequestUser);
                return true;
            } else {
                log.error("The current user is not authorized for this operation.");
                throw new UnAuthorizedExceptionAbstract("访问权限校验失败，请填写正确的token信息");
            }
        }).get();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContextHolder.clearContext();
    }
}
