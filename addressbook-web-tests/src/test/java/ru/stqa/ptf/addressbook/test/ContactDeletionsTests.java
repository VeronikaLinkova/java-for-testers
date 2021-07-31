package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase{
    @Test (enabled = false)
    public void testContactDeletions(){

        if (!app.getContactHelper().isThereAContact()){
            app.goTo().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.goTo().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().submitContactDeletion();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size()+1);
        before.remove(before.size()-1);
        Assert.assertEquals(before,after);
    }
}
