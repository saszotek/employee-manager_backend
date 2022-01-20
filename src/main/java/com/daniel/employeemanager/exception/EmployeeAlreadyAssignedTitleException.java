package com.daniel.employeemanager.exception;

import java.text.MessageFormat;

public class EmployeeAlreadyAssignedTitleException extends RuntimeException {
    public EmployeeAlreadyAssignedTitleException(final Long employeeId, final Long titleId) {
        super(MessageFormat.format("Employee: {0} is already assigned to title: {1}", employeeId, titleId));
    }
}
