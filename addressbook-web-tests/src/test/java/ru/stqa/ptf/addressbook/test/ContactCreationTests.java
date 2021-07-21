package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", "test1"), true);
    app.getContactHelper().confirmNewContact();
    app.getNavigationHelper().goToHomePage();
    app.logOut();
  }
}
