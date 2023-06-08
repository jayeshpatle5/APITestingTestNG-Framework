package com.api.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import automation.core.utils.data.ProjectConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification reqSpec;
	static ResponseSpecification resSpec;

	public RequestSpecification reqSpec(String baseUrl) throws FileNotFoundException {
		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logger.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(ProjectConfig.getPropertyValue("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public ResponseSpecification responseSpec(int code) {

		resSpec = new ResponseSpecBuilder().expectStatusCode(code).expectContentType(ContentType.JSON).build();
		return resSpec;
	}



}
