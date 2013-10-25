import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TicketOfficeTest {

    private static final String TRAIN_ID = "express_2000";
    private static final int SEAT_COUNT = 4;

    private final TicketOffice ticketOffice = new TicketOffice();
    private final ReservationRequest request = new ReservationRequest(TRAIN_ID, SEAT_COUNT);
    
    @Test
    public void reservingReturnsANonNullReservation() throws Exception {
        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r, is(not(nullValue())));
    }

    @Test
    public void reservationReturnsTheTrainId() throws Exception {
        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.trainId, is(TRAIN_ID));
    }

    @Test
    public void reservationReturnsTheSameNumberOfSeats() throws Exception {
        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.seats.size(), is(SEAT_COUNT));
    }

    @Test
    public void weCanReserve5Seats() throws Exception {
        Reservation r = ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, 5));
        assertThat(r.seats.size(), is(5));
    }
}
