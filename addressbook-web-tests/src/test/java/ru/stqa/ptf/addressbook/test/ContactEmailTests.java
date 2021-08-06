package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData().withFirstname("Veronika1990First").withMiddlename("Alexandrovna").withNikcname("Nika")
                    .withLastname("Linkova").
                            withEmail("email1").withEmail2("email2").withEmail3("email1@yandex.ru"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactEmail() throws Exception{
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmailAdress(),
                equalTo(mergeEmailAddress(contactInfoFromEditForm)));
    }
    private String mergeEmailAddress(ContactData emailAddress) {
        return Arrays.asList(emailAddress.getEmail(),emailAddress.getEmail2(),
                emailAddress.getEmail3()).stream().filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
