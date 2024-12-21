// Certificatoins Object

package main.java.com.programs.model;

import java.time.LocalDate;

public class Certifications
{
    private int certID;
    private int personalID;
    private String certification;
    private String organization;
    private LocalDate issueDate;
    private LocalDate expirationDate;

    public Certifications()
    {}

    public Certifications(int certID, int personalID, String certification, String organization, LocalDate issueDate, LocalDate expirationDate)
    {
        this.certID = certID;
        this.personalID = personalID;
        this.certification = certification;
        this.organization = organization;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
    }

    public int getCertID()
    {
        return certID;
    }

    public void setCertID(int certID)
    {
        this.certID = certID;
    }

    public int getPersonalID()
    {
        return personalID;
    }

    public void setPersonalID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getCertification()
    {
        return certification;
    }

    public void setCertification(String certification)
    {
        this.certification = certification;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public LocalDate getIssueDate()
    {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate)
    {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate)
    {
        this.expirationDate = expirationDate;
    }
}
