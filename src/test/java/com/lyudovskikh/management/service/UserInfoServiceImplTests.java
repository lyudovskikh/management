package com.lyudovskikh.management.service;

import com.lyudovskikh.management.model.domain.UserInfo;
import com.lyudovskikh.management.model.dto.UserInfoCreationRequest;
import com.lyudovskikh.management.model.dto.UserInfoDto;
import com.lyudovskikh.management.model.mapper.UserInfoMapper;
import com.lyudovskikh.management.repository.UserInfoRepository;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceImplTests {

    @Mock
    private UserInfoRepository repository;

    @Mock
    private UserInfoMapper mapper;

    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    @Test
    public void whenCreateNewUserInfo_thenUserInfoShouldBeCreated() {
        var userInfoCreationRequest = buildUserInfoCreationRequest();
        var userInfo = mock(UserInfo.class);
        var createdUserInfo = mock(UserInfo.class);
        var expected = mock(UserInfoDto.class);

        when(repository.exists(any())).thenReturn(false);
        when(mapper.map(userInfoCreationRequest)).thenReturn(userInfo);
        when(repository.save(userInfo)).thenReturn(createdUserInfo);
        when(mapper.map(createdUserInfo)).thenReturn(expected);

        var actual = userInfoService.create(userInfoCreationRequest);

        assertSame(expected, actual);
    }

    @Test
    public void whenCreateExistingUserInfo_thenIllegalArgumentExceptionShouldBeThrown() {
        var userInfoCreationRequest = buildUserInfoCreationRequest();
        when(repository.exists(any())).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userInfoService.create(userInfoCreationRequest));
    }

    @Test
    public void whenUpdateExistingUserInfo_thenUserInfoShouldBeUpdated() {
        var expected = buildUserInfo();
        var userInfo = mock(UserInfo.class);
        var updatedUserInfo = mock(UserInfo.class);

        when(repository.existsById(expected.getId())).thenReturn(true);
        when(mapper.map(expected)).thenReturn(userInfo);
        when(repository.save(userInfo)).thenReturn(updatedUserInfo);
        when(mapper.map(updatedUserInfo)).thenReturn(expected);

        var actual = userInfoService.update(expected);

        assertSame(expected, actual);
    }

    @Test
    public void whenUpdateNonExistingUserInfo_thenNoSuchElementExceptionShouldBeThrown() {
        var userInfo = buildUserInfo();
        when(repository.existsById(userInfo.getId())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> userInfoService.update(userInfo));
    }

    @Test
    public void whenDeleteExistingUserInfo_thenUserInfoShouldBeDeleted() {
        var userInfoId = 777L;
        when(repository.existsById(userInfoId)).thenReturn(true);
        userInfoService.delete(userInfoId);
        verify(repository).deleteById(userInfoId);
    }

    @Test
    public void whenDeleteNonExistingUserInfo_thenNoSuchElementExceptionShouldBeThrown() {
        var userInfoId = 777L;
        when(repository.existsById(userInfoId)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> userInfoService.delete(userInfoId));
        verify(repository, never()).deleteById(userInfoId);
    }

    @Test
    public void whenGetByIdWithExistingUserInfo_thenUserInfoShouldBeReturned() {
        var userInfo = mock(UserInfo.class);
        var expected = mock(UserInfoDto.class);

        when(repository.findById(userInfo.getId())).thenReturn(Optional.of(userInfo));
        when(mapper.map(userInfo)).thenReturn(expected);

        var actual = userInfoService.getById(userInfo.getId());

        assertSame(expected, actual);
    }

    @Test
    public void whenGetByIdWithoutExistingUserInfo_thenNoSuchElementExceptionShouldBeThrown() {
        var userInfoId = 1L;
        when(repository.findById(userInfoId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userInfoService.getById(userInfoId));
    }

    @Test
    public void whenSearch_thenListOfUserInfoShouldBeReturned() {
        var predicate = mock(Predicate.class);
        var userInfo = mock(UserInfo.class);
        var expectedUserInfoDto = mock(UserInfoDto.class);

        when(repository.findAll(predicate)).thenReturn(List.of(userInfo));
        when(mapper.map(userInfo)).thenReturn(expectedUserInfoDto);

        var actualUserInfoDtoList = userInfoService.search(predicate);

        assertEquals(1, actualUserInfoDtoList.size());
        assertSame(expectedUserInfoDto, actualUserInfoDtoList.get(0));
    }

    private UserInfoDto buildUserInfo() {
        var userInfo = new UserInfoDto();
        userInfo.setId(RandomUtils.nextLong(1, 1_000_000));
        userInfo.setLogin(RandomStringUtils.randomAlphanumeric(1, 25));
        userInfo.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@mail.com");
        userInfo.setFirstName(RandomStringUtils.randomAlphabetic(1, 25));
        userInfo.setLastName(RandomStringUtils.randomAlphabetic(1, 25));
        userInfo.setMiddleName(RandomStringUtils.randomAlphabetic(1, 25));
        userInfo.setVersion(RandomUtils.nextInt(0, 100));
        return userInfo;
    }

    private UserInfoCreationRequest buildUserInfoCreationRequest() {
        var userInfoCreationRequest = new UserInfoCreationRequest();
        userInfoCreationRequest.setLogin(RandomStringUtils.randomAlphanumeric(1, 25));
        userInfoCreationRequest.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@mail.com");
        userInfoCreationRequest.setFirstName(RandomStringUtils.randomAlphabetic(1, 25));
        userInfoCreationRequest.setLastName(RandomStringUtils.randomAlphabetic(1, 25));
        userInfoCreationRequest.setMiddleName(RandomStringUtils.randomAlphabetic(1, 25));
        return userInfoCreationRequest;
    }

}
