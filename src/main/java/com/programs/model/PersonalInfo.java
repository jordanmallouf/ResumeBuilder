// Personal Information Object

package main.java.com.programs.model;

public class PersonalInfo
{
    private int personalID;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    public PersonalInfo()
    {}

    public PersonalInfo(int personalID, String fName, String lName, String email, String phone, String address, String city, String state, String zip, String country)
    {
        this.personalID = personalID;
        this.fName = fName;
        this.lName = lName;
        this. email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    //Getters and Setters
    public int getID()
    {
        return personalID;
    }

    public void setID(int personalID)
    {
        this.personalID = personalID;
    }

    public String getFName()
    {
        return fName;
    }

    public void setFName(String fName)
    {
        this.fName = fName;
    }

    public String getLName()
    {
        return lName;
    }

    public void setLName(String lName)
    {
        this.lName = lName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    // Utility method for Full Name
    public String getFullName()
    {
        return fName + " " + lName;
    }
}


