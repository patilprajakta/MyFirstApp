package com.example.synerzip.sqlite;
//CONTENT PROVIDER
//The need for Content Providers arises because the database created in one application is not visible to a second application.
//Android app by default cannot access another app’s data
public class DataProvider
{
    private String Name;
    private String Rollno;
    private String Marks;


    public DataProvider(String Name,String Rollno, String Marks)
    {
        this.Name=Name;
        this.Rollno=Rollno;
        this.Marks=Marks;

    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }
    public String getRollno()
    {
        return Rollno;
    }

    public void setRollno(String rollno)
    {
        Rollno = rollno;
    }
    public String getMarks()
    {
        return Marks;
    }

    public void setMarks(String marks)
    {
        Marks = marks;
    }
}
