package com.truextend.problem2.exception;

/**
 * The type Classroom already exists exception.
 */
public class ClassroomAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Classroom already exists exception.
     *
     * @param code the code
     */
    public ClassroomAlreadyExistsException(String code) {
        super(String.format("Classroom with name %s already exists", code));
    }
}
