package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

@Test
public void testGroupModification() {
  app.getNavigationHelper().gotoGroupPage();

  if (!app.getGroupHelper().isThereAGroup()) {
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  List<GroupData> before = app.getGroupHelper().getGroupList();//размер списка до модификации
  app.getNavigationHelper().gotoGroupPage();
  app.getGroupHelper().selectGroup(before.size() - 1);//выбрать последнюю в списке группу
  app.getGroupHelper().initGroupModification();// редактировать группу
  GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test1", "test2", "test3");
  app.getGroupHelper().fillGroupForm(group);//заполнить группу
  app.getGroupHelper().submitGroupModification();//обновить группу
  app.getGroupHelper().returnToGroupPage();
  List<GroupData> after = app.getGroupHelper().getGroupList();//размер списка после модификации
  Assert.assertEquals(after.size(), before.size());

  before.remove(before.size() - 1);
  before.add(group);
  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
}
}
