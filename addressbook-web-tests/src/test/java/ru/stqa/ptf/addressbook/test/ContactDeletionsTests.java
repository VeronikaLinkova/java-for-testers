package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionsTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData().withFirstname("Veronika1990").withMiddlename("Alexandrovna").
                    withLastname("Linkova").withNikcname("Nika"));
            app.goTo().homePage();
        }
    }
    @Test ()
    public void testContactDeletions(){

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);
        app.goTo().homePage();

        Contacts after = app.contact().all();
        Assert.assertEquals(before.size(),after.size()+1);
        assertThat(before.without(deletedContact), equalTo(after));
    }
}
