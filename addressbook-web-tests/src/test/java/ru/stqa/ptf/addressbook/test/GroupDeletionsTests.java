package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionsTests extends TestBase {

  @Test
  public void testGroupDeletions() throws Exception {
    app.getNavigationHelper().goToGroupsPage();

    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().goToGroupsPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupsCount();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();

    app.getNavigationHelper().goToGroupsPage();
    //int after = app.getGroupHelper().getGroupsCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after,before - 1);
    Assert.assertEquals(after.size(),before.size() - 1);
  }
}

