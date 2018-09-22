package com.egrasoft.ss.demography.converter;

import javafx.util.converter.IntegerStringConverter;

public class DemographyIntegerStringConverter extends IntegerStringConverter {
    @Override
    public Integer fromString(String value) {
        try {
            return super.fromString(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
