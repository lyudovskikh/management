package com.lyudovskikh.management.service;

import com.lyudovskikh.management.model.domain.QUserInfo;
import com.lyudovskikh.management.model.dto.UserInfoCreationRequest;
import com.lyudovskikh.management.model.dto.UserInfoDto;
import com.lyudovskikh.management.model.mapper.UserInfoMapper;
import com.lyudovskikh.management.repository.UserInfoRepository;
import com.lyudovskikh.management.service.api.UserInfoService;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * User info service
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository repository;
    private final UserInfoMapper mapper;

    public UserInfoServiceImpl(UserInfoRepository repository, UserInfoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserInfoDto create(UserInfoCreationRequest userInfo) {
        if (repository.exists(QUserInfo.userInfo.login.eq(userInfo.getLogin()))) {
            throw new IllegalArgumentException(String.format("User (login = %s) is already exists!", userInfo.getLogin()));
        }
        return mapper.map(repository.save(mapper.map(userInfo)));
    }

    @Override
    public UserInfoDto update(UserInfoDto userInfo) {
        checkUserInfoExistence(userInfo.getId());
        return mapper.map(repository.save(mapper.map(userInfo)));
    }

    @Override
    public void delete(Long id) {
        checkUserInfoExistence(id);
        repository.deleteById(id);
    }

    @Override
    public UserInfoDto getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Override
    public List<UserInfoDto> search(Predicate predicate) {
        var users = new ArrayList<UserInfoDto>();
        for (var user : repository.findAll(predicate)) {
            users.add(mapper.map(user));
        }
        return users;
    }

    private void checkUserInfoExistence(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException(String.format("There is no user with id = %d!", id));
        }
    }
}
