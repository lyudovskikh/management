package com.lyudovskikh.management.controller;

import com.lyudovskikh.management.model.domain.UserInfo;
import com.lyudovskikh.management.model.dto.UserInfoCreationRequest;
import com.lyudovskikh.management.model.dto.UserInfoDto;
import com.lyudovskikh.management.service.api.UserInfoService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User Info Controller", description = "Allowing create/read/update/delete user info, search user info")
@Validated
@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Operation(description = "Create user info")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfoDto create(
            @Parameter(description = "User info creation request")
            @Valid @NotNull @RequestBody UserInfoCreationRequest userInfo) {
        return userInfoService.create(userInfo);
    }

    @Operation(description = "Update user info")
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public UserInfoDto update(
            @Parameter(description = "User info")
            @Valid @NotNull @RequestBody UserInfoDto userInfo) {
        return userInfoService.update(userInfo);
    }

    @Operation(description = "Delete user info")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "User info ID")
            @Positive @PathVariable long id) {
        userInfoService.delete(id);
    }

    @Operation(description = "Get user info by ID")
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserInfoDto getById(
            @Parameter(description = "User info ID")
            @Positive @PathVariable long id) {
        return userInfoService.getById(id);
    }

    @Operation(description = "Search user info")
    @Parameter(name = "login", description = "Login", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    @Parameter(name = "email", description = "Email", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    @Parameter(name = "firstName", description = "First name", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    @Parameter(name = "lastName", description = "Last name", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    @Parameter(name = "middleName", description = "Middle name", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserInfoDto> search(
            @Parameter(hidden = true)
            @QuerydslPredicate(root = UserInfo.class) Predicate predicate) {
        return userInfoService.search(predicate);
    }

}
