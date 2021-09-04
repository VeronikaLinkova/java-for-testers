package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(
            System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }
    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m){

    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){

    }

    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUI")){
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();

            MatcherAssert.assertThat(dbGroups.stream().map(g->new GroupData().withId(g.getId()).withName(g.getName()))
                            .collect(Collectors.toSet()),
                    CoreMatchers.equalTo(uiGroups));
        }
    }
}
