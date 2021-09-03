package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.db().contacts().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData().withFirstname("Veronika1990First").withMiddlename("Alexandrovna").withNikcname("Nika")
                    .withLastname("Linkova").withNikcname("test").withHomePhone("111"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstname("Veronika1990Last").withLastname("Test3").withMiddlename("Test3").
                withLastname("Ivanova11").withNikcname("test").withHomePhone("111");

        app.contact().modify(contact);
        app.goTo().homePage();
        Assert.assertEquals(before.size(),app.contact().count());
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(
                before.without(modifiedContact).withAdded(contact)));
    }
}
