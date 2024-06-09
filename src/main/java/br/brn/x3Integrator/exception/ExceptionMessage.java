package br.brn.x3Integrator.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExceptionMessage {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultMessage = getEnglishMessageDefault(code, args);
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    private String getEnglishMessageDefault(String code, Object... args) {
        Locale locale = Locale.ENGLISH;
        return messageSource.getMessage(code, args, "Message Lost. Inform your partner", locale);
    }

}
