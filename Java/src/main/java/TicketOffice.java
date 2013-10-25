import java.util.LinkedList;
import java.util.List;

public class TicketOffice {

    private final TrainInformationService trainService;

    public TicketOffice(TrainInformationService trainService) {
        this.trainService = trainService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        trainService.getTrainInformation(request.trainId);

        List<Seat> seats = new LinkedList<Seat>();
        for (int i = 0; i < request.seatCount; i++) {
            seats.add(new Seat(null, 0));
        }

		return new Reservation(request.trainId, seats, null);
    }

}