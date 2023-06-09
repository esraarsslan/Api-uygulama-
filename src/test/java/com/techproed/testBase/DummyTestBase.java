package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyTestBase {

    protected RequestSpecification spec03;

    @Before
    public void setup(){

        spec03 = new RequestSpecBuilder().setContentType(ContentType.JSON).
                setBaseUri("https://dummy.restapiexample.com/api/v1").build();

    }
}
