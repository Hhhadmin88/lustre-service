package com.evan.homemaking.aspect;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.exception.NoSuchAnnotationException;
import com.evan.homemaking.common.exception.UnAuthorizedException;
import com.evan.homemaking.security.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName AuthorityControlAspect
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 2:48
 */
@Component
@Aspect
@Slf4j
public class AuthorityControlAspect {
    /**
     * 用户信息增删改查权限，修改订单权限，发布招聘信息权限(管理员)
     */
    @Pointcut("@annotation(com.evan.homemaking.common.annotation.Authentication)")
    private void authenticate() {

    }

    @Before("authenticate()")
    public void doAuthenticate(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Authentication authentication = method.getAnnotation(Authentication.class);
        if (authentication == null) {
            log.error("Not found the annotation of the method that need to authenticate");
            throw new NoSuchAnnotationException("没有找到权限验证方法上的注解");
        }
        String currentUserRole = SecurityContextHolder.getContext().getAuthentication().getUserDetail().getUser().getRole();
        if (!(authentication.value().getRole()).equals(currentUserRole)) {
            System.out.println(currentUserRole);
            String methodName = method.getName();
            log.error("Current user is not have {} authority", methodName);
            throw new UnAuthorizedException("当前登录用户没有" + methodName + "操作权限");
        }
    }

}
