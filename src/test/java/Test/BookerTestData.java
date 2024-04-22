package Test;

import java.util.HashMap;
import java.util.Map;

public class BookerTestData {
    public static Map<String, String> bookingdatesMethod(String checkin, String checkout) {
        Map<String, String> BookingDateMap = new HashMap<>();
        BookingDateMap.put("checkin", "2023-03-07");
        BookingDateMap.put("checkout", "2024-09-25");
        return BookingDateMap;
    }

    public static Map<String, Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, boolean depositpaid, Map<String, String> bookingdatesMap, String additionalneeds) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 15);
        expectedData.put("depositpaid", true);
        expectedData.put("BookingDateMap", bookingdatesMap);
        expectedData.put("additionalneeds", "Lunch");
        return expectedData;
    }
}
