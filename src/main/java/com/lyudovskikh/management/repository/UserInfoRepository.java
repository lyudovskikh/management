package com.lyudovskikh.management.repository;

import com.lyudovskikh.management.model.domain.QUserInfo;
import com.lyudovskikh.management.model.domain.UserInfo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>,
                                            QuerydslPredicateExecutor<UserInfo>,
                                            QuerydslBinderCustomizer<QUserInfo> {

    @Override
    default void customize(QuerydslBindings bindings, QUserInfo userInfo) {

        bindings.bind(userInfo.login).first(((path, value) -> path.eq(value)));
        bindings.bind(userInfo.email).first(((path, value) -> path.eq(value)));
        bindings.bind(userInfo.firstName).first(((path, value) -> path.equalsIgnoreCase(value)));
        bindings.bind(userInfo.lastName).first(((path, value) -> path.equalsIgnoreCase(value)));
        bindings.bind(userInfo.middleName).first(((path, value) -> path.equalsIgnoreCase(value)));
        bindings.excluding(userInfo.id);
        bindings.excluding(userInfo.version);

    }

}
