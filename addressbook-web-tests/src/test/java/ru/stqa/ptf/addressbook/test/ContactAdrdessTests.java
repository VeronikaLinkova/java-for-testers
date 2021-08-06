package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdrdessTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData().withFirstname("Veronika1990First").withMiddlename("Alexandrovna").withNikcname("Nika")
                    .withLastname("Linkova").withAddress("test"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactAddress() throws Exception{
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(),
                equalTo(contactInfoFromEditForm.getAddress()));
    }
}
