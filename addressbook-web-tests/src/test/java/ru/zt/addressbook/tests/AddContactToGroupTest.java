package ru.zt.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

private SessionFactory sessionFactory;
private ContactData contact;

//предусловие: если нет контакта, создать его
@BeforeMethod
public void ensurePrecondition1() {
  if (app.db().contacts().size() == 0) {
    app.goTo().gotoAddNewPage();
    app.contact().create(new ContactData().withLastname("Ivanov1").withFirstname("Ivan1").
            withHomePhone("111111").withMobilePhone("22222").withWorkPhone("33333"), true);
  }
}
//предусловие: если нет группы, создать ее
@BeforeMethod
public void ensurePrecondition2() {
  if (app.db().groups().size() == 0) {
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
  }
}

//тест добавления контакта в группу
@Test
public void addContactToGroup() {

  app.goTo().homePage();
  //получить список контактов
  Contacts beforeContact = app.db().contacts();
  //выбрать контакт
  ContactData addedContact = beforeContact.iterator().next();
  //получить ИД контакта
  int contactId = addedContact.getId();
 //получить список групп в которых состоит контакт
  Groups beforeGroupsOfContact = addedContact.getGroups();


  //выгрузить группы из бд
  Groups allGroups = app.db().groups();
  //выбрать группу
 GroupData addedGroup = allGroups.iterator().next();

 //проверка надо ли создавать новую группу

  if(beforeGroupsOfContact.size()==allGroups.size()){
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test2").withHeader("test2").withFooter("test3"));
  }

 /*
  for (GroupData g1 : allGroups) {
   for(GroupData g2: beforeGroupsOfContact){
     assertThat(g1, equalTo(g2));
   }
  }*/

  //добавить контакт в группу
  app.contact().addToGroup(addedContact, addedGroup);

  //проверка того что контакт добавлен в группу
  Contacts afterContact = app.db().contactInGroup(contactId);
  ContactData a = afterContact.iterator().next();
  Groups afterGroups = a.getGroups();



 assertThat(beforeGroupsOfContact.size(), equalTo(afterGroups.size()-1));



/*
  app.goTo().homePage();
  //выбрать контакт
  Contacts beforeContact = app.db().contacts();
  ContactData addedContact = beforeContact.iterator().next();

//проверить что контакт добавлен во все группы


  //выгрузить группы из бд
  Groups beforeGroup = app.db().groups();
  GroupData addedGroup = beforeGroup.iterator().next();

  //добавить контакт в группу
  app.contact().addToGroup(addedContact,addedGroup);

// проверить что контакт добавлен в группу
  Contacts afterContact = app.db().contactInGroup();
  int contactInGroup = afterContact.iterator().next().getId();

*/


}
}
