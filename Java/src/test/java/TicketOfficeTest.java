import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TicketOfficeTest {
    @Test
    public void reservingReturnsANonNullReservation() throws Exception {
        Reservation r = new TicketOffice().makeReservation(null);
        assertThat(r, is(not(nullValue())));
    }


}
