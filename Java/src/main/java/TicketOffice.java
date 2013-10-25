public class TicketOffice {

    public Reservation makeReservation(ReservationRequest request) {
		return new Reservation(request.trainId, null, null);
    }

}