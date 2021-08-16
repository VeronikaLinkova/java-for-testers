package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line!=null){
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withNikcname(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    Contacts before = app.contact().all();

    app.goTo().goToAddNewContactPage();
    File photo = new File("src/test/resources/photo.png");
    //ContactData contact = new ContactData().withFirstname("Ekaterina1").withLastname("111").withNikcname("222")
    //        .withPhoto(photo);

    app.contact().create(contact);
    app.goTo().homePage();

    Assert.assertEquals(before.size(),app.contact().count()-1);
    Contacts after = app.contact().all();

    int max = after.stream().mapToInt((c)->c.getId()).max().getAsInt();
    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(max))));
  }
}
