package com.github.pablowinck;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url shortenUrl(String originalUrl) {
        String shortUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes(StandardCharsets.UTF_8)).substring(0, 8);
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setCreatedDate(LocalDateTime.now());
        return urlRepository.save(url);
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

}

