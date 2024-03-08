package com.riko.booking.client.service.implementation;

import com.riko.booking.client.ShowClient;
import com.riko.booking.client.dto.Show;
import com.riko.booking.client.service.ShowClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowClientServiceImpl implements ShowClientService {

    private final ShowClient showClient;

    @Override
    public Show getShowSetupById(Long id) {
        try {
            Show show = showClient.getShowSetupById(id).getBody();
            log.info("show found : {}", show);
            return show;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("show not found with id");
        }
    }
}
