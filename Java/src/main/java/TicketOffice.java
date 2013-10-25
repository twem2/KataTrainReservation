import java.util.Arrays;
import java.util.List;

public class TicketOffice {

    public Reservation makeReservation(ReservationRequest request) {
        List<Seat> seats = Arrays.asList(new Seat(null, 0), new Seat(null, 0), new Seat(null, 0), new Seat(null, 0));

		return new Reservation(request.trainId, seats, null);
    }

}