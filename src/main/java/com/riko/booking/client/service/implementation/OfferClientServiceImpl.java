package com.riko.booking.client.service.implementation;

import com.riko.booking.client.GlobalOfferClient;
import com.riko.booking.client.dto.GlobalOffer;
import com.riko.booking.client.service.OfferClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OfferClientServiceImpl implements OfferClientService {

    private final GlobalOfferClient globalOfferClient;

    @Override
    public List<GlobalOffer> getAllGlobalOffers() {
        try {
            List<GlobalOffer> globalOffers = globalOfferClient.getAllGlobalOffers().getBody();
            log.info("global offers found: {}", globalOffers);
            return globalOffers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("global offers not found");
        }
    }
}
