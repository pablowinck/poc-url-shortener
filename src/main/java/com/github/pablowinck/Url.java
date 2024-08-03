package com.github.pablowinck;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class Url {
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    public Url() {
    }

    public Url(String originalUrl, String shortUrl, LocalDateTime createdDate, LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
