package com.lyudovskikh.management;

import com.lyudovskikh.management.controller.UserInfoController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagementApplicationTests {

	@Autowired
	private UserInfoController userInfoController;

	@Test
	void contextLoads() {
		Assertions.assertThat(userInfoController).isNotNull();
	}

}
