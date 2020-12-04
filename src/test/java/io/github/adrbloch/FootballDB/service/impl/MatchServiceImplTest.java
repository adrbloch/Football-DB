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
@AutoConfigureWebTestClient
@WithMockUser(authorities = "ADMIN")
@ActiveProfiles("test")
class MatchServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findMatchesByTeamsReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "chelsea")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findMatchesByTeamsReturnsProperMatch() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "chelsea")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strHomeTeam").isEqualTo("Arsenal")
                .jsonPath("$.[0].strAwayTeam").isEqualTo("Chelsea");
    }

    @Test
    void findMatchesByNotExistHomeTeamReturnsEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "xxx")
                                .queryParam("awayTeam", "chelsea")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByNotExistAwayTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByNotExistTeamsReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "xxx")
                                .queryParam("awayTeam", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByEmptyHomeTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "")
                                .queryParam("awayTeam", "chelsea")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByEmptyAwayTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByEmptyTeamsReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "")
                                .queryParam("awayTeam", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesByTeamsAndSeasonReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "chelsea")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findMatchesByTeamsAndSeasonReturnsProperMatch() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "chelsea")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strHomeTeam").isEqualTo("Arsenal")
                .jsonPath("$.[0].strAwayTeam").isEqualTo("Chelsea")
                .jsonPath("$.[0].strSeason").isEqualTo("2016-2017");

    }

    @Test
    void findMatchesBySeasonAndNotExistHomeTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "xxx")
                                .queryParam("awayTeam", "chelsea")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesBySeasonAndNotExistAwayTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "xxx")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesBySeasonAndNotExistTeamsReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "xxx")
                                .queryParam("awayTeam", "xxx")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesBySeasonAndEmptyHomeTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "")
                                .queryParam("awayTeam", "chelsea")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesBySeasonAndEmptyAwayTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "arsenal")
                                .queryParam("awayTeam", "")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchesBySeasonAndEmptyTeamsReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("homeTeam", "")
                                .queryParam("awayTeam", "")
                                .queryParam("season", "2016-2017")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findMatchByIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("id", "679698")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findMatchByIdReturnsProperMatch() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("id", "679698")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idEvent").isEqualTo("679698");
    }

    @Test
    void findMatchByNotExistIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("id", "0")
                                .build())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void findMatchByNotANumberIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("id", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findMatchByEmptyIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/matches")
                                .queryParam("id", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }


}