// Experience Object

package main.java.com.programs.model;

import java.time.LocalDate;

public class Experience
{
    private int experienceID;
    private int personalID;
    private String jobTitle;
    private String companyName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Experience()
    {}

    public Experience(int experienceID, int personalID, String jobTitle, String companyName, String description, LocalDate startDate, LocalDate endDate)
    {
        this.experienceID = experienceID;
        this.personalID = personalID;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getExperienceID()
    {
        return experienceID;
    }

    public void setExperienceID(int experienceID)
    {
        this.experienceID = experienceID;
    }

    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
}
