package com.evan.lustre.aspect;

import com.evan.lustre.common.annotation.Authentication;
import com.evan.lustre.common.exception.NoSuchAnnotationExceptionAbstract;
import com.evan.lustre.common.exception.UnAuthorizedExceptionAbstract;
import com.evan.lustre.security.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

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
    @Pointcut("@within(com.evan.lustre.common.annotation.Authentication)||@annotation(com.evan.lustre.common.annotation.Authentication)")
    private void authenticate() {

    }

    @Before("authenticate()")
    public void doAuthenticate(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        Authentication authenticationOnClass = declaringClass.getAnnotation(Authentication.class);
        Authentication authenticationOnMethod = method.getAnnotation(Authentication.class);
        if (authenticationOnMethod == null && authenticationOnClass == null) {
            log.error("Not found the annotation of the class or method  that need to authenticate");
            throw new NoSuchAnnotationExceptionAbstract("未找到需要权限验证类或方法上标注的注解");
        }
        Authentication authentication = authenticationOnClass == null ? authenticationOnMethod : authenticationOnClass;
        Integer currentUserRole = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getUserDetail().getUser().getRole();
        if (!(authentication.value().getRole()).equals(currentUserRole)) {
            String methodName = method.getName();
            String className = declaringClass.getName();
            log.error("Current user is not have {}.{} authority", className, methodName);
            throw new UnAuthorizedExceptionAbstract("当前登录用户没有" + className + "." + methodName + "操作权限");
        }
    }

}
