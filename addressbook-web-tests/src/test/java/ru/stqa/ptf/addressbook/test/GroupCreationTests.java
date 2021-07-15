package ru.stqa.ptf.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupsPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "tets2", "teste3"));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().goToGroupsPage();
    app.logOut();
  }
}
