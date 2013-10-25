import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TicketOfficeTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private static final String TRAIN_ID = "express_2000";
    private static final int SEAT_COUNT = 4;

    @Mock
    private TrainInformationService trainService;

    private TicketOffice ticketOffice;
    private final ReservationRequest request = new ReservationRequest(TRAIN_ID, SEAT_COUNT);


    @Before
    public void setUp() throws Exception {
        ticketOffice = new TicketOffice(trainService);
    }

    @Test
    public void reservingReturnsANonNullReservation() throws Exception {
        ignoring(trainService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r, is(not(nullValue())));
    }

    @Test
    public void reservationReturnsTheTrainId() throws Exception {
        ignoring(trainService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.trainId, is(TRAIN_ID));
    }

    @Test
    public void reservationReturnsTheSameNumberOfSeats() throws Exception {
        ignoring(trainService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.seats.size(), is(SEAT_COUNT));
    }

    @Test
    public void weCanReserve5Seats() throws Exception {
        ignoring(trainService);

        Reservation r = ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, 5));
        assertThat(r.seats.size(), is(5));
    }

    @Test
    public void weCanGetDetailsOfTheTrain() throws Exception {
        context.checking(new Expectations() {{
            oneOf(trainService).getTrainInformation(TRAIN_ID);
        }});

        ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, SEAT_COUNT));
    }

    // utility method to ignore use of collaberators
    private void ignoring(final Object o) {
        context.checking(new Expectations() {{
            ignoring(o);
        }});
    }
}
