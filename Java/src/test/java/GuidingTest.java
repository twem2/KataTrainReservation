import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GuidingTest {
    public static final String TRAIN_ID = "express_2000";
    public static final String BOOKING_ID = "75bcd15";

    // FIXME will have to wire this up properly when more is implemented
    TrainInformationService tis = new TrainInformationService() {
        @Override
        public Train getTrainInformation(String trainId) {
            return null;
        }
    };
    TrainReservationService trs = new TrainReservationService() {
        @Override
        public boolean reserve(Train train) {
            return true;
        }
    };

    @Test
    public void guidingTest() throws Exception {
        ReservationRequest request = new ReservationRequest(TRAIN_ID, 4);
        Reservation reservation = new TicketOffice(tis, trs).makeReservation(request);

        assertThat(reservation.trainId, is(TRAIN_ID));
        assertThat(reservation.seats.size(), is(4));
//        assertThat(reservation.seats.get(0).seatNumber, is(1));
//        assertThat(reservation.seats.get(0).coach, is("A"));
        assertThat(reservation.bookingId, is(BOOKING_ID));
    }
}

