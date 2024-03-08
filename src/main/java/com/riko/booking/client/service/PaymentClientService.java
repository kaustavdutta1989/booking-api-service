package com.riko.booking.client.service;

import com.riko.booking.client.dto.Payment;
import com.riko.booking.client.dto.PaymentMinDTO;

public interface PaymentClientService {

    Payment createPayment(PaymentMinDTO paymentMinDTO);
    Payment getPayment(Long id);
}
