import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    private final TrainInformationService informationService;
    private final TrainReservationService reservationService;
    private final BookingReferenceService bookingReferenceService;

    public TicketOffice(TrainInformationService informationService, TrainReservationService reservationService, BookingReferenceService bookingReferenceService) {
        this.informationService = informationService;
        this.reservationService = reservationService;
        this.bookingReferenceService = bookingReferenceService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        String reference = bookingReferenceService.getNewReference();

        Train train = informationService.getTrainInformation(request.trainId);

        List<Seat> seats = new LinkedList<Seat>();
        for (int i = 0; i < request.seatCount; i++) {
            seats.add(new Seat(null, 0));
        }

        reservationService.reserve(train);

		return new Reservation(request.trainId, seats, reference);
    }


}