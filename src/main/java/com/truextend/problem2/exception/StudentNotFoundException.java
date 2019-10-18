package com.truextend.problem2.exception;

/**
 * The type Student not found exception.
 */
public class StudentNotFoundException extends Exception {
    /**
     * Instantiates a new Student not found exception.
     *
     * @param name the name
     */
    public StudentNotFoundException(String name) {
        super(String.format("Student with name %s not found", name));
    }
}
