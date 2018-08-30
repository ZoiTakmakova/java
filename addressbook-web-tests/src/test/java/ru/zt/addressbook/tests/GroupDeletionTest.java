package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {


@Test
public void testGroupDeletion() {
  app.getNavigationHelper().gotoGroupPage();
  int before = app.getGroupHelper().getGroupCount(); //количество групп до добавления
  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  app.getGroupHelper().selectGroup(before-1);
  app.getGroupHelper().deleteSelectedGroups();
  app.getGroupHelper().returnToGroupPage();
  int after = app.getGroupHelper().getGroupCount(); //количество групп после добавления
  Assert.assertEquals(after,before-1);
}

}
