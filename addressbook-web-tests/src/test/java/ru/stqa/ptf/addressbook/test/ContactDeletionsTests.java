package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletionsTests extends TestBase{
    @Test
    public void testContactDeletions(){

        if (!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.getNavigationHelper().goToHomePage();
        }

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }
}
