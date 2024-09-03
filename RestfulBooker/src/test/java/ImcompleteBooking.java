import lombok.Getter;
import lombok.Setter;

public class ImcompleteBooking {
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private int totalprice;
    @Getter @Setter
    private boolean depositpaid;
    @Getter @Setter
    private BookingDates bookingdates;
    @Getter @Setter
    private String additionalneeds;

}