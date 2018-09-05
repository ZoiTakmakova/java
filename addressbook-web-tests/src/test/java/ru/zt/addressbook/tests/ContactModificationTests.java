package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTests extends TestBase {
@Test
public void testContactModification() {
  //переход на главную страницу
  app.getNavigationHelper().gotoHomePage();
  //если контактов нет
  if (!app.getContactHelper().isThereAContact()) {
    //переходим на страницу "группы"
    app.getNavigationHelper().gotoGroupPage();
    //если групп нет
    if (!app.getGroupHelper().isThereAGroup()) {
      //создаем новую группу
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    //переходим на форму создания контаков
    app.getNavigationHelper().gotoAddNewPage();
    //создаем новый контакт
    app.getContactHelper().createContact(new ContactData("Ivanov23", "Ivan", "Ivanovich", null, "8956234", "89041235678", "ivanov@mail.ru", "test1"), true);
  }
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> before = app.getContactHelper().getContactList();//кол-во контактов до модификации контакта
  //выбираем контакт
  app.getContactHelper().selectContact(before.size()-1);
  app.getContactHelper().submitEditContact();
  app.getContactHelper().fillContactData(new ContactData("Ivanov6", "Ivan6", "Ivanovich6", "Tver", "8956234", "89041235678", "ivanov@mail.ru", null), false);
  app.getContactHelper().submitUpDateContact();
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());//проверка: кол-во контактов после модификации должно быть равно кол-ву контактов до модификации

}
}
