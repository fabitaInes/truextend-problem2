package com.truextend.problem2.dao;

import com.truextend.problem2.entity.Classroom;
import com.truextend.problem2.exception.ClassroomAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Classroom dao.
 */
public class ClassroomDao {

    private static ClassroomDao DAO;
    private static List<Classroom> classroomList = new ArrayList<>();

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public static ClassroomDao getDao() {
        if (DAO == null) {
            DAO = new ClassroomDao();
        }
        return DAO;
    }

    /**
     * Fetch all collection.
     *
     * @return the collection
     */
    public Collection<Classroom> fetchAll() {
        return classroomList;
    }

    /**
     * Insert classroom.
     *
     * @param classroom the classroom
     * @return the classroom
     * @throws ClassroomAlreadyExistsException the classroom already exists exception
     */
    public Classroom insert(Classroom classroom) throws ClassroomAlreadyExistsException {
        for (Classroom classroom1 : classroomList) {
            if (classroom1.getName().equals(classroom.getName())) {
                throw new ClassroomAlreadyExistsException(classroom.getName());
            }
        }
        classroomList.add(classroom);
        return classroom;
    }

}
