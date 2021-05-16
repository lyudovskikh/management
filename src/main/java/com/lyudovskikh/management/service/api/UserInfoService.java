package com.lyudovskikh.management.service.api;

import com.lyudovskikh.management.model.dto.UserInfoCreationRequest;
import com.lyudovskikh.management.model.dto.UserInfoDto;
import com.querydsl.core.types.Predicate;

import java.util.List;

/**
 * User info service
 */
public interface UserInfoService {

    /**
     * Create user info
     * @param userInfo user info creation request
     * @return created user info
     */
    UserInfoDto create(UserInfoCreationRequest userInfo);

    /**
     * Update user info
     * @param userInfo user info update
     * @return updated user info
     */
    UserInfoDto update(UserInfoDto userInfo);

    /**
     * Delete user info
     * @param id user info identifier
     */
    void delete(Long id);

    /**
     * Receive user info by identifier
     * @param id identifier
     * @return user info
     */
    UserInfoDto getById(Long id);

    /**
     * Search appropriate user info
     * @param predicate search conditions
     * @return appropriate list of user info
     */
    List<UserInfoDto> search(Predicate predicate);

}
