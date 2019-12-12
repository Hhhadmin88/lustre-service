package com.evan.homemaking.security.context;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @ClassName SecurityContextHolder
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:35
 */
public class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> CONTEXT_HOLDER=new ThreadLocal<>();

    private SecurityContextHolder() {
    }

    /**
     * Gets context.
     *
     * @return security context
     */
    @NonNull
    public static SecurityContext getContext(){
        //Get from thread local
        SecurityContext context = CONTEXT_HOLDER.get();
        if (context==null){
            // If no context is available now then create an empty context
            context = createEmptyContext();
            // Set to thread local
            CONTEXT_HOLDER.set(context);
        }
        System.out.println("当前线程:"+Thread.currentThread());
        return context;
    }

    /**
     * Sets security context.
     *
     * @param context security context
     */
    public static void setContext(@Nullable SecurityContext context) {
        System.out.println("当前线程:"+Thread.currentThread());
        CONTEXT_HOLDER.set(context);
    }

    /**
     * Clears context.
     */
    public static void clearContext() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * Creates an empty security context.
     *
     * @return an empty security context
     */
    @NonNull
    private static SecurityContext createEmptyContext() {
        return new SecurityContextImpl(null);
    }
}
