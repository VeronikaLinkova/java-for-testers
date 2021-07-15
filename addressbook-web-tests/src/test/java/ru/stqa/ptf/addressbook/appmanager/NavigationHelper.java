package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);//явно вызывем конструктр базового класса
    }

    public void goToGroupsPage() {
        click(By.linkText("groups"));
    }
}
