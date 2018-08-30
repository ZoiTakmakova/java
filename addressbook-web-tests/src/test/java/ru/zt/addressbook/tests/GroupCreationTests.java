//Декларация пакета
package ru.zt.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

@Test
public void testGroupCreation() {
  app.getNavigationHelper().gotoGroupPage();
  int before = app.getGroupHelper().getGroupCount(); //количество групп до добавления
  app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  int after = app.getGroupHelper().getGroupCount(); //количество групп после добавления
  Assert.assertEquals(after,before+1);
}
}
