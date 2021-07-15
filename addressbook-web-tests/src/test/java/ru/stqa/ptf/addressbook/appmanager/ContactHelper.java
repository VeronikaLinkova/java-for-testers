package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void confirmNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
        //wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillNewContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        //wd.findElement(By.name("firstname")).click();
        //wd.findElement(By.name("firstname")).clear();
        //wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        //wd.findElement(By.name("middlename")).click();
        //wd.findElement(By.name("middlename")).clear();
        //wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNikcname());
        /*
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNikcname());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHome());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(contactData.getWork());
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys("ru/stqa/ptf/addressbook/test");

         */
    }
}
