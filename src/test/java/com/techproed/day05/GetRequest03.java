package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;// YILDIZ KOYUNCA TUM MECHERS LARI IMPORT ETMIS OLDUK. INTERVIEW SORUSU


public class GetRequest03 {

     /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Sally"
ve lastname'in "Ericsson"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test(){

        String url = "https://restful-booker.herokuapp.com/booking/7";

        //1.Request olustrma
       Response response=  given().
               accept("application/Json").
               when().
               get(url);

        response.prettyPrint();
 //MATCHERS CLASS ILE API'DEN GELEN SONUCU ASSERT ETME...
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Eric"),
                        "lastname",equalTo("Jackson"),
                        "totalprice", equalTo(837),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2016-11-19"),// Json path i yaptik
                        "bookingdates.checkout", equalTo("2022-08-12"));

    }
}
