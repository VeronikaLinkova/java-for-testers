package ru.stqa.ptf.addressbook.model;

import java.util.Objects;

public class ContactData {
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNikcname(String nikcname) {
        this.nikcname = nikcname;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withHomePhone(String homePhone){
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone){
        this.workPhone = workPhone;
        return this;
    }

    private String firstname;
    private String middlename;
    private String lastname;
    private String nikcname;
    private int id = Integer.MAX_VALUE;;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    public int getId() {
        return id;
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

    public String getGroup(){return group;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, id);
    }
}
