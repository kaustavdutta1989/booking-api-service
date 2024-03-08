package com.riko.booking.service.implementation;

import com.riko.booking.client.dto.*;
import com.riko.booking.client.dto.enums.ShowTime;
import com.riko.booking.client.dto.enums.TransactionalStatus;
import com.riko.booking.client.service.*;
import com.riko.booking.model.Ticket;
import com.riko.booking.model.dto.TicketMaxDTO;
import com.riko.booking.model.enums.TicketStatus;
import com.riko.booking.repo.TicketRepository;
import com.riko.booking.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final GlobalOfferService globalOfferService;
    private final ShowClientService showClientService;
    private final SeatClientService seatClientService;
    private final PaymentClientService paymentClientService;
    private final AllocatedSeatClientService allocatedSeatClientService;
    private final UserClientService userClientService;
    private final RefundClientService refundClientService;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ticket not found with id: " + id));
    }

    @Override
    public Ticket createTicket(TicketMaxDTO ticketMaxDTO) {
        Show show = showClientService.getShowSetupById(ticketMaxDTO.getShowId());
        User viewer = userClientService.getViewerById(ticketMaxDTO.getUserId());
        Seat seat = seatClientService.getSeatSetupByTheatreIdAndSeatTypeAndHallName
                (ticketMaxDTO.getTheatreHallSeat().getTheatreId(),
                        ticketMaxDTO.getTheatreHallSeat().getSeatType(),
                        ticketMaxDTO.getTheatreHallSeat().getHallName());
        Double discount = calculateDiscount(viewer.getId(), show.getShowTime(),
                seat.getSeatCost() * ticketMaxDTO.getTicketCount() * 1.);


        Payment payment = setupPayment(seat.getSeatCost(), ticketMaxDTO.getTicketCount(), discount);
        Ticket ticket = saveTicket(Ticket.builder()
                .showId(ticketMaxDTO.getShowId())
                .userId(ticketMaxDTO.getUserId())
                .ticketCost(seat.getSeatCost())
                .ticketDiscount(discount)
                .paymentId(payment.getId())
                .ticketCount(ticketMaxDTO.getTicketCount())
                .ticketStatus(TicketStatus.UNBOOKED)
                .build());

        allocateSeatSpots(ticketMaxDTO, ticket.getId(), payment);
        ticket.setTicketStatus(TicketStatus.BOOKED);
        return saveTicket(ticket);
    }

    @Override
    public Ticket changeStatusTicket(Long id, TicketStatus status) {
        Ticket foundTicket = getTicket(id);
        foundTicket.setTicketStatus(status);
        return saveTicket(foundTicket);
    }

    @Override
    public Ticket cancelTicket(Long id) {
        // Get Ticket
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ticket not found with id: " + id));
        // Get Payment
        Payment payment = paymentClientService.getPayment(ticket.getPaymentId());
        // Initiate Refund
        Refund refund = refundClientService.createRefund(RefundMinDTO.builder()
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .status(TransactionalStatus.INITIATED)
                .build());
        log.info("refund initiated: {} for the ticket: {}", refund, ticket);
        // Cancel Seat Spot
        allocatedSeatClientService.deallocateSeats(ticket.getId());
        // Cancel Ticket
        ticket.setTicketStatus(TicketStatus.CANCELLED);
        return saveTicket(ticket);
    }

    private Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    private List<AllocatedSeat> allocateSeatSpots(TicketMaxDTO ticketMaxDTO, Long ticketId, Payment payment) {
        try {
            List<AllocatedSeat> seatSpots = ticketMaxDTO.getSeatSpots().stream()
                    .map(ss -> AllocatedSeat.builder()
                            .rowSeat(ss.getRowSeat())
                            .columnSeat(ss.getColumnSeat())
                            .ticketId(ticketId)
                            .showId(ticketMaxDTO.getShowId())
                            .theatreId(ticketMaxDTO.getTheatreHallSeat().getTheatreId())
                            .seatType(ticketMaxDTO.getTheatreHallSeat().getSeatType())
                            .hallName(ticketMaxDTO.getTheatreHallSeat().getHallName())
                            .build())
                    .toList();
            List<AllocatedSeat> allocatedSeats = allocatedSeatClientService.allocateSeats(seatSpots);
            log.info("allocated seats:{} for userId: {}", allocatedSeats, ticketMaxDTO.getUserId());
            return allocatedSeats;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("seats not allocated for userId: {}", ticketMaxDTO.getUserId());
            // Assign Refund
            Refund refund = refundClientService.createRefund(RefundMinDTO.builder()
                    .paymentId(payment.getId())
                    .amount(payment.getAmount())
                    .status(TransactionalStatus.INITIATED)
                    .build());
            throw new IllegalArgumentException("could not allocate seat spots, initiating refund: " + refund);
        }
    }

    private Payment setupPayment(Integer seatCost, Integer seatCount, Double discount) {
        try {
            Double totalCost = seatCost * seatCount * 1.;
            Double reducedCost = totalCost - discount;
            Payment payment = paymentClientService.createPayment(PaymentMinDTO.builder()
                    .amount(reducedCost)
                    .cost(Double.valueOf(seatCost))
                    .discount(discount)
                    .build());
            log.info("payment successful: {}", payment);
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("payment unsuccessful");
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Double calculateDiscount(Long userId, ShowTime showTime, Double totalCost) {
        Double discount = 0.;

        List<GlobalOffer> globalOffers = new ArrayList<>();
        try {
            globalOffers = globalOfferService.getAllGlobalOffers();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("could not find global offers");
        }

        GlobalOffer thirdTicketOffer = globalOffers.stream()
                .filter(o -> o.getName().equals("THIRD_TICKET"))
                .findFirst()
                .orElse(GlobalOffer.builder().percentage(0).build());

        if(ticketRepository.findByUserId(userId).size() == 2)
            discount += (thirdTicketOffer.getPercentage() * totalCost) / 100;

        GlobalOffer afternoonTicketOffer = globalOffers.stream()
                .filter(o -> o.getName().equals("AFTERNOON_TICKET"))
                .findFirst()
                .orElse(GlobalOffer.builder().percentage(0).build());

        if(showTime == ShowTime.AFTERNOON)
            discount += (afternoonTicketOffer.getPercentage() * totalCost) / 100;

        return discount;
    }
}
