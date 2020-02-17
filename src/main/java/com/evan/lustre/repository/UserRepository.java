package com.evan.lustre.repository;

import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @ClassName UserRepository
 * @Description User information repository.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 17:39
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    /**
     * Get an user by accountId.
     *
     * @param accountId accountId must not be null.
     * @return an user
     */
    @NonNull
    Optional<User> findByAccountId(@NonNull String accountId);

    /**
     * Get an user by email.
     *
     * @param email email must not be blank.
     * @return an user.
     */
    @NonNull
    Optional<User> findByEmail(@NonNull String email);

    /**
     * Get an user by nick name.
     *
     * @param nickName nick name of user.
     * @return an user.
     */
    @NonNull
    User findUserByNickName(@NonNull String nickName);

    /**
     * Returns whether an user with the given account id exists.
     *
     * @param accountId account id.
     * @return if an user with the given account id exists return true,otherwise return false.
     */
    boolean existsUserByAccountId(@NonNull String accountId);

    /**
     * Returns whether an user with the given nick name exists.
     *
     * @param nickName nick name.
     * @return if an user with the given nick name exists return true,otherwise return false.
     */
    boolean existsUserByNickName(@NonNull String nickName);

    /**
     * Returns whether an user with the given email exists.
     *
     * @param email email.
     * @return if an user with the given email exists return true,otherwise return false.
     */
    boolean existsUserByEmail(@NonNull String email);

    /**
     * Returns whether an user with the phone number exists.
     *
     * @param phoneNumber phone number.
     * @return if an user with the given phone number exists return true,otherwise return false.
     */
    boolean existsUserByPhoneNumber(@NonNull String phoneNumber);
}