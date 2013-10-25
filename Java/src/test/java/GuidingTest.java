import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Tristan
 * Date: 10/25/13
 * Time: 10:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class GuidingTest {
    public static final String TRAIN_ID = "express_2000";


    @Test
    public void guidingTest() throws Exception {
        ReservationRequest request = new ReservationRequest(TRAIN_ID, 4);
        Reservation reservation = new TicketOffice().makeReservation(request);

        assertThat(reservation.trainId, is(TRAIN_ID));
        assertThat(reservation.seats.size(), is(4));
        assertThat(reservation.seats.get(0).seatNumber, is(1));
        assertThat(reservation.seats.get(0).coach, is("A"));
        assertThat(reservation.bookingId, is("75bcd15"));
    }
}

