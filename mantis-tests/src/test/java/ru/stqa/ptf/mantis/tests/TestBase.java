package ru.stqa.ptf.mantis.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.ptf.mantis.appmanager.ApplicationManager;

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
}
