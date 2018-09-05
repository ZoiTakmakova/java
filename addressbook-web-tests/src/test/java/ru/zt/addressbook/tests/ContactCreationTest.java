package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase {

@Test
public void testAddNewCreation() {
 app.getNavigationHelper().gotoHomePage();
 int before = app.getContactHelper().getContactCount();//кол-во контактов до добавления нового контакта
 app.getNavigationHelper().gotoGroupPage();
  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  app.getNavigationHelper().gotoAddNewPage();
  app.getContactHelper().createContact(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
 int after = app.getContactHelper().getContactCount();//кол-во контактов после добавления нового контакта
 Assert.assertEquals(after, before +1);//проверка: кол-во контактов после добавление должно быть равно кол-ву контактов до добавления +1
}

}
