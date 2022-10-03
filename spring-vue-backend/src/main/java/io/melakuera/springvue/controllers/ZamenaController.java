package io.melakuera.springvue.controllers;

import io.melakuera.springvue.dto.ZamenaOfferWebSocketRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ZamenaController {

    @MessageMapping("/zamena")
    @SendTo("/topic/zamenas")
    public void offerZamena(@Payload ZamenaOfferWebSocketRequest zamenaOffer) {
        log.info(zamenaOffer.toString());
    }
}
