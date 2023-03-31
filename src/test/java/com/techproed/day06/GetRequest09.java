package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyTestBase {

 /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec03).when().
                get("/{parametre1}");

        response.prettyPrint();


        Assert.assertEquals(200, response.getStatusCode());
       // Assert.assertTrue(response.getStatusCode()==200);
        JsonPath json=response.jsonPath();
        Assert.assertEquals("Airi Satou",json.getString("data.employee_name[4]"));
        Assert.assertEquals(372000,json.getInt("data.employee_salary[5]"));
        Assert.assertEquals(24,json.getList("data.id").size());
        System.out.println(json.getList("data.id"));
        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

//        Assert.assertTrue(json.getList("data.employee_age").contains(21));
//        Assert.assertTrue(json.getList("data.employee_age").contains(23));
//        Assert.assertTrue(json.getList("data.employee_age").contains(61));

        List<Integer> yasListesi=new ArrayList<Integer>();
        yasListesi.add(21);
        yasListesi.add(23);
        yasListesi.add(61);
//List<Integer> arananYaslar = Arrays.asList(21,23,61);
        System.out.println(yasListesi);

        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasListesi));



    }
}