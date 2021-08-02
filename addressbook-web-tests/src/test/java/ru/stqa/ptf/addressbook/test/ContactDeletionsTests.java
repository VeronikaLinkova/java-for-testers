package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null));
            app.goTo().homePage();
        }
    }
    @Test ()
    public void testContactDeletions(){

        List<ContactData> before = app.contact().list();
        int index = before.size()-1;

        app.contact().delete(index);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(),after.size()+1);
        before.remove(index);
        Assert.assertEquals(before,after);
    }
}
