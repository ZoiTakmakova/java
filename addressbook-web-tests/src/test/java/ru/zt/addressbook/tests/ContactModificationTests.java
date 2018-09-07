package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
@Test(enabled = false) /*ТЕСТ ОТКЛЮЧЕН*/
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
    app.getContactHelper().createContact(new ContactData("Ivanov1", null, null, null, null, null, null, null), true);
  }
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> before = app.getContactHelper().getContactList();//кол-во контактов до модификации контакта
  //выбираем контакт
  app.getContactHelper().selectContact(before.size()-1);
  app.getContactHelper().submitEditContact(before.size()-1);
  ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Ivanov2", "Ivan2", null, null, null, null, null, null);
  app.getContactHelper().fillContactData(contact, false);
  app.getContactHelper().submitUpDateContact();
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(), before.size());//проверка: кол-во контактов после модификации должно быть равно кол-ву контактов до модификации


  before.remove(before.size()-1);
  before.add(contact);
  Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(),c2.getId()) ;
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(before,after);
}
}
