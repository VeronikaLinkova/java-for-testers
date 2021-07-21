package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void confirmNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }
    public void submitContactDeletion(){
        wd.switchTo().alert().accept();
    }
    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification() {click(By.name("update")); }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNikcname());
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(3);
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }
}
