package ru.stqa.ptf.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nikcname;

    public ContactData(String firstname, String middlename, String lastname, String nikcname) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nikcname = nikcname;
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
}
