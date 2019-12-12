package com.evan.homemaking.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName BaseRepository
 * @Description  Base repository interface contains some common methods.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 20:18
 */
@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID> {

    /**
     * Finds all domain by id list.
     *
     * @param ids  id list of domain must not be null
     * @param sort the specified sort must not be null
     * @return a list of domains
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Sort sort);

    /**
     * Finds all domain by domain id list.
     *
     * @param ids      must not be null
     * @param pageable must not be null
     * @return a list of domains
     */
    @NonNull
    Page<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Pageable pageable);

    /**
     * Deletes by id list.
     *
     * @param ids id list of domain must not be null
     * @return number of rows affected
     */
    long deleteByIdIn(@NonNull Collection<ID> ids);
}
