package ru.zt.addressbook.tests;

import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {


@Test
public void testGroupDeletion() {
  app.getNavigationHelper().gotoGroupPage();
  if (!app.getGroupHelper().isThereAGroup()){
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
  app.getGroupHelper().selectGroup();
  app.getGroupHelper().deleteSelectedGroups();
  app.getGroupHelper().returnToGroupPage();
}

}
