package com.riko.booking.client.service.implementation;

import com.riko.booking.client.PaymentClient;
import com.riko.booking.client.dto.Payment;
import com.riko.booking.client.dto.PaymentMinDTO;
import com.riko.booking.client.service.PaymentClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentClientServiceImpl implements PaymentClientService {

    private final PaymentClient paymentClient;

    @Override
    public Payment createPayment(PaymentMinDTO paymentMinDTO) {
        try {
            Payment payment = paymentClient.createPayment(paymentMinDTO).getBody();
            log.info("payment successful: {}", payment);
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("payment unsuccessful for: " + paymentMinDTO);
        }
    }

    @Override
    public Payment getPayment(Long id) {
        try {
            Payment payment = paymentClient.getPayment(id).getBody();
            log.info("payment found: {}", payment);
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("payment not found");
        }
    }
}
