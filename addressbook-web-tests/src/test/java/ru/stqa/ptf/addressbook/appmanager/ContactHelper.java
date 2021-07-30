package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void initContactModification(int index) {
        List<WebElement> element = wd.findElements(By.xpath("//img[@alt='Edit']"));
        element.get(index).click();
        //click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification() {click(By.name("update")); }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNikcname());
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void createContact(ContactData contact) {
        fillContactForm(new ContactData("Veronika1990", "Alexandrovna","Linkova","Nika", null), true);
        confirmNewContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<WebElement> elements = wd.findElements(By.name("entry"));
        //List<WebElement> elements = wd.findElements(By.name("selected[]"));
        List <ContactData> contacts = new ArrayList<>();
        for (WebElement element: elements){
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName= element.findElement(By.xpath("td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id,firstName, null,lastName, null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
