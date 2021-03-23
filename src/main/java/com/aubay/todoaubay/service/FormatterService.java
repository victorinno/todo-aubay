package com.aubay.todoaubay.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@ApplicationScope
class FormatterService {

    private static final Map<String, DateTimeFormatter> FORMATTERS = new HashMap<>(100);
    
    private static final String CREATING_FORMATTER_FOR_0 = "Creating formatter for: {0}";

    static DateTimeFormatter getFormatter(final String format) {
        return FORMATTERS.computeIfAbsent(format, f -> {
            System.out.println(MessageFormat.format(CREATING_FORMATTER_FOR_0, f));
            return DateTimeFormatter.ofPattern(f);
        });
    }
}
