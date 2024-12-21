// Skills Object

package main.java.com.programs.model;

public class Skills
{
    private int skillsID;
    private int personalID;
    private String skill;
    private String skillDescription;
    private String proficiency;

    public Skills()
    {}

    public Skills(int skillsID, int personalID, String skill, String skillDescription, String proficiency)
    {
        this.skillsID = skillsID;
        this.personalID = personalID;
        this.skill = skill;
        this.skillDescription = skillDescription;
        this.proficiency = proficiency;
    }

    public int getSkillsID()
    {
        return skillsID;
    }

    public void setSkillsID(int skillsID)
    {
        this.skillsID = skillsID;
    }
    
    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(String skill)
    {
        this.skill = skill;
    }

    public String getSkillDescription()
    {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription)
    {
        this.skillDescription = skillDescription;
    }

    public String getProficiency()
    {
        return proficiency;
    }

    public void setProficiency(String proficiency)
    {
        this.proficiency = proficiency;
    }
}