package com.riko.booking.controller;

import com.riko.booking.model.Ticket;
import com.riko.booking.model.dto.TicketMaxDTO;
import com.riko.booking.model.enums.TicketStatus;
import com.riko.booking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketMaxDTO ticketMaxDTO) {
        return ResponseEntity.ok(ticketService.createTicket(ticketMaxDTO));
    }

    @PatchMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicketStatus(@PathVariable Long ticketId,
                                                 @RequestParam TicketStatus status) {
        return ResponseEntity.ok(ticketService.changeStatusTicket(ticketId, status));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Ticket> cancelTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.cancelTicket(ticketId));
    }
}
