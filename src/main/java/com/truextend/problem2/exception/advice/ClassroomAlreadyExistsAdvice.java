package com.truextend.problem2.exception.advice;

import com.truextend.problem2.exception.ClassroomAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Classroom already exists advice.
 */
@ControllerAdvice
class ClassroomAlreadyExistsAdvice {

    /**
     * Handler map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(ClassroomAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Map<?, ?> handler(ClassroomAlreadyExistsException ex) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", ex.getMessage());
        return messageMap;
    }
}