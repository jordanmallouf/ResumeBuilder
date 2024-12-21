// Projects Object

package main.java.com.programs.model;

import java.time.LocalDate;

public class Projects
{
    private int projectID;
    private int personalID;
    private String projectName;
    private String description;
    private String tools;
    private LocalDate startDate;
    private LocalDate endDate;

    public Projects()
    {}

    public Projects(int projectID, int personalID, String projectName, String description, String tools, LocalDate startDate, LocalDate endDate)
    {
        this.projectID = projectID;
        this.personalID = personalID;
        this.projectName = projectName;
        this.description = description;
        this.tools = tools;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProjectID()
    {
        return projectID;
    }

    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }

    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTools()
    {
        return tools;
    }

    public void setTools(String tools)
    {
        this.tools = tools;
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
