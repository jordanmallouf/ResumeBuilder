// Achievements Object

package main.java.com.programs.model;

import java.time.LocalDate;

public class Achievements
{
    private int achievementID;
    private int personalID;
    private String achievement;
    private String achievementDescription;
    private LocalDate achievementDate;

    public Achievements()
    {}

    public Achievements(int achievementID, int personalID, String achievement, String achievementDescription, LocalDate achievementDate)
    {
        this.achievementID = achievementID;
        this.personalID = personalID;
        this.achievement = achievement;
        this.achievementDescription = achievementDescription;
        this.achievementDate = achievementDate;
    }

    public int getAchievementID()
    {
        return achievementID;
    }

    public void setAchievementID(int achievementID)
    {
        this.achievementID = achievementID;
    }

    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getAchievement()
    {
        return achievement;
    }

    public void setAchievement(String achievement)
    {
        this.achievement = achievement;
    }

    public String getAchievementDescription()
    {
        return achievementDescription;
    }

    public void setAchievementDescription(String achievementDescription)
    {
        this.achievementDescription = achievementDescription;
    }

    public LocalDate getAchievementDate()
    {
        return achievementDate;
    }

    public void setAchievementDate(LocalDate achievementDate)
    {
        this.achievementDate = achievementDate;
    }
}
