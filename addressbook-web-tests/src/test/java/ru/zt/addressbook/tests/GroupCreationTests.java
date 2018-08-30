//Декларация пакета
package ru.zt.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;
import java.util.List;


public class GroupCreationTests extends TestBase {

@Test
public void testGroupCreation() {
  app.getNavigationHelper().gotoGroupPage();

  List<GroupData> before = app.getGroupHelper().getGroupList();//размер списка до создания
  app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  List<GroupData> after = app.getGroupHelper().getGroupList();//размер списка после удаления
  Assert.assertEquals(after.size(),before.size()+1);
}
}
