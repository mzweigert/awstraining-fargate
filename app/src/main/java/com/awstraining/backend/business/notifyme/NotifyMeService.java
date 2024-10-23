package com.awstraining.backend.business.notifyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyMeService {
    private MessageSender sender;
    private final Translator translator;
    private final SentimentDetector sentimentDetector;

    // TODO: lab1
    //  1. Inject MessageSender.
    // TODO lab2
    //  1. Inject Translator
    // TODO lab3
    //  1. Inject sentiment detector
    @Autowired
    public NotifyMeService(MessageSender sender,
                           Translator translator,
                           SentimentDetector sentimentDetector) {
        this.sender = sender;
        this.translator = translator;
        this.sentimentDetector = sentimentDetector;
    }

    public String notifyMe(NotifyMeDO notifyMe) {
        final String translatedMessage = translator.translate(notifyMe);
        final String sentiment = sentimentDetector.detectSentiment(notifyMe.targetLc(), translatedMessage);
        final String sentMessage = sentiment + ": " + translatedMessage;
        sender.send(sentMessage);
        return sentMessage;
    }

}
