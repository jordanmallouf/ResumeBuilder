// Education Object

package main.java.com.programs.model;

import java.time.LocalDate;

public class Education
{

    private int educationID;
    private int personalID;
    private String degree;
    private String institution;
    private LocalDate startDate;
    private LocalDate endDate;
    private String major;
    private double gpa;

    public Education()
    {}

    public Education(int educationID, int personalID, String degree, String insitutition, LocalDate startDate, LocalDate endDate, String major, Double gpa)
    {
        this.educationID = educationID;
        this.personalID = personalID;
        this.degree = degree;
        this.institution = insitutition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.major = major;
        this.gpa = gpa;
    }

    public int getEducationID()
    {
        return educationID;
    }

    public void setEducationID(int educationID)
    {
        this.educationID = educationID;
    }

    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    public String getInstitution()
    {
        return institution;
    }

    public void setInstitution(String institution)
    {
        this.institution = institution;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public String getMajor()
    {
        return major;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    public double getGPA()
    {
        return gpa;
    }

    public void setGPA(double gpa)
    {
        this.gpa = gpa;
    }
}
