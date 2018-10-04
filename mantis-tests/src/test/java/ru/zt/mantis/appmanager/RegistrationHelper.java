package ru.zt.mantis.appmanager;

import com.sun.javafx.binding.ExpressionHelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

private WebDriver wd;

public RegistrationHelper(ApplicationManager app) {
  super(app);
}

public void start(String username, String email)
{
  wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  type(By.name("username"),username);
  type(By.name("email"),email);
  click(By.cssSelector("input[value='Signup']"));
}

public void finish(String cofirmationLink, String password) {
  wd.get(cofirmationLink);
  type(By.name("password"),password);
  type(By.name("password_confirm"),password);
  click(By.cssSelector("input[value='Update User']"));
}
}
