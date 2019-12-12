package com.evan.homemaking.security.support;

import com.evan.homemaking.common.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * @ClassName UserDetail
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/7 20:25
 */
@AllArgsConstructor
public class UserDetail {

    private User user;

    /**
     * Gets user info.
     *
     * @return user info
     */
    @NonNull
    public User getUser() {
        return user;
    }

    /**
     * Sets user info.
     *
     * @param user user info
     */
    public void setUser(User user) {
        this.user = user;
    }
}
