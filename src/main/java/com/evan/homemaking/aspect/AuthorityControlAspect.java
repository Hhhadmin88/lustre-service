package com.evan.homemaking.aspect;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.Role;
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
public class AuthorityControlAspect {
    /**
     * 用户信息增删改查权限，修改订单权限，发布招聘信息权限(管理员)
     */
    @Pointcut("@annotation(com.evan.homemaking.common.annotation.Authentication)")
    private void authenticate() {

    }

    @Before("authenticate()")
    public void doAuthenticate(JoinPoint joinPoint) throws NoSuchMethodException {
        //被注解标记的方法 所在的controller
        Object target = joinPoint.getTarget();
        //被注解标记的方法签名(被代理)
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //注解标记的方法名
        String methodName = joinPoint.getSignature().getName();
        //被注解标记的Method对象
        Method method = methodSignature.getMethod();
        //方法上标记的注解
        Authentication authentication = method.getAnnotation(Authentication.class);
        if (authentication != null) {
            Role value = authentication.value();
            value.getRole();
        }
    }

}
