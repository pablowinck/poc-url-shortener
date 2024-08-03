package com.github.pablowinck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class PocUrlshortenerApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api";
    }

    @Test
    void testUrlShorteningAndRedirection() {
        String originalUrl = "http://example.com";
        Url response = restTemplate.postForObject(baseUrl + "/shorten", originalUrl, Url.class);

        assertThat(response).isNotNull();
        assertThat(response.getOriginalUrl()).isEqualTo(originalUrl);
        assertThat(response.getShortUrl()).isNotEmpty();

        String shortUrl = response.getShortUrl();
        String redirectContent = restTemplate.getForObject(baseUrl + "/" + shortUrl, String.class);

        assertThat(redirectContent).contains("This domain is for use in illustrative examples in documents.");
    }
}