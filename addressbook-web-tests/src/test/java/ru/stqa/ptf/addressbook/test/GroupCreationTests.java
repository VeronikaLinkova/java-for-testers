package ru.stqa.ptf.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupsPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupsCount();

    app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData("test1", null, null);
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().goToGroupsPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    //int after = app.getGroupHelper().getGroupsCount();
    app.logOut();
    //Assert.assertEquals(after,before + 1);
    Assert.assertEquals(after.size(),before.size() + 1);
    //after.
    /*
    int max = 0;
    for (GroupData g: after){
      if (g.getId()>max){
        max = g.getId();
      }
    }
     */

    before.add(group);
    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
    //group.setId(max);
    Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
  }
}
