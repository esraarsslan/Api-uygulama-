package com.techproed.day04;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    /*
GetRequest01:
https://restful-booker.herokuapp.com/booking/3
adresine bir request gonderildiginde donecek cevap(response) icin
HTTP status kodunun 200
Content Type’in Json
Ve Status Line’in HTTP/1.1 200 OK
Oldugunu test edin.
 */
    @Test
    public void test01() {


        // 1- url belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2-expected result oluştur...
//burada bizden body de gelen resposu assert etmemiz beklenmemiştir. bu sebeple bu adıma gerek yoktur.

        //3-request gönder
       Response response =  given().
               accept("application/json").
               when().
               get(url);
       response.prettyPrint();
        //4- respose al(actual result)

        //body testi yapmadığım için actual result almıyorum

        //5- assertion işlemini yapalım

        response.
                then().// gherkin dilinde assert yerine kullanilir....
                statusCode(200).//HTTP Status Code should be 200
                contentType("application/json").//Content Type should be JSON
                statusLine("HTTP/1.1 200 OK");//Status Line should be HTTP/1.1 200 OK

        System.out.println("response.getStatusCode() = " + response.getStatusCode());//Response dan gelen status code u verir
        System.out.println("response.getContentType() = " + response.getContentType());//Response dan gelen content type i verir
        System.out.println("response.getStatusLine() = " + response.getStatusLine());// Responsedan gelen status line i verir.
        System.out.println("response.getHeaders() = " + response.getHeaders());
        Assert.assertEquals(200,response.statusCode());
//        //Assert kismi bize task olarak verilen kisimdir..
//        // Actual kismi ise, response dan donen degerdir.
//        // status code int deger dondurur.
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());




    }
}