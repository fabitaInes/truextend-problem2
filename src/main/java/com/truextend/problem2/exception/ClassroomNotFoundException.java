package com.truextend.problem2.exception;

/**
 * The type Classroom not found exception.
 */
public class ClassroomNotFoundException extends Exception {
    /**
     * Instantiates a new Classroom not found exception.
     *
     * @param code the code
     */
    public ClassroomNotFoundException(String code) {
        super(String.format("Class with code %s not found", code));
    }
}
