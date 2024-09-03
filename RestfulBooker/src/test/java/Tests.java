import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.not;

public class Tests {
    @Test
    public void getBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().pathParam("id", "1")
                .when().get("/booking/{id}");
        response.then().log().body();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("size()", not(0));
    }

    @Test
    public void getInexistBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Response response = RestAssured
                .given().pathParam("id", "1000000000")
                .when().get("/booking/{id}");
        response.then().log().body();
        response.then().assertThat().statusCode(404);
    }

    @Test
    public void postBookingTest() throws JsonProcessingException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        Booking booking = new Booking();
        booking.setFirstname("Alejandro");
        booking.setLastname("Uriarte");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void postIncompleteBookingTest() throws JsonProcessingException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        ImcompleteBooking booking = new ImcompleteBooking();
        booking.setFirstname("Alejandro");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(400);
    }
    @Test
    public void postWrongBookingTest() throws JsonProcessingException {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        WrongBooking booking = new WrongBooking();
        booking.setFirstname("Alejandro");
        booking.setLastname("Uriarte");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        booking.setEmail("alejandro@gmail.com");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(200);
    }
}
