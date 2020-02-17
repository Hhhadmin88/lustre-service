package com.evan.lustre.service;

import com.evan.lustre.common.model.dto.UserDTO;
import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.common.model.param.RegisterParam;
import com.evan.lustre.common.model.param.UserParam;
import com.evan.lustre.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @ClassName UserService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 14:20
 */
public interface UserService extends CrudService<User, Integer> {

    /**
     * Gets user by accountId.
     *
     * @param accountId accountId of user.
     * @return an optional user.
     */
    @NonNull
    Optional<User> getByAccountId(@NonNull String accountId);

    /**
     * Gets user by email.
     *
     * @param email email of user.
     * @return an optional user.
     */
    @NonNull
    Optional<User> getByEmail(@NonNull String email);

    /**
     * Get an user by account id.
     *
     * @param accountId account id must not be null.
     * @return an user.
     */
    @NonNull
    User getByAccountIdOfNonNull(@NonNull String accountId);

    /**
     * Get an user by email.
     *
     * @param accountId email must not be null.
     * @return an user.
     */
    User getByEmailOfNonNull(@NonNull String accountId);

    /**
     * Register an user.
     *
     * @param registerParam registerParam.
     */
    void registerUser(@NonNull RegisterParam registerParam);

    /**
     * Update an user.
     *
     * @param userId    userId.
     * @param userParam userParam.
     * @return an user dto that has been updated.
     */
    UserDTO updateOne(@NonNull Integer userId, @NonNull UserParam userParam);

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
     * @param user user object.
     */
    void setEncryptedPassword(@NonNull User user);

    /**
     * Get currently logged in user from SecurityContextHolder.
     *
     * @return an user that current has been logged in.
     */
    @NonNull
    User getCurrentUser();

    /**
     * Get an user name.
     *
     * @param userName accountId or email.
     * @return an user.
     */
    User getOneUserByUserName(String userName);

    /**
     * Get an user by user id.
     *
     * @param userId user id.
     * @return an user.
     */
    User getOneUserById(@NonNull Integer userId);

    /**
     * Put the currently logged in user from SecurityContextHolder.
     *
     * @param user current user that has been logged in.
     */
    void storeCurrentUser(@NonNull User user);

    /**
     * Convert user id to nick name.
     *
     * @param userId employer id or employee id.
     * @return employer name or employee name.
     */
    String convertUserIdToName(@NonNull Integer userId);
}
