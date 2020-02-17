package com.evan.lustre.security.authentication;

import com.evan.lustre.security.support.UserDetail;
import org.springframework.lang.NonNull;

/**
 * @ClassName Authentication
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:36
 */
public interface Authentication {
    /**
     * Get user detail.
     *
     * @return user detail
     */
    @NonNull
    UserDetail getUserDetail();
}
