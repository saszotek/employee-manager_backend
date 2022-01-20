package com.daniel.employeemanager.exception;

import java.text.MessageFormat;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find title with id: {0} ", id));
    }
}
