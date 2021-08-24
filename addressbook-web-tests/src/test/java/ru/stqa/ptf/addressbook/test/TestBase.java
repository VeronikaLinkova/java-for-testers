package ru.stqa.ptf.addressbook.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(
            System.getProperty("browser",BrowserType.FIREFOX));

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
}
