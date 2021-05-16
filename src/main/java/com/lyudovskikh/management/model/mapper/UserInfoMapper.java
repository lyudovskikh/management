package com.lyudovskikh.management.model.mapper;

import com.lyudovskikh.management.model.domain.UserInfo;
import com.lyudovskikh.management.model.dto.UserInfoCreationRequest;
import com.lyudovskikh.management.model.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * User info mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserInfoMapper {

    /**
     * Map domain user info model to dto user info model
     * @param userInfo domain user info model
     * @return dto user info model
     */
    UserInfoDto map(UserInfo userInfo);

    /**
     * Map dto user info model to domain user info model
     * @param userInfo dto user info model
     * @return domain user info model
     */
    UserInfo map(UserInfoDto userInfo);

    /**
     * Map user info creation request model to domain user info model
     * @param userInfoCreationRequest user info creation request model
     * @return domain user info model
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserInfo map(UserInfoCreationRequest userInfoCreationRequest);

}
