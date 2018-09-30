package ru.zt.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zt.mantis.appmanager.ApplicationManager;

public class TestBase {

//Logger logger = LoggerFactory.getLogger(TestBase.class);

protected static final ApplicationManager app
        = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

@BeforeSuite/*один запуск*/
public void setUp() throws Exception {
  app.init();
}

@AfterSuite(alwaysRun = true)/*один запуск*/
public void tearDown() {
  app.stop();
}
}
