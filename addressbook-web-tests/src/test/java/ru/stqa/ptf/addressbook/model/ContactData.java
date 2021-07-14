package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nikcname;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String work;

    public ContactData(String firstname, String middlename, String lastname, String nikcname, String company, String address, String home, String mobile, String work) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nikcname = nikcname;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNikcname() {
        return nikcname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }
}
