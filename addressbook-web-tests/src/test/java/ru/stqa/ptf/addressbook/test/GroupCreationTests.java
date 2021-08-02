package ru.stqa.ptf.addressbook.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().GroupsPage();
    Set<GroupData> before = app.group().all();

    List<GroupData> beforeList = app.group().list();
    GroupData group = new GroupData().withName("test1");
    //int before = app.getGroupHelper().getGroupsCount();

    app.group().create(group);
    //первоначальный вариант создания группы
    //app.getGroupHelper().initGroupCreation();
    //app.getGroupHelper().fillGroupForm(group);
    //app.getGroupHelper().submitGroupCreation();
    app.goTo().GroupsPage();
    Set<GroupData> after = app.group().all();
    //List<GroupData> after = app.group().list();

    //int after = app.getGroupHelper().getGroupsCount();
    //app.logOut();
    //Assert.assertEquals(after,before + 1);
    //Assert.assertEquals(after.size(),before.size() + 1);
    //after.
    /*
    int max = 0;
    for (GroupData g: after){
      if (g.getId()>max){
        max = g.getId();
      }
    }
     */

    //int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
    before.add(group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()));

    //group.setId(max);
    //закомментирую компоратор, т.к. он относится к сортировке для списков,а мы перешли на множества
    /*
    Comparator<? super GroupData> byId = (g1, g2) ->Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
     */
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    Assert.assertEquals(before,after);
    assertThat(after, equalTo(before));
  }
}
