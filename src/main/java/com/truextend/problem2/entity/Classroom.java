package com.truextend.problem2.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * The type Classroom.
 */
public class Classroom {
    @ApiModelProperty(notes = "Classroom name")
    private String name;
    @ApiModelProperty(notes = "Classroom latitude")
    private double latitude;
    @ApiModelProperty(notes = "Classroom longitude")
    private double longitude;

    /**
     * Instantiates a new Classroom.
     */
    public Classroom() {
    }

    /**
     * Instantiates a new Classroom.
     *
     * @param name      the name
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Classroom(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
