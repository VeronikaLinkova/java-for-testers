package ru.stqa.ptf.addressbook.test;

import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line!=null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c)->new Object[]{c}).collect(Collectors.toList()).iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    Contacts before = app.db().contacts();

    app.goTo().goToAddNewContactPage();
    //File photo = new File("src/test/resources/photo.png");
    //ContactData contact = new ContactData().withFirstname("Ekaterina1").withLastname("111").withNikcname("222")
    //        .withPhoto(photo);

    app.contact().create(contact);
    app.goTo().homePage();

    Assert.assertEquals(before.size(),app.contact().count()-1);
    Contacts after = app.db().contacts();

    int max = after.stream().mapToInt((c)->c.getId()).max().getAsInt();
    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(max))));
  }
}
