package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactModification(){
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Test2","Alexandrovna","Krilova","Nika",null);

        int index = before.size()-1;

        app.contact().modify(index, contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(before.size(),after.size());
        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        Comparator<? super ContactData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(before,after);
    }
}
