package com.api.test;

import org.testng.annotations.Test;

import automation.core.testng.BaseTest;
import automation.core.utils.data.ProjectConfig;

public class AllTest extends BaseTest {

	static String token = ProjectConfig.getPropertyValue("token");
	@Test(description = "Post Method")
	public void postUserDetails() throws Exception {

	}

	@Test(description = "Get Method")
	public void getUserDetails() throws Exception {

	}

}
