package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        if (!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Veronika02/03/1990","Alexandrovna","Krilova","Nika",null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
