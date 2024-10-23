package com.awstraining.backend.business.notifyme;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Translator {

    private static final Logger LOGGER = LogManager.getLogger(Translator.class);

    private AmazonTranslate translate;

    // TODO: lab2
    //  1. Inject AWS AmazonTranslate from configuration TranslatorConfig.
    @Autowired
    public Translator(AmazonTranslate translate) {
        this.translate = translate;
    }

    public String translate(NotifyMeDO notifyMeDO) {
        // TODO: lab2
        //  1. Create translate text request.
        //  2. Call translate.
        //  3. Log information about successful translated message.
        //  4. Return translated message
        final TranslateTextRequest translateRequest = new TranslateTextRequest()
                .withText(notifyMeDO.text())
                .withSourceLanguageCode(notifyMeDO.sourceLc())
                .withTargetLanguageCode(notifyMeDO.targetLc());

        final TranslateTextResult translateResult = translate.translateText(translateRequest);

        LOGGER.info("Text '{}' was successful translated from {} to {} with result '{}'",
                notifyMeDO.text(), notifyMeDO.sourceLc(), notifyMeDO.targetLc(), translateRequest.getText());

        return translateResult.getTranslatedText();
    }
}
