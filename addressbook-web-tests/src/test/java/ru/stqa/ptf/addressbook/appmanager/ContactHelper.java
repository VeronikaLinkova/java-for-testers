package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void confirmNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }
    public void submitContactDeletion(){
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }
    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public int count(){
        return wd.findElements(By.name("selected[]")).size();
    }
    public void selectContactById(int id) {

        //wd.findElements(By.name("selected[]")).get(index).click();
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void initContactModification(int index) {
        List<WebElement> element = wd.findElements(By.xpath("//img[@alt='Edit']"));
        element.get(index).click();
        //click(By.xpath("//img[@alt='Edit']"));
    }
    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
    }
    public void submitContactModification() {click(By.name("update")); }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNikcname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        //attach(By.name("photo"),contactData.getPhoto());

        /*
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

         */
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<WebElement> elements = wd.findElements(By.name("entry"));
        //List<WebElement> elements = wd.findElements(By.name("selected[]"));
        List <ContactData> contacts = new ArrayList<>();
        for (WebElement element: elements){
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName= element.findElement(By.xpath("td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;
    public Contacts all() {
        if (contactCache!=null){
            return new Contacts(contactCache);
        }
        else {
            List<WebElement> elements = wd.findElements(By.name("entry"));
            //List<WebElement> elements = wd.findElements(By.name("selected[]"));
            contactCache = new Contacts();
            //List <ContactData> contacts = new ArrayList<>();
            for (WebElement element: elements){
                String firstName = element.findElement(By.xpath("td[3]")).getText();
                String lastName= element.findElement(By.xpath("td[2]")).getText();

                String allPhones = element.findElement(By.xpath("td[6]")).getText();
                //String[] phones = element.findElement(By.xpath("td[6]")).getText().split("\n");
                String allEmailAdress = element.findElement(By.xpath("td[5]")).getText();
                String adress = element.findElement(By.xpath("td[4]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).
                        withAllPhones(allPhones).withAddress(adress).withAllEmailAdress(allEmailAdress);
                contactCache.add(contact);
            }
            return new Contacts(contactCache);
        }
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        confirmNewContact();
        contactCache = null;
    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        contactCache = null;
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        submitContactDeletion();
        contactCache = null;
    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        submitContactDeletion();
        contactCache = null;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName= wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
