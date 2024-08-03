package com.github.pablowinck;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Value("${server.url:localhost:8080}")
    private String serverUrl;

    public String generateWhatsappLink(String shortUrl) {
        String fullUrl = serverUrl + "/api/" + shortUrl;
        return "https://api.whatsapp.com/send?text=" + fullUrl;
    }

}

