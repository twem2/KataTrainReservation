import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    public Reservation makeReservation(ReservationRequest request) {
        List<Seat> seats = new LinkedList<Seat>();
        for (int i = 0; i < request.seatCount; i++) {
            seats.add(new Seat(null, 0));
        }

		return new Reservation(request.trainId, seats, null);
    }

}