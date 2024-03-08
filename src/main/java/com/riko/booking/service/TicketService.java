package com.riko.booking.service;

import com.riko.booking.model.Ticket;
import com.riko.booking.model.dto.TicketMaxDTO;
import com.riko.booking.model.enums.TicketStatus;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();
    Ticket getTicket(Long id);
    Ticket createTicket(TicketMaxDTO ticketMaxDTO);
    Ticket changeStatusTicket(Long id, TicketStatus status);
    Ticket cancelTicket(Long id);
}
