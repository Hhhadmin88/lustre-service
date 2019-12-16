package com.evan.homemaking.service;

import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.RegisterParam;
import com.evan.homemaking.service.base.CrudService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName UserService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:20
 */
@Service
public interface UserService extends CrudService<User, Integer> {

    /**
     * Gets user by accountId.
     *
     * @param accountId accountId of user.
     * @return an user.
     */
    @NonNull
    User getByAccountId(@NonNull String accountId);

    /**
     * Gets user by email.
     *
     * @param email email of user.
     * @return an user.
     */
    @NonNull
    User getByEmail(@NonNull String email);

    /**
     * Register an user.
     *
     * @param registerParam
     */
    void registerUser(@NonNull RegisterParam registerParam);

    /**
     * Check the password of loginParam  is match the user password.
     *
     * @param user          user info must not be null.
     * @param plainPassword The password in loginParam.
     * @return boolean result of match.
     */
    boolean matchPassword(@NonNull User user, @NonNull String plainPassword);

    /**
     * Encrypt password.
     *
     * @param user
     */
    void setPassword(@NonNull User user);

    /**
     * Get the user who is currently sending the request.
     *
     * @param userName the username.
     * @return optional user
     */
    @NonNull
    Optional<User> getCurrentRequestUser(@NonNull String userName);

    /**
     * Get currently logged in user into SecurityContextHolder.
     *
     * @return a user that current has been logged in.
     */
    @NonNull
    User getCurrentUser();

    /**
     * Put the currently logged in user from SecurityContextHolder.
     *
     * @param user current user that has been logged in.
     */
    void storeCurrentUser(@NonNull User user);

}
