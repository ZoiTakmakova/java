package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {


@Test
public void testGroupDeletion() {
  app.getNavigationHelper().gotoGroupPage();

  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  List<GroupData> before = app.getGroupHelper().getGroupList();//размер списка групп  до удаления
  app.getGroupHelper().selectGroup(before.size()-1);
  app.getGroupHelper().deleteSelectedGroups();
  app.getGroupHelper().returnToGroupPage();
  List<GroupData> after = app.getGroupHelper().getGroupList();//размер списка групп после удаления
  Assert.assertEquals(after.size(),before.size()-1);
}

}
