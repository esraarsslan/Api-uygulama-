package com.techproed.testData;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> setUpDestData() {


        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("userIdi", 1);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        return expectedData;
    }
}