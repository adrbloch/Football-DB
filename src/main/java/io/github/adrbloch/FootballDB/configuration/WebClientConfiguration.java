package io.github.adrbloch.FootballDB.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private static final String THESPORTSDB_API_BASE_URL = "https://www.thesportsdb.com/api/v1/json/1";

    @Bean
    public WebClient getApiClient() {
        return WebClient.builder()
                .baseUrl(THESPORTSDB_API_BASE_URL)
                .build();
    }

}
