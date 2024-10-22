package com.awstraining.backend.business.notifyme;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class MessageSender {

    private static final Logger LOGGER = LogManager.getLogger(MessageSender.class);

    private String snsTopic;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKey:#{null}}")
    private String snsAccessKey;

    @Value("${aws.secretKey:#{null}}")
    private String snsSecretKey;

    private AmazonSNS sns;
    // TODO: lab1
    //  1. Inject AWS AmazonsSNS from configuration SNSConfig.
    //  2. Make sure that you created new value in parameter store with arn of sns topic.
    //  3. Inject parameter with @Value annotation through constructor.
    @Autowired
    public MessageSender(@Value("${notification.topicarn}") String snsTopic, AmazonSNS sns) {
        this.snsTopic = snsTopic;
        this.sns = sns;
    }

    public void send(String text) {
        // TODO: lab1
        //  1. Create publish request.
        //  2. Publish request with sns.
        //  3. Log information about successful sent message to topic with topic arn and message id.
        final PublishRequest publishRequest = new PublishRequest(snsTopic, text);
        final PublishResult publishResult = sns.publish(publishRequest);
        LOGGER.info("Message was send to topic '{}' with id '{}'.", snsTopic, publishResult.getMessageId());
    }
}
