import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    private final TrainInformationService informationService;
    private final TrainReservationService reservationService;

    public TicketOffice(TrainInformationService informationService, TrainReservationService reservationService) {
        this.informationService = informationService;
        this.reservationService = reservationService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        Train train = informationService.getTrainInformation(request.trainId);

        List<Seat> seats = new LinkedList<Seat>();
        for (int i = 0; i < request.seatCount; i++) {
            seats.add(new Seat(null, 0));
        }

        reservationService.reserve(train);

		return new Reservation(request.trainId, seats, null);
    }


}