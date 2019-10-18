package com.truextend.problem2.controller;

import com.truextend.problem2.entity.Classroom;
import com.truextend.problem2.exception.ClassroomAlreadyExistsException;
import com.truextend.problem2.exception.ClassroomFieldsException;
import com.truextend.problem2.service.ClassroomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Classroom controller.
 */
@RestController
public class ClassroomController {


    /**
     * List collection.
     *
     * @return the collection
     */
    @GetMapping("/classrooms")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of registered classrooms", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Classroom> list() {
        return ClassroomService.getInstance().selectAll();
    }

    /**
     * Create classroom.
     *
     * @param classroom the classroom
     * @return the classroom
     * @throws ClassroomAlreadyExistsException the classroom already exists exception
     * @throws ClassroomFieldsException        the classroom fields exception
     */
    @PostMapping("/classrooms")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a classroom", response = Classroom.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created classroom"),
            @ApiResponse(code = 409, message = "Classroom already exists"),
            @ApiResponse(code = 400, message = "Invalid classroom")
    })
    public Classroom create(@RequestBody Classroom classroom) throws ClassroomAlreadyExistsException, ClassroomFieldsException {
        return ClassroomService.getInstance().add(classroom);
    }

}