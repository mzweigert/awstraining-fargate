package com.awstraining.backend.business.notifyme;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SentimentDetector {

    private static final Logger LOGGER = LogManager.getLogger(SentimentDetector.class);

    private AmazonComprehend sentimentDetector;

    // TODO: lab3
    //  1. Inject AWS AmazonComprehend from configuration ComprehendSentimentConfig.
    @Autowired
    public SentimentDetector(AmazonComprehend sentimentDetector) {
        this.sentimentDetector = sentimentDetector;
    }

    // TODO: lab3
    //  1. Create detect sentiment text request.
    //  2. Call detection.
    //  3. Log information about sentiment.
    //  4. Return sentiment.
    public String detectSentiment(String language, String text) {
        final DetectSentimentRequest sentimentRequest = new DetectSentimentRequest()
                .withText(text)
                .withLanguageCode(language);

        final DetectSentimentResult sentimentResult = sentimentDetector.detectSentiment(sentimentRequest);

        // Print sentiment analysis result
        LOGGER.info("Sentiment: {}", sentimentResult.getSentiment());
        return sentimentResult.getSentiment();
    }
}
