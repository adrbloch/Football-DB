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

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@WithMockUser(authorities = "ADMIN")
@ActiveProfiles("test")
class ContractServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findContractsByPlayerIdReturnsNotEmptyResponse() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/contracts")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void findContractsByPlayerIdReturnsProperContracts() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/contracts")
                                .queryParam("playerId", "34146304")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idPlayer").isEqualTo("34146304");
    }

    @Test
    void findContractsByNotExistPlayerIdReturnsNull() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/contracts")
                                .queryParam("playerId", "0")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").doesNotExist();
    }

    @Test
    void findContractsByEmptyPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/contracts")
                                .queryParam("playerId", "")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void findContractsByNotNumberPlayerIdReturnsError() {

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/rest/api/contracts")
                                .queryParam("playerId", "xxx")
                                .build())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}


