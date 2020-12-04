package io.github.adrbloch.FootballDB.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
@WithMockUser(authorities = "ADMIN")
@ActiveProfiles("test")
class TableServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findTableByLeagueIdAndSeasonReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/table")
                                .queryParam("leagueId", "4328")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findTableByLeagueIdAndSeasonReturnsProperTable() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/table")
                                .queryParam("leagueId", "4328")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].name").isEqualTo("Chelsea");
    }

    @Test
    void findTableByNotExistIdReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/table")
                                .queryParam("leagueId", "0")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTableByNotANumberIdReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/table")
                                .queryParam("leagueId", "xxx")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTableByEmptyIdReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/table")
                                .queryParam("leagueId", "")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }
}