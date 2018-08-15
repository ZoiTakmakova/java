package ru.zt.addressbook;

import org.testng.annotations.Test;

public class AddNewCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
  gotoAddNewPage();
  fillAddNewForm(new AddNewData("Ivanov", "Ivan", "Ivanovich", "Tver", "8956234", "89041235678", "ivanov@mail.ru"));
  submitAddNewCreation();
  returnToHomePage();
}

}
