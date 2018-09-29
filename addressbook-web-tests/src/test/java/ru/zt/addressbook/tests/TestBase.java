package ru.zt.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zt.addressbook.appmanager.ApplicationManager;

public class TestBase {

protected static final ApplicationManager app
        = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

@BeforeSuite/*один запуск*/
public void setUp() throws Exception {
  app.init();
}

@AfterSuite/*один запуск*/
public void tearDown() {
  app.stop();
}

}
