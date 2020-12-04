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
class TeamServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findTeamsByNameReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("name", "arsenal")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findTeamsByNameReturnsProperTeams() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("name", "arsenal")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strTeam").isEqualTo("Arsenal");
    }

    @Test
    void findTeamsByNotExistNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("name", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByEmptyNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByNotSoccerTeamNameReturnsEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("name", "Los Angeles Lakers")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    void findTeamsByLeagueReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("league", "english premier league")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findTeamsByLeagueReturnsProperTeams() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("league", "english premier league")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strLeague").isEqualTo("English Premier League");
    }

    @Test
    void findTeamsByNotExistLeagueReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("league", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByEmptyLeagueReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("league", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByNotSoccerLeagueReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("league", "Los Angeles Lakers")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByCountryReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("country", "england")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]").isNotEmpty();
    }

    @Test
    void findTeamsByCountryReturnsProperLeagues() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("country", "england")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strCountry").isEqualTo("England");
    }

    @Test
    void findTeamsByNotExistCountryReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("country", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamsByEmptyCountryReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("country", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findTeamByIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("id", "133604")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findTeamByIdReturnsProperTeam() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("id", "133604")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idTeam").isEqualTo("133604");
    }

    @Test
    void findTeamByNotExistIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("id", "0")
                                .build())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void findTeamByNotANumberIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("id", "xxx")
                                .build())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    void findTeamByEmptyIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/teams")
                                .queryParam("id", "")
                                .build())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}