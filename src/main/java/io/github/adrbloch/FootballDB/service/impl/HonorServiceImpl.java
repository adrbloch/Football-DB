package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.model.honor.Honor;
import io.github.adrbloch.FootballDB.model.honor.Honors;
import io.github.adrbloch.FootballDB.service.HonorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class HonorServiceImpl implements HonorService {

    private static final Logger logger = LoggerFactory.getLogger(HonorServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public HonorServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Honor> findHonorsByPlayerId(String playerId) {

        logger.info("Getting honors by player id: [{}]", playerId);

        return webClient.get()
                .uri("/lookuphonors.php?id=" + playerId)
                .retrieve()
                .bodyToMono(Honors.class)
                .block()
                .getHonors();
    }
}
