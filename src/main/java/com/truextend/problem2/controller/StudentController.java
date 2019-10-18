package com.truextend.problem2.controller;

import com.truextend.problem2.entity.Student;
import com.truextend.problem2.exception.StudentAlreadyExistsException;
import com.truextend.problem2.exception.StudentFieldsException;
import com.truextend.problem2.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The type Student controller.
 */
@RestController
public class StudentController {

    /**
     * List collection.
     *
     * @return the collection
     */
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View a list of registered students", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Student> list() {
        return StudentService.getInstance().selectAll();
    }

    /**
     * Create student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     * @throws StudentFieldsException        the student fields exception
     */
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a classroom", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created student"),
            @ApiResponse(code = 409, message = "Student already exists"),
            @ApiResponse(code = 400, message = "Invalid student")
    })
    public Student create(@RequestBody Student student) throws StudentAlreadyExistsException, StudentFieldsException {
        return StudentService.getInstance().add(student);
    }

    /**
     * Students in classrooms collection.
     *
     * @return the collection
     */
    @GetMapping("/students/classrooms")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create a classroom", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public Collection<Student> studentsInClassrooms() {
        double distanceInMeters = 10;
        // assuming no declination for each classroom
        double[] bearingInDegrees = new double[]{45, 135, 225, 315};
        return StudentService.getInstance().getStudentsInClassrooms(distanceInMeters, bearingInDegrees);
    }

}