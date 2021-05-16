package com.lyudovskikh.management.controller;

import com.lyudovskikh.management.model.dto.UserInfoDto;
import com.lyudovskikh.management.service.api.UserInfoService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserInfoController.class)
public class UserInfoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInfoService userInfoService;

    @Test
    public void whenPostUserInfoCreationRequest_thenUserInfoShouldBeCreated() throws Exception {
        var userInfo = buildUserInfo();
        when(userInfoService.create(any())).thenReturn(userInfo);
        var request = post("/api/users/")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(toJson(userInfo));
        var response = mockMvc.perform(request);
        response.andExpect(status().isOk());
        response.andExpect(content().contentType(APPLICATION_JSON));
        response.andExpect(jsonPath("$.login", equalTo(userInfo.getLogin())));
        response.andExpect(jsonPath("$.email", equalTo(userInfo.getEmail())));
        response.andExpect(jsonPath("$.firstName", equalTo(userInfo.getFirstName())));
        response.andExpect(jsonPath("$.lastName", equalTo(userInfo.getLastName())));
        response.andExpect(jsonPath("$.middleName", equalTo(userInfo.getMiddleName())));
        response.andExpect(jsonPath("$.version", is(userInfo.getVersion()), Integer.class));
        response.andExpect(jsonPath("$.id", is(userInfo.getId()), Long.class));
    }

    @Test
    public void whenPutRequestWithValidUserInfo_thenUserInfoShouldBeUpdated() throws Exception {
        var userInfo = buildUserInfo();
        when(userInfoService.update(any())).thenReturn(userInfo);
        var request = put("/api/users/")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(toJson(userInfo));
        var response = mockMvc.perform(request);
        response.andExpect(status().isOk());
        response.andExpect(content().contentType(APPLICATION_JSON));
        response.andExpect(jsonPath("$.login", equalTo(userInfo.getLogin())));
        response.andExpect(jsonPath("$.email", equalTo(userInfo.getEmail())));
        response.andExpect(jsonPath("$.firstName", equalTo(userInfo.getFirstName())));
        response.andExpect(jsonPath("$.lastName", equalTo(userInfo.getLastName())));
        response.andExpect(jsonPath("$.middleName", equalTo(userInfo.getMiddleName())));
        response.andExpect(jsonPath("$.version", is(userInfo.getVersion()), Integer.class));
        response.andExpect(jsonPath("$.id", is(userInfo.getId()), Long.class));
    }

    @Test
    public void whenGetRequestWithValidUserInfoId_thenUserInfoShouldBeReturned() throws Exception {
        var userInfo = buildUserInfo();
        when(userInfoService.getById(userInfo.getId())).thenReturn(userInfo);
        var response = mockMvc.perform(get("/api/users/" + userInfo.getId()));
        response.andExpect(status().isOk());
        response.andExpect(content().contentType(APPLICATION_JSON));
        response.andExpect(jsonPath("$.login", equalTo(userInfo.getLogin())));
        response.andExpect(jsonPath("$.email", equalTo(userInfo.getEmail())));
        response.andExpect(jsonPath("$.firstName", equalTo(userInfo.getFirstName())));
        response.andExpect(jsonPath("$.lastName", equalTo(userInfo.getLastName())));
        response.andExpect(jsonPath("$.middleName", equalTo(userInfo.getMiddleName())));
        response.andExpect(jsonPath("$.version", is(userInfo.getVersion()), Integer.class));
        response.andExpect(jsonPath("$.id", is(userInfo.getId()), Long.class));
    }

    @Test
    public void whenDeleteRequestWithValidUserInfoId_thenUserInfoShouldBeDeleted() throws Exception {
        var userInfoId = RandomUtils.nextLong(1, 1_000_000);
        var response = mockMvc.perform(delete("/api/users/" + userInfoId));
        verify(userInfoService).delete(userInfoId);
        response.andExpect(status().isOk());
    }

    @Test
    public void whenSearchUserInfo_thenListOfUserInfoShouldBeReturned() throws Exception {
        var user1 = buildUserInfo();
        var user2 = buildUserInfo();
        var users = List.of(user1, user2);
        when(userInfoService.search(any())).thenReturn(users);
        var response = mockMvc.perform(get("/api/users/"));
        response.andExpect(status().isOk());
        response.andExpect(content().contentType(APPLICATION_JSON));
        response.andExpect(jsonPath("$", notNullValue()));
        response.andExpect(jsonPath("$", isA(List.class)));
        response.andExpect(jsonPath("$", hasSize(users.size())));
        response.andExpect(jsonPath("$[0].id", is(user1.getId()), Long.class));
        response.andExpect(jsonPath("$[1].id", is(user2.getId()), Long.class));
    }

    @Test
    public void whenDeleteRequestWithNegativeUserInfoId_thenValidationErrorResponseShouldBeReturned() throws Exception {
        var userInfoId = -100L;
        var response = mockMvc.perform(delete("/api/users/" + userInfoId));
        verify(userInfoService, never()).delete(userInfoId);
        response.andExpect(status().isBadRequest());
        response.andExpect(jsonPath("$", notNullValue()));
        response.andExpect(jsonPath("$.violations[0].fieldName", is("delete.id")));
    }

    @Test
    public void whenPutRequestWithInvalidUserInfo_thenValidationErrorResponseShouldBeReturned() throws Exception {
        var userInfo = buildUserInfo();
        userInfo.setEmail("it's not email - this field will fail validation");
        when(userInfoService.update(any())).thenReturn(userInfo);
        var request = put("/api/users/")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(toJson(userInfo));
        var response = mockMvc.perform(request);
        verify(userInfoService, never()).update(any());
        response.andExpect(status().isBadRequest());
        response.andExpect(jsonPath("$", notNullValue()));
        response.andExpect(jsonPath("$.violations[0].fieldName", is("email")));
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

    private String toJson(UserInfoDto userInfo) {
        return String.format(
                "{ " +
                        "\"id\": %d, " +
                        "\"login\": \"%s\", " +
                        "\"email\": \"%s\", " +
                        "\"firstName\": \"%s\", " +
                        "\"lastName\": \"%s\", " +
                        "\"middleName\": \"%s\", " +
                        "\"version\": %d " +
                 "}",
                userInfo.getId(),
                userInfo.getLogin(), userInfo.getEmail(),
                userInfo.getFirstName(), userInfo.getLastName(), userInfo.getMiddleName(),
                userInfo.getVersion()
        );
    }

}
