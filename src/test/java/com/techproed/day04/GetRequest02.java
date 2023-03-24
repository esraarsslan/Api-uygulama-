package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

     /*
    https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu test edin
     */


    @Test
    public void test01(){

        String url = "https://restful-booker.herokuapp.com/booking";


        //3-request gönder
     Response response =  given().
             accept("application/Json").
             when().
             get(url);
     response.prettyPrint();
// assertion islemini yap

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.getContentType() = " + response.getContentType());

    }
    @Test
    public void test02(){
         /*
           https://restful-booker.herokuapp.com/booking/1009 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    ve Response body'sinin "Not Found" icerdigini
    ve Response body'sinin "API" icermedigini test edin
         */

        String url="https://restful-booker.herokuapp.com/booking/1009";

        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        //response.asString() responsu stringe cevirir...
        Assert.assertFalse(response.asString().contains("API"));
    // "API" icermedigini  test ettik false ile....
    }

}
