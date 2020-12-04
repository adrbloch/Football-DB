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
class FormerTeamServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findFormerTeamsByPlayerIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/former-teams")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findFormerTeamsByPlayerIdReturnsProperTeams() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/former-teams")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].idPlayer").isEqualTo("34146304");
    }

    @Test
    void findFormerTeamsByNotExistPlayerIdReturnsNullResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/former-teams")
                                .queryParam("playerId", "0")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findFormerTeamsByEmptyPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/former-teams")
                                .queryParam("playerId", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findFormerTeamsByNotNumberPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/former-teams")
                                .queryParam("playerId", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}