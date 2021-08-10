package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInformationTests extends TestBase{
    @BeforeTest()
    public void ensurePreconditions(){
        if (app.contact().list().size()==0){
            app.goTo().goToAddNewContactPage();
            app.contact().create(new ContactData().withFirstname("Veronika1990").withMiddlename("Alexandrovna").
                    withLastname("Linkova").withNikcname("Nika")
                    .withHomePhone("90201111").withMobilePhone("945-99-50-20").withWorkPhone("(90)")
            .withAddress("test").withEmail("email1").withEmail2("email2").withEmail3("email1@yandex.ru"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactPhones() throws Exception{
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(),
                equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test()
    public void testContactEmail() throws Exception{
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmailAdress(),
                equalTo(mergeEmailAddress(contactInfoFromEditForm)));
    }

    @Test()
    public void testContactAddress() throws Exception{
        app.goTo().homePage();

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(),
                equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),
                contact.getWorkPhone()).stream().filter((s)->!s.equals(""))
                .map(ContactInformationTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmailAddress(ContactData emailAddress) {
        return Arrays.asList(emailAddress.getEmail(),emailAddress.getEmail2(),
                emailAddress.getEmail3()).stream().filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
