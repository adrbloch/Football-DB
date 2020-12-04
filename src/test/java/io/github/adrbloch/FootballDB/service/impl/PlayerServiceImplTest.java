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
class PlayerServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findPlayersByNameReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findPlayersByNameReturnsProperPlayer() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strPlayer").isEqualTo("Cristiano Ronaldo");
    }

    @Test
    void findPlayersByNotExistNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("name", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByEmptyNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByNotSoccerPlayerNameReturnsEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("name", "Michael Jordan")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isEmpty();
    }

    @Test
    void findPlayersByTeamAndNameReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "juventus")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findPlayersByTeamAndNameReturnsProperPlayer() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "juventus")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strTeam").isEqualTo("Juventus")
                .jsonPath("$.[0].strPlayer").isEqualTo("Cristiano Ronaldo");
    }

    @Test
    void findPlayersByTeamAndNotExistNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "juventus")
                                .queryParam("name", "xxx")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByNameAndNotExistTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "xxx")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByTeamAndEmptyNameReturnsPlayersByTeam() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "juventus")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strTeam").isEqualTo("Juventus");
    }

    @Test
    void findPlayersByNameAndEmptyTeamReturnsPlayersByName() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "")
                                .queryParam("name", "cristiano ronaldo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].strPlayer").isEqualTo("Cristiano Ronaldo");
    }

    @Test
    void findPlayersByEmptyTeamAndNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }


    @Test
    void findPlayersByNotSoccerPlayerNameAndEmptyTeamReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "")
                                .queryParam("name", "LeBron James")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByNotSoccerTeamAndEmptyNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "Los Angeles Lakers")
                                .queryParam("name", "")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayersByNotSoccerTeamAndNameReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("team", "Los Angeles Lakers")
                                .queryParam("name", "LeBron James")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findPlayerByIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("id", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findPlayerByIdReturnsProperLeague() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("id", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idPlayer").isEqualTo("34146304");
    }

    @Test
    void findPlayerByNotExistIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("id", "0")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findPlayerByNotANumberIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("id", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findPlayerByEmptyIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/players")
                                .queryParam("id", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}