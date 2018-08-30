package ru.zt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.zt.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount(); //количество групп до добавления
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test12", "test22", "test33"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount(); //количество групп после добавления
    Assert.assertEquals(after,before);

  }
}
