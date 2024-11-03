package com.oop;

public class Education {
    private String degree;
    private String institute;
    private String startYear;
    private String endYear;

    public Education(String degree, String institute, String startYear, String endYear) {
        this.degree = degree;
        this.institute = institute;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getDegree() {
        return degree;
    }

    public String getInstitute() {
        return institute;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public String toString() {
        return degree + " from " + institute + "\n(" + startYear + " - " + endYear + ")\n";
    }
}
