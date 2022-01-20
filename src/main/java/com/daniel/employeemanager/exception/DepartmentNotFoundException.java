package com.daniel.employeemanager.exception;

import java.text.MessageFormat;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(final Long id) {
        super(MessageFormat.format("Could not find department with id: {0} ", id));
    }
}
