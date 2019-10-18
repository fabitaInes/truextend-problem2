package com.truextend.problem2.service;

import com.truextend.problem2.dao.ClassroomDao;
import com.truextend.problem2.entity.Classroom;
import com.truextend.problem2.exception.ClassroomAlreadyExistsException;
import com.truextend.problem2.exception.ClassroomFieldsException;

import java.util.Collection;

/**
 * The type Classroom service.
 */
public class ClassroomService {
    private static ClassroomService INSTANCE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ClassroomService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClassroomService();
        }
        return INSTANCE;
    }

    /**
     * Select all collection.
     *
     * @return the collection
     */
    public Collection<Classroom> selectAll() {
        return ClassroomDao.getDao().fetchAll();
    }

    /**
     * Add classroom.
     *
     * @param classRoom the class room
     * @return the classroom
     * @throws ClassroomAlreadyExistsException the classroom already exists exception
     * @throws ClassroomFieldsException        the classroom fields exception
     */
    public Classroom add(Classroom classRoom) throws ClassroomAlreadyExistsException, ClassroomFieldsException {
        validateClassroom(classRoom);
        return ClassroomDao.getDao().insert(classRoom);
    }

    private void validateClassroom(Classroom classroom) throws ClassroomFieldsException {
        if (classroom.getName() == null) {
            throw new ClassroomFieldsException("Classroom Name cannot be null");
        }
        if (classroom.getLatitude() == 0) {
            throw new ClassroomFieldsException("Classroom Latitude cannot be null");
        }
        if (classroom.getLongitude() == 0) {
            throw new ClassroomFieldsException("Classroom Longitude cannot be null");
        }
    }

}
