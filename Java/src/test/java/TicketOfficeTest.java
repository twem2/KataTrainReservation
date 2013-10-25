import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TicketOfficeTest {

    private static final String BOOKING_REFERENCE = "foo";
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private static final String TRAIN_ID = "express_2000";
    private static final int SEAT_COUNT = 4;
    private static final Train TRAIN = new Train();

    @Mock
    private TrainInformationService trainInformationService;

    @Mock
    private TrainReservationService trainReservationService;

    @Mock
    private BookingReferenceService bookingReferenceService;

    private TicketOffice ticketOffice;
    private final ReservationRequest request = new ReservationRequest(TRAIN_ID, SEAT_COUNT);


    @Before
    public void setUp() throws Exception {
        ticketOffice = new TicketOffice(trainInformationService, trainReservationService, bookingReferenceService);
    }

    @Test
    public void reservingReturnsANonNullReservation() throws Exception {
        ignoring(trainInformationService, trainReservationService, bookingReferenceService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r, is(not(nullValue())));
    }

    @Test
    public void reservationReturnsTheTrainId() throws Exception {
        ignoring(trainInformationService, trainReservationService, bookingReferenceService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.trainId, is(TRAIN_ID));
    }

    @Test
    public void reservationReturnsTheSameNumberOfSeats() throws Exception {
        ignoring(trainInformationService, trainReservationService, bookingReferenceService);

        Reservation r = ticketOffice.makeReservation(request);
        assertThat(r.seats.size(), is(SEAT_COUNT));
    }

    @Test
    public void weCanReserve5Seats() throws Exception {
        ignoring(trainInformationService, trainReservationService, bookingReferenceService);

        Reservation r = ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, 5));
        assertThat(r.seats.size(), is(5));
    }

    @Test
    public void weCanGetDetailsOfTheTrain() throws Exception {
        context.checking(new Expectations() {{
            ignoring(trainReservationService);
            ignoring(bookingReferenceService);

            oneOf(trainInformationService).getTrainInformation(TRAIN_ID);
        }});

        ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, SEAT_COUNT));
    }

    @Test
    public void reservationRequestsGetSubmittedToTheReservationService() throws Exception {
        context.checking(new Expectations() {{
            ignoring(bookingReferenceService);

            allowing(trainInformationService).getTrainInformation(TRAIN_ID); will(returnValue(TRAIN));

            oneOf(trainReservationService).reserve(TRAIN);
        }});

        ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, SEAT_COUNT));
    }

    @Test
    public void weRequestABookingReference() throws Exception {
        context.checking(new Expectations() {{
            ignoring(trainInformationService);
            ignoring(trainReservationService);

            oneOf(bookingReferenceService).getNewReference();
        }});

        ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, SEAT_COUNT));
    }

    @Test
    public void weReturnTheBookinReference() throws Exception {
        context.checking(new Expectations() {{
            ignoring(trainInformationService);
            ignoring(trainReservationService);

            oneOf(bookingReferenceService).getNewReference(); will(returnValue(BOOKING_REFERENCE));
        }});

        Reservation r = ticketOffice.makeReservation(new ReservationRequest(TRAIN_ID, SEAT_COUNT));

        assertThat(r.bookingId, is(BOOKING_REFERENCE));
    }

    // utility method to ignore use of collaberators
    private void ignoring(final Object... os) {
        context.checking(new Expectations() {{
            for (Object o : os) {
                ignoring(o);
            }
        }});
    }
}
