package com.riko.booking.client.service;

import com.riko.booking.client.dto.Refund;
import com.riko.booking.client.dto.RefundMinDTO;
import com.riko.booking.client.dto.enums.TransactionalStatus;

public interface RefundClientService {

    Refund getRefund(Long id);
    Refund createRefund(RefundMinDTO refundMinDTO);
    Refund statusChangeRefund(Long id, TransactionalStatus status);
    Refund getRefundByTicketId(Long ticketId);
}
