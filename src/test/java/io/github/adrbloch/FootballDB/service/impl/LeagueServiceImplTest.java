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
class LeagueServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findLeaguesByCountryReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("country", "england")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findLeaguesByCountryReturnsProperLeagues() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("country", "england")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strCountry").isEqualTo("England");
    }

    @Test
    void findLeaguesByNotExistCountryReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("country", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findLeaguesByEmptyCountryReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("country", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findLeaguesByNameReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("name", "english premier league")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findLeaguesByNameReturnsProperLeague() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("name", "english premier league")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.strLeague").isEqualTo("English Premier League");
    }

    @Test
    void findLeaguesByNotExistNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("name", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findLeaguesByEmptyNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findLeagueByIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("id", "4328")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findLeagueByIdReturnsProperLeague() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("id", "4328")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idLeague").isEqualTo("4328");
    }

    @Test
    void findLeagueByNotExistIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("id", "0")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findLeagueByNotANumberIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("id", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findLeaguesByEmptyIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/leagues")
                                .queryParam("id", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}