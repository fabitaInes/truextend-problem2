package com.truextend.problem2.service;

import com.truextend.problem2.dao.StudentDao;
import com.truextend.problem2.entity.Student;
import com.truextend.problem2.exception.StudentAlreadyExistsException;
import com.truextend.problem2.exception.StudentFieldsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Student service.
 */
public class StudentService {
    /**
     * The constant EARTH_RADIO.
     */
    public final static double EARTH_RADIO = 6378.1;
    private static final Logger logger = LogManager.getLogger(StudentService.class);
    private static StudentService INSTANCE;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StudentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentService();
        }
        return INSTANCE;
    }

    /**
     * Determine lat lon double [ ].
     *
     * @param distance         the distance
     * @param bearingInDegrees the bearing in degrees
     * @param lat              the lat
     * @param lon              the lon
     * @return the double [ ]
     */
    public double[] determineLatLon(double distance, double bearingInDegrees, double lat, double lon) {
        double distanceInKm = distance / 1000;
        double bearing = Math.toRadians(bearingInDegrees);
        double lat1 = Math.toRadians(lat); //Current lat point converted to radians
        double lon1 = Math.toRadians(lon); //Current long point converted to radians

        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(distanceInKm / EARTH_RADIO) +
                Math.cos(lat1) * Math.sin(distanceInKm / EARTH_RADIO) * Math.cos(bearing));

        double lon2 = lon1 + Math.atan2(Math.sin(bearing) * Math.sin(distanceInKm / EARTH_RADIO) * Math.cos(lat1),
                Math.cos(distanceInKm / EARTH_RADIO) - Math.sin(lat1) * Math.sin(lat2));

        lat2 = Math.toDegrees(lat2);
        lon2 = Math.toDegrees(lon2);
        logger.debug("Latitude: " + lat2 + " Longitude: " + lon2);
        return new double[]{lat2, lon2};
    }

    /**
     * Is point inside polygon boolean.
     *
     * @param points the points
     * @param x      the x
     * @param y      the y
     * @return the boolean
     */
    public boolean isPointInsidePolygon(List<double[]> points, double x, double y) {
        boolean inside = false;
        int j = points.size() - 1;

        for (int i = 0; i < points.size(); j = i++) {
            double[] u0 = (double[]) points.get(i);
            double[] u1 = (double[]) points.get(j);
            if (y < u1[1]) {
                if (u0[1] <= y && (y - u0[1]) * (u1[0] - u0[0]) > (x - u0[0]) * (u1[1] - u0[1])) {
                    inside = !inside;
                }
            } else if (y < u0[1] && (y - u0[1]) * (u1[0] - u0[0]) < (x - u0[0]) * (u1[1] - u0[1])) {
                inside = !inside;
            }
        }
        return inside;
    }

    /**
     * Select all collection.
     *
     * @return the collection
     */
    public Collection<Student> selectAll() {
        return StudentDao.getDao().fetchAll();
    }

    /**
     * Add student.
     *
     * @param student the student
     * @return the student
     * @throws StudentAlreadyExistsException the student already exists exception
     * @throws StudentFieldsException        the student fields exception
     */
    public Student add(Student student) throws StudentAlreadyExistsException, StudentFieldsException {
        validateStudent(student);
        return StudentDao.getDao().insert(student);
    }

    private void validateStudent(Student student) throws StudentFieldsException {
        if (student.getName() == null) {
            throw new StudentFieldsException("Student Name cannot be null");
        }
        if (student.getLatitude() == 0) {
            throw new StudentFieldsException("Student Latitude cannot be null");
        }
        if (student.getLongitude() == 0) {
            throw new StudentFieldsException("Student Longitude cannot be null");
        }
    }

    /**
     * Gets students in classrooms.
     *
     * @param distanceInMeters the distance in meters
     * @param bearingInDegrees the bearing in degrees
     * @return the students in classrooms
     */
    public Collection<Student> getStudentsInClassrooms(double distanceInMeters, double[] bearingInDegrees) {
        return ClassroomService.getInstance().selectAll()
                .stream().map(classroom -> {
                    double[] vertex1 = determineLatLon(distanceInMeters, bearingInDegrees[0], classroom.getLatitude(), classroom.getLongitude());
                    double[] vertex2 = determineLatLon(distanceInMeters, bearingInDegrees[1], classroom.getLatitude(), classroom.getLongitude());
                    double[] vertex3 = determineLatLon(distanceInMeters, bearingInDegrees[2], classroom.getLatitude(), classroom.getLongitude());
                    double[] vertex4 = determineLatLon(distanceInMeters, bearingInDegrees[3], classroom.getLatitude(), classroom.getLongitude());
                    List<double[]> points = new ArrayList<>();
                    points.add(vertex1);
                    points.add(vertex2);
                    points.add(vertex3);
                    points.add(vertex4);
                    return getInstance().selectAll()
                            .stream().filter(student -> isPointInsidePolygon(points, student.getLatitude(), student.getLongitude()))
                            .collect(Collectors.toList());
                }).flatMap(Collection::stream).collect(Collectors.toList());
    }


}
