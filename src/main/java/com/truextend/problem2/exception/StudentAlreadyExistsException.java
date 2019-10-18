package com.truextend.problem2.exception;

/**
 * The type Student already exists exception.
 */
public class StudentAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Student already exists exception.
     *
     * @param name the name
     */
    public StudentAlreadyExistsException(String name) {
        super(String.format("Student with name %s already exists", name));
    }
}
