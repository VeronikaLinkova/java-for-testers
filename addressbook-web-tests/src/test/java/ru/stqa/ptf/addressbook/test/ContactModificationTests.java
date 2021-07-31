package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test(enabled = false)
    public void testContactModification(){

        if (!app.getContactHelper().isThereAContact()){
            app.goTo().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.goTo().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size()-1);
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test2","Alexandrovna","Krilova","Nika",null);
        app.getContactHelper().fillContactForm(new ContactData("Test2","Alexandrovna","Krilova","Nika",null),false);
        app.getContactHelper().submitContactModification();
        app.goTo().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size(),after.size());
        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        Comparator<? super ContactData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before,after);
    }
}
