package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.Test;

public class ContactDeletionsTests extends TestBase{
    @Test
    public void testContactDeletions(){
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }
}
