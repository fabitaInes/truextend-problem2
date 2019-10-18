package com.truextend.problem2.exception.advice;

import com.truextend.problem2.exception.ClassroomFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Classroom fields advice.
 */
@ControllerAdvice
class ClassroomFieldsAdvice {

    /**
     * Handler map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(ClassroomFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<?, ?> handler(ClassroomFieldsException ex) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("message", ex.getMessage());
        return messageMap;
    }
}