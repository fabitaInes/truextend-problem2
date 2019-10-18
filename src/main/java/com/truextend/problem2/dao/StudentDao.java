package com.truextend.problem2.dao;

import com.truextend.problem2.entity.Student;
import com.truextend.problem2.exception.StudentAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Student dao.
 */
public class StudentDao {

    private static StudentDao DAO;
    private static List<Student> studentList = new ArrayList<>();

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public static StudentDao getDao() {
        if (DAO == null) {
            DAO = new StudentDao();
        }
        return DAO;
    }

    /**
     * Fetch all collection.
     *
     * @return the collection
     */
    public Collection<Student> fetchAll() {
        return studentList;
    }

    /**
     * Insert student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     */
    public Student insert(Student student) throws StudentAlreadyExistsException {
        for (Student student1 : studentList) {
            if (student1.getName().equals(student.getName())) {
                throw new StudentAlreadyExistsException(student.getName());
            }
        }
        studentList.add(student);
        return student;
    }

}
