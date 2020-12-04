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
class HonorServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findHonorsByPlayerIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/honors")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0]").isNotEmpty();
    }

    @Test
    void findHonorsByPlayerIdReturnsProperHonors() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/honors")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].idPlayer").isEqualTo("34146304");
    }

    @Test
    void findHonorsByNotExistPlayerIdReturnsEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/honors")
                                .queryParam("playerId", "0")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();
    }

    @Test
    void findHonorsByEmptyPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/honors")
                                .queryParam("playerId", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findHonorsByNotNumberPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/honors")
                                .queryParam("playerId", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}