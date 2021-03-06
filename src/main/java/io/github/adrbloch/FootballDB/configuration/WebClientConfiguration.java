package io.github.adrbloch.FootballDB.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private static final String THESPORTSDB_API_BASE_URL = "https://www.thesportsdb.com/api/v1/json/";
    private static final String API_KEY = "1";

    @Bean
    public WebClient getApiClient() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl(THESPORTSDB_API_BASE_URL + API_KEY)
                .build();
    }

}
