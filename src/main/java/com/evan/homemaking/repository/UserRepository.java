package com.evan.homemaking.repository;

import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

/**
 * @ClassName UserRepository
 * @Description User information repository.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 17:39
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    /**
     * Gets user by username.
     *
     * @param username username must not be blank
     * @return an user
     */
    @NonNull
    User findByAccountId(@NonNull String username);

    /**
     * Gets user by email.
     *
     * @param email email must not be blank
     * @return an user.
     */
    @NonNull
    User findByEmail(@NonNull String email);
}