package com.daniel.employeemanager.exception;

import java.text.MessageFormat;

public class EmployeeAlreadyAssignedDepartmentException extends RuntimeException {
    public EmployeeAlreadyAssignedDepartmentException(final Long employeeId, final Long departmentId) {
        super(MessageFormat.format("Employee: {0} is already assigned to department: {1}", employeeId, departmentId));
    }
}
