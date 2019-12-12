package com.evan.homemaking.security.resolver;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.common.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * @ClassName authenticateArgumentResolver
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 11:33
 */
@Deprecated
public class AuthenticateArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return (LoginParam.class.isAssignableFrom(parameterType) &&
                methodParameter.hasParameterAnnotation(Authentication.class));
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String singleRow;
        while ((singleRow = reader.readLine()) != null) {
            requestBody.append(singleRow);
        }
        LoginParam loginParam = JsonUtil.jsonToObject(requestBody.toString(), LoginParam.class);
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        return loginParam;
    }
}
