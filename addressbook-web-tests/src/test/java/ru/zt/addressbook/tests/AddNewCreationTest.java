package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.AddNewData;

public class AddNewCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
  app.gotoAddNewPage();
  app.fillAddNewForm(new AddNewData("Ivanov", "Ivan", "Ivanovich", "Tver", "8956234", "89041235678", "ivanov@mail.ru"));
  app.submitAddNewCreation();
  app.returnToHomePage();
}

}
