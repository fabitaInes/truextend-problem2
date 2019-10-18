package com.truextend.problem2.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Student service test.
 */
public class StudentServiceTest {

    /**
     * Determine lat lon.
     */
    @Test
    public void determineLatLon() {
        double[] location = StudentService.getInstance().determineLatLon(10, 45, 34.069140, -118.442689);
        assertEquals(34.06920352082758d, location[0], 0.0);
        assertEquals(-118.44261231748044d, location[1], 0.0);
    }

    /**
     * Is point inside polygon.
     */
    @Test
    public void isPointInsidePolygon() {
        List<double[]> points = new ArrayList<>();
        points.add(new double[]{10,0});
        points.add(new double[]{0,0});
        points.add(new double[]{0,10});
        points.add(new double[]{10,10});
                
        assertTrue(StudentService.getInstance().isPointInsidePolygon(points, 5, 5));
        assertFalse(StudentService.getInstance().isPointInsidePolygon(points, -5, -5));
    }
}