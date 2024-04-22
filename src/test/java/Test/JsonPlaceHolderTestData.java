package Test;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // With this method, we can create test data in the test class without creating a new map.

    public static Map<String, Object> expectedDataMap(Integer userId, String title, Boolean completed) {

        Map<String, Object> expectedData = new HashMap<>();

        if (userId != null) {
            expectedData.put("userId", userId);
        }
        if (title != null) {
            expectedData.put("title", title);
        }
        if (completed != null) {
            expectedData.put("completed", completed);
        }
        return expectedData;

    }


}
