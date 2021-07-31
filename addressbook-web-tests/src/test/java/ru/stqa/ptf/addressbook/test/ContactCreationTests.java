package ru.stqa.ptf.addressbook.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getNavigationHelper().goToAddNewContactPage();
    ContactData contact = new ContactData("1Veronika", "Alexandrovna","Linkova","Nika", null);
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().confirmNewContact();
    app.getNavigationHelper().goToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size(),after.size()-1);
    before.add(contact);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Comparator<? super ContactData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
    after.sort(byId);
    before.sort(byId);
    Assert.assertEquals(before,after);
  }
}
