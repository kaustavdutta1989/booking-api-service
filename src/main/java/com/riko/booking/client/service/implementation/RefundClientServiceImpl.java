package com.riko.booking.client.service.implementation;

import com.riko.booking.client.RefundClient;
import com.riko.booking.client.dto.Refund;
import com.riko.booking.client.dto.RefundMinDTO;
import com.riko.booking.client.dto.enums.TransactionalStatus;
import com.riko.booking.client.service.RefundClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundClientServiceImpl implements RefundClientService {

    private final RefundClient refundClient;

    @Override
    public Refund getRefund(Long id) {
        try {
            Refund refund = refundClient.getRefund(id).getBody();
            log.info("refund found : {}", refund);
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("refund not found");
        }
    }

    @Override
    public Refund createRefund(RefundMinDTO refundMinDTO) {
        try {
            Refund refund = refundClient.createRefund(refundMinDTO).getBody();
            log.info("refund created : {}", refund);
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("refund not created");
        }
    }

    @Override
    public Refund statusChangeRefund(Long id, TransactionalStatus status) {
        getRefund(id); // Check Refund to Change Status
        try {
            Refund refund = refundClient.statusChangeRefund(id, status).getBody();
            log.info("refund found : {}", refund);
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("refund not found");
        }
    }

    @Override
    public Refund getRefundByTicketId(Long ticketId) {
        try {
            Refund refund = refundClient.getRefundByTicketId(ticketId).getBody();
            log.info("refund found : {} for ticketId: {}", refund, ticketId);
            return refund;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("refund not found for ticketId: " + ticketId);
        }
    }
}
