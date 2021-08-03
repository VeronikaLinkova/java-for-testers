package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test()
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();

    app.goTo().goToAddNewContactPage();
    ContactData contact = new ContactData().withFirstname("Ekaterina1").withLastname("111").withNikcname("222");

    app.contact().create(contact);
    app.goTo().homePage();

    Contacts after = app.contact().all();

    Assert.assertEquals(before.size(),after.size()-1);
    int max = after.stream().mapToInt((c)->c.getId()).max().getAsInt();
    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(max))));
  }
}
