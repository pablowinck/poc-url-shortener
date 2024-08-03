package com.github.pablowinck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    private final WhatsappService whatsappService;

    public UrlController(UrlService urlService, WhatsappService whatsappService) {
        this.urlService = urlService;
        this.whatsappService = whatsappService;
    }

    @PostMapping("/shorten")
    @ResponseBody
    public Url shortenUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(url -> new RedirectView(url.getOriginalUrl()))
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    @GetMapping("/whatsapp/{shortUrl}")
    @ResponseBody
    public String getWhatsappLink(@PathVariable String shortUrl) {
        return whatsappService.generateWhatsappLink(shortUrl);
    }

}
