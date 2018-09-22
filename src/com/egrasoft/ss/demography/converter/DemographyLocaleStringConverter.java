package com.egrasoft.ss.demography.converter;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DemographyLocaleStringConverter extends StringConverter<Locale> {
    private static Map<String, Locale> availableLocales = new HashMap<>();

    public DemographyLocaleStringConverter() {
        for (Locale locale : Locale.getAvailableLocales())
            availableLocales.put(locale.getLanguage(), locale);
    }

    @Override
    public String toString(Locale object) {
        return object == null ? null : object.toString();
    }

    @Override
    public Locale fromString(String string) {
        return availableLocales.get(string);
    }
}
