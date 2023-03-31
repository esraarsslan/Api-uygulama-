package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class GetRequest11 extends JsonPlaceHolderTestBase {

       /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
     Dönen response un
     Status kodunun 200, dönen body de,
     "completed": değerinin false
    "title”: değerinin “quis ut nam facilis et officia qui”
    "userId" sinin 1 ve header değerlerinden
     "Via" değerinin “1.1 vegur” ve
     "Server" değerinin “cloudflare” olduğunu test edin…
     */

    /*
    1-URL OLUSTUR
    --EXPECTED DATA
    2-REQUEST GONDER
    --ACTUAL DAtA
   3- ASSSERTION
     */

    @Test
    public void test() {
        //1-url oluştur
        spec01.pathParams("parametre1", "todos",
                "parametre2", 2);

        //2- expected data oluştur

        HashMap<String, Object> expectedData = new HashMap<String,Object>();
        expectedData.put("statusCode",200);
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userIdi",1);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println("ExpectedData");
        System.out.println(expectedData);
        System.out.println("--------------------------------------");



        //3-Request gönder
        Response response=given().
                accept("application/json").
                spec(spec01).when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();
        //4-Assert ET
        //*******1. yol ****MATCHERS CLASS ILE YAPTIKK

        response.then().
                assertThat().
                statusCode((int)expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("Via")),
                        "Server", equalTo(expectedData.get("Server"))).
                body("userId",equalTo(expectedData.get("userId")),
                "title",equalTo(expectedData.get("title")),
                 "completed",equalTo(expectedData.get("completed")));
        //**********2.YOL*******JSNPATH ILE ASSERTTT
        //HashMap<String ,Object> actualDataMap=response.as(HashMap.class); // api dan gelen response body i HashMap gibi yapıp
       JsonPath jsonPath= response.jsonPath();
         Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("Via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
//******************3. YONTEMMM***********DESERIALIZATION/////////////

        //--OBJECT MAPPER KULLANCAZ
        //--POJO CLASS ILE BIRLIKTE MAP


        System.out.println("Actual Data");
        System.out.println("---------------------");
        System.out.println(jsonPath);



    }
}

